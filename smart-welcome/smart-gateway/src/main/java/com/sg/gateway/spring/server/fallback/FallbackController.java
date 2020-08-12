package com.sg.gateway.spring.server.fallback;

import com.sg.api.SgResponse;
import com.sg.framework.base.ErrorCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * 响应超时熔断处理器
 *
 * 
 */
@RestController
public class FallbackController {

    @RequestMapping("/fallback")
    public Mono<SgResponse> fallback() {
        return Mono.just(SgResponse.failed(ErrorCode.GATEWAY_TIMEOUT));
    }
}
