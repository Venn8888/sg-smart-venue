package com.sg.gateway.spring.server.filter;

import com.sg.framework.base.ErrorCode;
import com.sg.gateway.model.AuthorityResource;
import com.sg.gateway.spring.server.exception.JsonAccessDeniedHandler;
import com.sg.gateway.spring.server.util.ReactiveWebUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * 访问验证前置过滤器
 *
 * 
 */
@Slf4j
public class PreCheckFilter implements WebFilter {

    private JsonAccessDeniedHandler accessDeniedHandler;

    private AccessAuthorizationManager apiAccessManager;

    public PreCheckFilter(AccessAuthorizationManager apiAccessManager, JsonAccessDeniedHandler accessDeniedHandler) {
        this.apiAccessManager = apiAccessManager;
        this.accessDeniedHandler = accessDeniedHandler;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String requestPath = request.getURI().getPath();
        String remoteIpAddress = ReactiveWebUtil.getRemoteAddress(exchange);
        String origin = request.getHeaders().getOrigin();
        AuthorityResource resource = apiAccessManager.getResource(requestPath);
        if (resource != null) {
            if ("0".equals(resource.getIsOpen().toString())) {
                // 未公开
                return accessDeniedHandler.handle(exchange, new AccessDeniedException(ErrorCode.ACCESS_DENIED_NOT_OPEN.getMsg()));
            }
            if ("0".equals(resource.getStatus().toString())) {
                // 禁用
                return accessDeniedHandler.handle(exchange, new AccessDeniedException(ErrorCode.ACCESS_DENIED_DISABLED.getMsg()));
            } else if ("2".equals(resource.getStatus().toString())) {
                // 维护中
                return accessDeniedHandler.handle(exchange, new AccessDeniedException(ErrorCode.ACCESS_DENIED_UPDATING.getMsg()));
            }
        }
        // 1.ip黑名单检测
        boolean deny = apiAccessManager.matchIpOrOriginBlacklist(requestPath, remoteIpAddress,origin);
        if (deny) {
            // 拒绝
            return accessDeniedHandler.handle(exchange, new AccessDeniedException(ErrorCode.ACCESS_DENIED_BLACK_LIMITED.getMsg()));
        }

        // 3.ip白名单检测
        Boolean[] matchIpWhiteListResult = apiAccessManager.matchIpOrOriginWhiteList(requestPath, remoteIpAddress,origin);
        boolean hasWhiteList = matchIpWhiteListResult[0];
        boolean allow = matchIpWhiteListResult[1];
        if (hasWhiteList) {
            // 接口存在白名单限制
            if (!allow) {
                // IP白名单检测通过,拒绝
                return accessDeniedHandler.handle(exchange, new AccessDeniedException(ErrorCode.ACCESS_DENIED_WHITE_LIMITED.getMsg()));
            }
        }
        return chain.filter(exchange);
    }
}
