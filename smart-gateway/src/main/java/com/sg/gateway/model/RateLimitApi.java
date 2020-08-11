package com.sg.gateway.model;

import java.io.Serializable;

/**
 * 
 */
public class RateLimitApi implements Serializable {
    private static final long serialVersionUID = 1212925216631391016L;
    private Long itemId;
    private Long policyId;
    private String policyName;
    private Long limitQuota;
    private String intervalUnit;
    private String url;


	/**
     * 资源ID
     */
    private Long apiId;

    /**
     * 资源编码
     */
    private String apiCode;

    /**
     * 资源名称
     */
    private String apiName;

    /**
     * 服务ID
     */
    private String serviceId;

    /**
     * 接口分类
     */
    private String apiCategory;
    /**
     * 资源路径
     */
    private String path;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 资源描述
     */
    private String apiDesc;

    /**
     * 状态:0-无效 1-有效
     */
    private Integer status;

    /**
     * 保留数据0-否 1-是 不允许删除
     */
    private Integer isPersist;

    /**
     * 安全认证:0-否 1-是 默认:1
     */
    private Integer isAuth;

    /**
     * 是否公开访问: 0-内部的 1-公开的
     */
    private Integer isOpen;
    /**
     * 请求方式
     */
    private String requestMethod;
    /**
     * 响应类型
     */
    private String contentType;

    /**
     * 类名
     */
    private String className;

    /**
     * 方法名
     */
    private String methodName;
    
    public Long getApiId() {
		return apiId;
	}

	public void setApiId(Long apiId) {
		this.apiId = apiId;
	}

	public String getApiCode() {
		return apiCode;
	}

	public void setApiCode(String apiCode) {
		this.apiCode = apiCode;
	}

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getApiCategory() {
		return apiCategory;
	}

	public void setApiCategory(String apiCategory) {
		this.apiCategory = apiCategory;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getApiDesc() {
		return apiDesc;
	}

	public void setApiDesc(String apiDesc) {
		this.apiDesc = apiDesc;
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

	public Integer getIsAuth() {
		return isAuth;
	}

	public void setIsAuth(Integer isAuth) {
		this.isAuth = isAuth;
	}

	public Integer getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}

	public String getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}


    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Long policyId) {
        this.policyId = policyId;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public Long getLimitQuota() {
        return limitQuota;
    }

    public void setLimitQuota(Long limitQuota) {
        this.limitQuota = limitQuota;
    }

    public String getIntervalUnit() {
        return intervalUnit;
    }

    public void setIntervalUnit(String intervalUnit) {
        this.intervalUnit = intervalUnit;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}