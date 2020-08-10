package com.sg.common.constants;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "application.common")
@Data
public class AppConstants {
	/**
	 * 上传路径
	 */
	private String upload;
	/**
	 * 下载路径
	 */
	private String download;
	/**
	 * 是否缓存
	 */
	private String cache;
	
    /**
     * 网关客户端Id
     */
    private String clientId;
    /**
     * 网关客户端密钥
     */
    private String clientSecret;
    /**
     * 网关服务地址
     */
    private String apiServerAddr;

    /**
     * 平台认证服务地址
     */
    private String authServerAddr;

    /**
     * 后台部署地址
     */
    private String adminServerAddr;

    /**
     * 认证范围
     */
    private String scope;
    /**
     * 获取token
     */
    private String accessTokenUri;
    /**
     * 认证地址
     */
    private String userAuthorizationUri;
    /**
     * 获取token地址
     */
    private String tokenInfoUri;
    /**
     * 获取用户信息地址
     */
    private String userInfoUri;

    /**
     * jwt签名key
     */
    private String jwtSigningKey;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OpenCommonProperties{");
        sb.append("clientId='").append(clientId).append('\'');
        sb.append(", clientSecret='").append(clientSecret).append('\'');
        sb.append(", apiServerAddr='").append(apiServerAddr).append('\'');
        sb.append(", authServerAddr='").append(authServerAddr).append('\'');
        sb.append(", adminServerAddr='").append(adminServerAddr).append('\'');
        sb.append(", scope='").append(scope).append('\'');
        sb.append(", accessTokenUri='").append(accessTokenUri).append('\'');
        sb.append(", userAuthorizationUri='").append(userAuthorizationUri).append('\'');
        sb.append(", tokenInfoUri='").append(tokenInfoUri).append('\'');
        sb.append(", userInfoUri='").append(userInfoUri).append('\'');
        sb.append(", jwtSigningKey='").append(jwtSigningKey).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
