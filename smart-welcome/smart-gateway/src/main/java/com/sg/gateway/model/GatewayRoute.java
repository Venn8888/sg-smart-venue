package com.sg.gateway.model;

import lombok.Data;

/**
 * 网关动态路由
 *
 * @author: xxxxxxx
 * @date: 2018/10/24 16:21
 * @description:
 */
@Data
public class GatewayRoute {
    private static final long serialVersionUID = -2952097064941740301L;

    /**
     * 路由ID
     */
    private Long routeId;

    /**
     * 路由名称
     */
    private String routeName;

    /**
     * 路径
     */
    private String path;

    /**
     * 服务ID
     */
    private String serviceId;

    /**
     * 完整地址
     */
    private String url;

    /**
     * 忽略前缀
     */
    private Integer stripPrefix;

    /**
     * 0-不重试 1-重试
     */
    private Integer retryable;

    /**
     * 状态:0-无效 1-有效
     */
    private String status;

    /**
     * 保留数据0-否 1-是 不允许删除
     */
    private Integer isPersist;

    /**
     * 路由说明
     */
    private String routeDesc;

}
