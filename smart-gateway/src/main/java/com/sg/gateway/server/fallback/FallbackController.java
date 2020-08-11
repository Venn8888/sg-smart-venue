package com.sg.gateway.server.fallback;

import com.sg.common.api.SgResponse;
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
        return Mono.just(SgResponse.failed("访问超时，请稍后再试!"));
    }
}
