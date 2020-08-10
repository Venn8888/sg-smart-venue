package com.sg.gateway.filter;

import com.google.common.collect.Maps;
import com.sg.common.constants.Constants;
import com.sg.gateway.autoconfigure.ApiProperties;
import com.sg.gateway.exception.JsonSignatureDeniedHandler;
import com.sg.gateway.model.BaseApp;
import com.sg.gateway.service.IBaseAppService;
import com.sg.gateway.util.SignatureUtil;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.support.WebExchangeDataBinder;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.security.SignatureException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 数字验签前置过滤器
 *
 * @author: xxxxxxx
 * @date: 2018/11/28 18:26
 * @description:
 */
public class PreSignatureFilter implements WebFilter {
    private JsonSignatureDeniedHandler signatureDeniedHandler;
    private IBaseAppService baseAppService;
    private ApiProperties apiGatewayProperties;
    private static final AntPathMatcher pathMatch = new AntPathMatcher();
    /**
     * 忽略签名
     */
    private final static List<String> NOT_SIGN = getIgnoreMatchers(
            "/**/login/**",
            "/**/logout/**"
    );

    public PreSignatureFilter(IBaseAppService baseAppServiceClient, ApiProperties apiGatewayProperties, JsonSignatureDeniedHandler signatureDeniedHandler) {
        this.baseAppService = baseAppServiceClient;
        this.apiGatewayProperties = apiGatewayProperties;
        this.signatureDeniedHandler = signatureDeniedHandler;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String requestPath = request.getURI().getPath();
        if (apiGatewayProperties.getCheckSign() && !notSign(requestPath)) {
            try {
                Map params = Maps.newHashMap();
                WebExchangeDataBinder.extractValuesToBind(exchange).subscribe(objectMap -> {
                            params.putAll(objectMap);
                        }
                );
                // 验证请求参数
                SignatureUtil.validateParams(params);
                //开始验证签名
                if (baseAppService != null) {
                    String appId = params.get("appId").toString();
                    // 获取客户端信息
                    BaseApp app = baseAppService.getApp(appId);
                    if (app == null) {
                        return signatureDeniedHandler.handle(exchange, new SignatureException("appId无效"));
                    }
                    // 强制覆盖请求参数clientId
                    params.put(Constants.SIGN_APP_ID_KEY, app.getAppId());
                    // 服务器验证签名结果
                    if (!SignatureUtil.validateSign(params, app.getSecretKey())) {
                        return signatureDeniedHandler.handle(exchange, new SignatureException("签名验证失败!"));
                    }
                }
            } catch (Exception ex) {
                return signatureDeniedHandler.handle(exchange, new SignatureException(ex.getMessage()));
            }
        }
        return chain.filter(exchange);
    }


    protected static List<String> getIgnoreMatchers(String... antPatterns) {
        List<String> matchers = new CopyOnWriteArrayList();
        for (String path : antPatterns) {
            matchers.add(path);
        }
        return matchers;
    }

    protected boolean notSign(String requestPath) {
        for (String path : NOT_SIGN) {
            if (pathMatch.match(path, requestPath)) {
                return true;
            }
        }
        return false;
    }
}
