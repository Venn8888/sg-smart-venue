package com.sg.sso.server.service;

import com.sg.api.SgResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author venn
 * @version 1.0.0
 * @date 2020/5/26
 */
public interface WeChatService {

    /**
     * 通过微信登录code获取token
     *
     * @param code 微信登录code
     * @return token
     * @throws Exception
     */
    SgResponse<String> wxLogin(String code) throws Exception;

    /**
     * 通过微信手机号登录
     *
     * @param token         微信登录接口返回的token
     * @param encryptedData 加密数据
     * @param iv            偏移量
     * @param headers       请求头数据
     * @return oath2登录token
     * @throws Exception
     */
    SgResponse<Object> wxMobileLogin(String token, String encryptedData, String iv,String storeId, HttpHeaders headers) throws Exception;

    /**
     * 手机号+验证码登录
     *
     * @param mobile  手机号
     * @param code    验证码
     * @param headers 请字头
     * @return oath2登录token
     * @throws Exception
     */
    SgResponse<Object> wxMobileAndCodeLogin(String mobile, String code,String storeId, HttpHeaders headers) throws Exception;

    /**
     * 手机验证码推送
     *
     * @param mobile 手机号
     * @return 发送结果
     */
    SgResponse wxMobileMsgSend(@RequestParam("mobile") String mobile) throws Exception;

    /**
     * 获取微信后台access_token
     * @return
     */
    public String getAccessToken() throws Exception;
}
