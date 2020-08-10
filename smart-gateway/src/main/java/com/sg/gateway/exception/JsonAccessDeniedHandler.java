package com.sg.gateway.exception;

import com.alibaba.fastjson.JSONObject;
import com.sg.common.api.SgResponse;
import com.sg.common.exception.BaseExceptionHandler;
import com.sg.gateway.service.impl.AccessLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;

/**
 * 网关权限异常处理,记录日志
 * 
 */
@Slf4j
public class JsonAccessDeniedHandler implements ServerAccessDeniedHandler {
  private AccessLogService accessLogService;

    public JsonAccessDeniedHandler(AccessLogService accessLogService) {
        this.accessLogService = accessLogService;
   }

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException e) {
        SgResponse resultBody = BaseExceptionHandler.resolveException(e,exchange.getRequest().getURI().getPath());
        return Mono.defer(() -> {
            return Mono.just(exchange.getResponse());
        }).flatMap((response) -> {
            response.setStatusCode(HttpStatus.valueOf(resultBody.getHttpStatus()));
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            DataBufferFactory dataBufferFactory = response.bufferFactory();
            DataBuffer buffer = dataBufferFactory.wrap(JSONObject.toJSONString(resultBody).getBytes(Charset.defaultCharset()));
            // 保存日志
            accessLogService.sendLog(exchange,e);
            return response.writeWith(Mono.just(buffer)).doOnError((error) -> {
                DataBufferUtils.release(buffer);
            });
        });
    }
}