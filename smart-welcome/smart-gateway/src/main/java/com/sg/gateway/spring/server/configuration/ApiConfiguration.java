package com.sg.gateway.spring.server.configuration;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnEnabledEndpoint;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.bus.BusProperties;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.reactive.result.view.ViewResolver;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.sg.framework.spring.SpringContextHolder;
import com.sg.gateway.spring.server.actuator.ApiEndpoint;
import com.sg.gateway.spring.server.exception.JsonExceptionHandler;
import com.sg.gateway.spring.server.locator.ApiResourceLocator;
import com.sg.gateway.spring.server.locator.JdbcRouteDefinitionLocator;
import com.sg.gateway.spring.server.service.IBaseAuthorityService;
import com.sg.gateway.spring.server.service.IGatewayService;
import com.sg.gateway.spring.server.service.impl.AccessLogService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * 网关配置类
 *
 *
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({ApiProperties.class})
public class ApiConfiguration {


    @Bean
    @ConditionalOnMissingBean(SpringContextHolder.class)
    public SpringContextHolder springContextHolder() {
        SpringContextHolder holder = new SpringContextHolder();
        log.info("bean [{}]", holder);
        return holder;
    }

    /**
     * 自定义异常处理[@@]注册Bean时依赖的Bean，会从容器中直接获取，所以直接注入即可
     *
     * @param viewResolversProvider
     * @param serverCodecConfigurer
     * @return
     */
    @Primary
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ErrorWebExceptionHandler errorWebExceptionHandler(ObjectProvider<List<ViewResolver>> viewResolversProvider,
                                                             ServerCodecConfigurer serverCodecConfigurer, AccessLogService accessLogService) {

        JsonExceptionHandler jsonExceptionHandler = new JsonExceptionHandler(accessLogService);
        jsonExceptionHandler.setViewResolvers(viewResolversProvider.getIfAvailable(Collections::emptyList));
        jsonExceptionHandler.setMessageWriters(serverCodecConfigurer.getWriters());
        jsonExceptionHandler.setMessageReaders(serverCodecConfigurer.getReaders());
        log.debug("Init Json Exception Handler Instead Default ErrorWebExceptionHandler Success");
        return jsonExceptionHandler;
    }

    /**
     * Jackson全局配置
     *
     * @param properties
     * @return
     */
    @Bean
    @Primary
    public JacksonProperties jacksonProperties(JacksonProperties properties) {
        properties.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
        properties.getSerialization().put(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        properties.setDateFormat("yyyy-MM-dd HH:mm:ss");
        properties.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return properties;
    }

    /**
     * 转换器全局配置
     *
     * @param converters
     * @return
     */
    @Bean
    public HttpMessageConverters httpMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        // 忽略为空的字段
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.getSerializationConfig().withFeatures(
                SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        /**
         * 序列换成json时,将所有的long变成string
         * js中long过长精度丢失
         */
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
        jackson2HttpMessageConverter.setObjectMapper(objectMapper);
        return new HttpMessageConverters(jackson2HttpMessageConverter);
    }


    /**
     * 动态路由加载
     *
     * @return
     */
    @Bean
    public JdbcRouteDefinitionLocator jdbcRouteDefinitionLocator(JdbcTemplate jdbcTemplate) {
        return new JdbcRouteDefinitionLocator(jdbcTemplate);
    }

    /**
     * 动态路由加载
     *
     * @return
     */
    @Bean
    @Lazy
    public ApiResourceLocator apiResourceLocator(RouteDefinitionLocator routeDefinitionLocator, IBaseAuthorityService baseAuthorityServiceClient, IGatewayService gatewayServiceClient) {
        return new ApiResourceLocator(routeDefinitionLocator, baseAuthorityServiceClient, gatewayServiceClient);
    }

    /**
     * 网关bus端点
     *
     * @param context
     * @param bus
     * @return
     */
    @Bean
    @ConditionalOnEnabledEndpoint
    @ConditionalOnClass({Endpoint.class})
    public ApiEndpoint openApiEndpoint(ApplicationContext context, BusProperties bus) {
        ApiEndpoint endpoint = new ApiEndpoint(context, bus.getId());
        log.info("bean [{}]", endpoint);
        return endpoint;
    }

    /*@Bean
    public KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }*/

    @Bean
    public KeyResolver pathKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getPath().value());
    }

}
