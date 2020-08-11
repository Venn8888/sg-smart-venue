package com.sg.gateway.autoconfigure;

import com.sg.gateway.exception.JsonSignatureDeniedHandler;
import com.sg.gateway.filter.AccessAuthorizationManager;
import com.sg.gateway.filter.PreRequestFilter;
import com.sg.gateway.filter.PreSignatureFilter;
import com.sg.gateway.oauth2.RedisAuthenticationManager;
import com.sg.gateway.service.IBaseAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.oauth2.server.resource.web.server.ServerBearerTokenAuthenticationConverter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.context.SecurityContextServerWebExchange;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * oauth2资源服务器配置
 *
 * @author: xxxxxxx
 * @date: 2019/5/8 18:45
 * @description:
 */
@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfiguration {

    private static final String MAX_AGE = "18000L";

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private ApiProperties apiProperties;

    @Autowired
    private IBaseAppService baseAppService;

    /**
     * 跨域配置
     *
     * @return
     */
    public WebFilter corsFilter() {
        return (ServerWebExchange ctx, WebFilterChain chain) -> {
            ServerHttpRequest request = ctx.getRequest();
            if (CorsUtils.isCorsRequest(request)) {
                HttpHeaders requestHeaders = request.getHeaders();
                ServerHttpResponse response = ctx.getResponse();
                HttpMethod requestMethod = requestHeaders.getAccessControlRequestMethod();
                HttpHeaders headers = response.getHeaders();
                headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, requestHeaders.getOrigin());
                headers.addAll(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, requestHeaders.getAccessControlRequestHeaders());
                if (requestMethod != null) {
                    headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, requestMethod.name());
                }
                headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
                headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "*");
                headers.add(HttpHeaders.ACCESS_CONTROL_MAX_AGE, MAX_AGE);
                if (request.getMethod() == HttpMethod.OPTIONS) {
                    response.setStatusCode(HttpStatus.OK);
                    return Mono.empty();
                }
            }
            return chain.filter(ctx);
        };
    }


    @Bean
    SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) throws Exception {
        // 自定义oauth2 认证, 使用redis读取token,而非jwt方式
        AccessAuthorizationManager accessAuthorizationManager = new AccessAuthorizationManager(apiProperties);
        AuthenticationWebFilter oauth2 = new AuthenticationWebFilter(new RedisAuthenticationManager(new RedisTokenStore(redisConnectionFactory)));
        oauth2.setServerAuthenticationConverter(new ServerBearerTokenAuthenticationConverter());
        oauth2.setAuthenticationSuccessHandler((webFilterExchange, authentication) -> {
            ServerWebExchange exchange = webFilterExchange.getExchange();
            SecurityContextServerWebExchange securityContextServerWebExchange = new SecurityContextServerWebExchange(exchange, ReactiveSecurityContextHolder.getContext().subscriberContext(
                    ReactiveSecurityContextHolder.withAuthentication(authentication)
            ));
            return webFilterExchange.getChain().filter(securityContextServerWebExchange);
        });
        http
                .httpBasic().disable()
                .csrf().disable()
                .authorizeExchange()
                .pathMatchers("/").permitAll()
                // 动态权限验证
                .anyExchange().access(accessAuthorizationManager)
                .and().exceptionHandling()
                .and()
                // 日志前置过滤器
                .addFilterAt(new PreRequestFilter(), SecurityWebFiltersOrder.FIRST)
                // 跨域过滤器
                .addFilterAt(corsFilter(), SecurityWebFiltersOrder.CORS)
                // 签名验证过滤器
                .addFilterAt(new PreSignatureFilter(baseAppService, apiProperties, new JsonSignatureDeniedHandler()), SecurityWebFiltersOrder.CSRF)
                // oauth2认证过滤器
                .addFilterAt(oauth2, SecurityWebFiltersOrder.AUTHENTICATION);
        return http.build();
    }
}
