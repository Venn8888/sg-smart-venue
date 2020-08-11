package com.sg.gateway.filter;

import cn.hutool.core.collection.ConcurrentHashSet;
import com.sg.common.constants.Constants;
import com.sg.common.exception.ErrorCode;
import com.sg.common.security.BaseAuthority;
import com.sg.common.utils.StringUtil;
import com.sg.gateway.autoconfigure.ApiProperties;
import com.sg.gateway.util.matcher.ReactiveIpAddressMatcher;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * @author venn
 * @version 1.0.0
 * @date 2020/8/10
 */
@Slf4j
@Component
public class AccessAuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {


    @Autowired
    private ApiProperties apiProperties;

    private static final AntPathMatcher pathMatch = new AntPathMatcher();

    private Set<String> permitAll = new ConcurrentHashSet<>();

    private Set<String> authorityIgnores = new ConcurrentHashSet<>();


    public AccessAuthorizationManager(ApiProperties apiProperties) {
        this.apiProperties = apiProperties;
        // 默认放行
        permitAll.add("/");
        permitAll.add("/error");
        permitAll.add("/favicon.ico");
        if (apiProperties != null) {
            if (apiProperties.getPermitAll() != null) {
                permitAll.addAll(apiProperties.getPermitAll());
            }
            if (apiProperties.getApiDebug()) {
                permitAll.add("/**/v2/api-docs/**");
                permitAll.add("/**/swagger-resources/**");
                permitAll.add("/webjars/**");
                permitAll.add("/doc.html");
                permitAll.add("/swagger-ui.html");
            }
            if (apiProperties.getAuthorityIgnores() != null) {
                authorityIgnores.addAll(apiProperties.getAuthorityIgnores());
            }
        }
    }


    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext authorizationContext) {
        ServerWebExchange exchange = authorizationContext.getExchange();
        String requestPath = exchange.getRequest().getURI().getPath();
        if (!apiProperties.getAccessControl()) {
            return Mono.just(new AuthorizationDecision(true));
        }
        // 是否直接放行
        if (permitAll(requestPath)) {
            return Mono.just(new AuthorizationDecision(true));
        }
        return authentication.map(a -> new AuthorizationDecision(checkAuthorities(exchange, a, requestPath))).defaultIfEmpty(new AuthorizationDecision(false));
    }

    /**
     * 始终放行
     *
     * @param requestPath
     * @return
     */
    public boolean permitAll(String requestPath) {
        final Boolean[] result = {false};
        for (String path : permitAll) {
            if (pathMatch.match(path, requestPath)) {
                return true;
            }
        }

        return result[0];
    }


    /**
     * 忽略鉴权
     *
     * @param requestPath
     * @return
     */
    private boolean authorityIgnores(String requestPath) {
        Iterator<String> it = authorityIgnores.iterator();
        while (it.hasNext()) {
            String path = it.next();
            if (pathMatch.match(path, requestPath)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检查权限
     *
     * @param exchange
     * @param authentication
     * @param requestPath
     * @return
     */
    private boolean checkAuthorities(ServerWebExchange exchange, Authentication authentication, String requestPath) {
        Object principal = authentication.getPrincipal();
        // 已认证身份
        if (principal != null) {
            if (authentication instanceof AnonymousAuthenticationToken) {
                //check if this uri can be access by anonymous
                //return
            }
            if (authorityIgnores(requestPath)) {
                // 认证通过,并且无需权限
                return true;
            }
            return mathAuthorities(exchange, authentication, requestPath);
        }
        return false;
    }

    public boolean mathAuthorities(ServerWebExchange exchange, Authentication authentication, String requestPath) {
        Collection<ConfigAttribute> attributes = getAttributes(requestPath);
        int result = 0;
        int expires = 0;
        if (authentication == null) {
            return false;
        } else {
            if (Constants.UserType.SUPPER.equals(authentication.getName())) {
                // 默认超级管理员账号,直接放行
                return true;
            }
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            Iterator var6 = attributes.iterator();
            while (var6.hasNext()) {
                ConfigAttribute attribute = (ConfigAttribute) var6.next();
                Iterator var8 = authorities.iterator();
                while (var8.hasNext()) {
                    GrantedAuthority authority = (GrantedAuthority) var8.next();
                    if (attribute.getAttribute().equals(authority.getAuthority())) {
                        result++;
                        if (authority instanceof BaseAuthority) {
                            BaseAuthority customer = (BaseAuthority) authority;
                            customer.getIsExpired();
                            if (customer.getIsExpired()) {
                                // 授权过期数
                                expires++;
                            }
                        }
                    }
                }
            }
            log.debug("mathAuthorities result[{}] expires[{}]", result, expires);
            if (expires > 0) {
                // 授权已过期
                throw new AccessDeniedException(ErrorCode.ACCESS_DENIED_AUTHORITY_EXPIRED.getMsg());
            }
            return result > 0;
        }
    }

    private Collection<ConfigAttribute> getAttributes(String requestPath) {
        // 匹配动态权限
        return SecurityConfig.createList("AUTHORITIES_REQUIRED");
    }



    /**
     * 匹配IP或域名
     *
     * @param values
     * @param ipAddress
     * @param origin
     * @return
     */
    public boolean matchIpOrOrigin(Set<String> values, String ipAddress, String origin) {
        ReactiveIpAddressMatcher ipAddressMatcher = null;
        for (String value : values) {
            if (StringUtil.matchIp(value)) {
                ipAddressMatcher = new ReactiveIpAddressMatcher(value);
                if (ipAddressMatcher.matches(ipAddress)) {
                    return true;
                }
            } else {
                if (StringUtil.matchDomain(value) && StringUtils.isNotBlank(origin) && origin.contains(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    public ApiProperties getApiProperties() {
        return apiProperties;
    }

    public void setApiProperties(ApiProperties apiProperties) {
        this.apiProperties = apiProperties;
    }
}
