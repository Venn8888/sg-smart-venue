package com.sg.gateway.spring.server.exception;

import java.security.SignatureException;

import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

public interface ServerSignatureDeniedHandler {
    Mono<Void> handle(ServerWebExchange var1, SignatureException var2);
}