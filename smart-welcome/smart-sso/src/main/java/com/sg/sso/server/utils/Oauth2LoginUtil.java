package com.sg.sso.server.utils;

import com.alibaba.fastjson.JSONObject;
import com.sg.framework.oauth2.client.BaseOAuth2ClientProperties;
import com.sg.framework.oauth2.client.OAuth2ClientDetails;
import com.sg.framework.security.http.BaseRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * Oauth2登录工具类
 *
 * @author venn
 * @version 1.0.0
 * @date 2020/5/27
 */
@Component
public class Oauth2LoginUtil {

    @Autowired
    private BaseOAuth2ClientProperties clientProperties;

    @Autowired
    private BaseRestTemplate baseRestTemplate;

    /**
     * 第三方登录获取token
     *
     * @param userName 登录用户名
     * @param password 密码
     * @param type     登录类型
     * @param headers  请求头
     * @return 结果
     */
    public JSONObject getToken(String userName, String password, String type, HttpHeaders headers) {
        OAuth2ClientDetails clientDetails = clientProperties.getOauth2().get("admin");
        // 使用oauth2密码模式登录.
        MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
        postParameters.add("username", userName);
        postParameters.add("password", password);
        postParameters.add("client_id", clientDetails.getClientId());
        postParameters.add("client_secret", clientDetails.getClientSecret());
        postParameters.add("grant_type", "password");
        // 添加参数区分,第三方登录
        postParameters.add("login_type", type);
        // 使用客户端的请求头,发起请求
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        // 强制移除 原来的请求头,防止token失效
        headers.remove(HttpHeaders.AUTHORIZATION);
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity(postParameters, headers);

        return baseRestTemplate.postForObject(clientDetails.getAccessTokenUri(), request, JSONObject.class);
    }
}
