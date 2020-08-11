package com.sg.gateway.model;

/**
 * 网关动态路由
 *
 * @author: xxxxxxx
 * @date: 2018/10/24 16:21
 * @description:
 */
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
    private Integer status;

    /**
     * 保留数据0-否 1-是 不允许删除
     */
    private Integer isPersist;

    /**
     * 路由说明
     */
    private String routeDesc;

	public Long getRouteId() {
		return routeId;
	}

	public void setRouteId(Long routeId) {
		this.routeId = routeId;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getStripPrefix() {
		return stripPrefix;
	}

	public void setStripPrefix(Integer stripPrefix) {
		this.stripPrefix = stripPrefix;
	}

	public Integer getRetryable() {
		return retryable;
	}

	public void setRetryable(Integer retryable) {
		this.retryable = retryable;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsPersist() {
		return isPersist;
	}

	public void setIsPersist(Integer isPersist) {
		this.isPersist = isPersist;
	}

	public String getRouteDesc() {
		return routeDesc;
	}

	public void setRouteDesc(String routeDesc) {
		this.routeDesc = routeDesc;
	}
}
