package com.sg.gateway.spring.server.actuator;

import com.sg.api.SgResponse;
import com.sg.framework.event.RemoteRefreshRouteEvent;
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.cloud.bus.endpoint.AbstractBusEndpoint;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 自定义网关监控端点
 * 
 */
@RestControllerEndpoint(id = "open")
public class ApiEndpoint extends AbstractBusEndpoint {
    public ApiEndpoint(ApplicationEventPublisher context, String id) {
        super(context, id);
    }

    /**
     * 支持灰度发布
     * /actuator/open/refresh?destination = customers：**
     *
     * @param destination
     */
    @PostMapping("/refresh")
    public SgResponse busRefreshWithDestination(@RequestParam(required = false)  String destination) {
        this.publish(new RemoteRefreshRouteEvent(this, this.getInstanceId(), destination));
        return SgResponse.ok(null);
    }
}
