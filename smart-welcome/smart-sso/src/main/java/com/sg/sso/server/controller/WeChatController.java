package com.sg.sso.server.controller;

import com.sg.api.SgResponse;
import com.sg.sso.server.service.WeChatService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author venn
 * @version 1.0.0
 * @date 2020/5/26
 */
@RestController
public class WeChatController {


    @Autowired
    private WeChatService weChatService;

    /**
     * 获取微信用户openid及session_key
     *
     * @param code 微信登录code
     * @return 微信登录token凭证（有效期3天少10分钟）
     */
    @GetMapping("/weChat/login")
    public SgResponse<String> wxLogin(@RequestParam("code") String code) throws Exception {
        Assert.isTrue(StringUtils.isNotBlank(code), "微信登录code不能为空");
        return weChatService.wxLogin(code);
    }

    /**
     * 微信手机号登录
     *
     * @param token         微信登录返回的token
     * @param encryptedData 加密数据
     * @param iv            偏移量
     * @return oauth2登录态对象
     */
    @GetMapping("/weChat/mobile/login")
    public SgResponse<Object> wxMobileLogin(@RequestParam("token") String token,
                                            @RequestParam("encryptedData") String encryptedData,
                                            @RequestParam("iv") String iv,
                                            @RequestParam("storeId") String storeId,
                                            @RequestHeader HttpHeaders headers) throws Exception {
        Assert.isTrue(StringUtils.isNotBlank(token), "微信登录返回的token不能为空");
        Assert.isTrue(StringUtils.isNotBlank(encryptedData), "加密数据不能为空");
        Assert.isTrue(StringUtils.isNotBlank(iv), "偏移量不能为空");
        return weChatService.wxMobileLogin(token, encryptedData, iv, storeId, headers);
    }

    /**
     * 手机号+验证码登录
     *
     * @param mobile  手机号
     * @param code    验证码
     * @param headers 请求头
     * @return oauth2登录态对象
     */
    @GetMapping("/weChat/mobileAndCode/login")
    public SgResponse<Object> wxMobileAndCodeLogin(@RequestParam("mobile") String mobile,
                                                   @RequestParam("code") String code,
                                                   @RequestParam("storeId") String storeId,
                                                   @RequestHeader HttpHeaders headers) throws Exception {
        Assert.isTrue(StringUtils.isNotBlank(mobile), "手机号不能为空");
        Assert.isTrue(StringUtils.isNotBlank(code), "验证码不能为空");
        return weChatService.wxMobileAndCodeLogin(mobile, code, storeId, headers);
    }

    /**
     * 短信验证码发送接口
     *
     * @param mobile 手机号
     * @return 推送结果
     */
    @GetMapping("/weChat/mobileMsgSend")
    public SgResponse wxMobileMsgSend(@RequestParam("mobile") String mobile) throws Exception {
        Assert.isTrue(StringUtils.isNotBlank(mobile), "手机号不能为空");
        return weChatService.wxMobileMsgSend(mobile);
    }
    @GetMapping("/weChat/getAccessToken")
    public void getAccessToken() throws Exception{
        String access_token=weChatService.getAccessToken();
        System.out.println("access_token:"+access_token);
    }
}
