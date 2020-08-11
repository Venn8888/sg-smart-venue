package com.sg.gateway.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 网关属性配置类
 *
 * @author: xxxxxxx
 * @date: 2018/11/23 14:40
 * @description:
 */
@ConfigurationProperties(prefix = "application.api")
@Component
@Data
public class ApiProperties {
    /**
     * 是否开启签名验证
     */
    private Boolean checkSign = true;
    /**
     * 是否开启动态访问控制
     */
    private Boolean accessControl = true;

    /**
     * 是否开启接口调试
     */
    private Boolean apiDebug = false;

    /**
     * 始终放行
     */
    private Set<String> permitAll;

    /**
     * 无需鉴权的请求
     */
    private Set<String> authorityIgnores;
}
