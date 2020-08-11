package com.sg.gateway.oauth2;

import com.sg.common.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import reactor.core.publisher.Mono;

/**
 * 基于redis 认证授权
 *
 * @author: xxxxxxx
 * @date: 2019/5/9 10:53
 * @description:
 */
@Slf4j
public class RedisAuthenticationManager implements ReactiveAuthenticationManager {

    private TokenStore tokenStore;

    public RedisAuthenticationManager(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.justOrEmpty(authentication)
                .filter(a -> a instanceof BearerTokenAuthenticationToken)
                .cast(BearerTokenAuthenticationToken.class)
                .map(BearerTokenAuthenticationToken::getToken)
                .flatMap((token -> {
                    OAuth2Authentication oAuth2Authentication = this.tokenStore.readAuthentication(token);
                    if (oAuth2Authentication == null) {
                        return Mono.error(new InvalidTokenException(ErrorCode.INVALID_TOKEN.getMsg()));
                    } else {
                        return Mono.just(oAuth2Authentication);
                    }
                }))
                .cast(Authentication.class);
    }
}
