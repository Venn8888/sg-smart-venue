package com.sg.gateway.server.exception;

import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.security.SignatureException;

public interface ServerSignatureDeniedHandler {
    Mono<Void> handle(ServerWebExchange var1, SignatureException var2);
}