package com.sg.sso.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.sg.api.SgResponse;
import com.sg.framework.security.SecurityHelper;
import com.sg.sso.server.domain.LoginRequest;
import com.sg.sso.server.utils.Oauth2LoginUtil;
import com.sg.utils.RsaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @author:
 * @date:
 * @description:
 */
@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    private Oauth2LoginUtil oauth2LoginUtil;
    @Autowired
    private TokenStore tokenStore;

    private static final String PRI_KEY = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAKjjKB3xYZaZItzU\n" +
            "nSnA2vVlNLHU3dlsW7Tb4UjdlQjIfnb/QeppJX1xQnNPT7aOghMXAQYz7qTz6AUK\n" +
            "2xNezdpE5bdvTn1qV1p4k+ZL6rBH35XO3mGUhSH/7Y5bV1YTXKzxODnKpbphOmqN\n" +
            "NtIoEO7M/Idfrexo19vItdLsBRp/AgMBAAECgYB6Eu+tbQt3+qrLaldLld3u4L+N\n" +
            "ButwvDUxbnnR3HBcT+exheIUWPpDSPmaNarNrwZwXAFehW0rCC4ebvFZd/5caTKd\n" +
            "7FutwyzfX2rZEQFlxkH9jmOGtHOxILQLg//Sf6t8LfUaexMYQ4/9uedaUM12mYGS\n" +
            "3KP8PO0dNsVpNxPYUQJBANH8yMR/hHQ3b5DxiyznwIgxfkc5UR3y6nJp/WSUudcx\n" +
            "WvM6vywTT1EQ0wN7hUAK67kwaJ9qJB7eIwWPiIE09qkCQQDN5N4i1Nc8Iy3CDBG+\n" +
            "j6PhujsLPELYnzX6YS2wfl0uocTuPa2ME2nX+WFFJatLXR/vh3a8HuDKt3XvaV34\n" +
            "40jnAkEAm9qlIMMMij2C/u9oPwIbsnoZob9252WegSENw5Nng8LMIG9f6NTEMATu\n" +
            "uUz6j5gLcT3uyTM8b/SrxbrMtCmdUQJBAJ3pYtWkGE/vSzEAwLs+v5poktBTL5Gk\n" +
            "yml2DuzGfgE94SQ3+drtKMiUY538/huXGN9FLjOqGw5fFvy/+2rgJxECQQDGTb9O\n" +
            "utucYLlW+l/H8Ulx5ScQ8E6BaZzNTLUe/g9+dEnKfxEcR/BM4zuriD+wUwFiIorJ\n" +
            "44ByxpwdZ4Ld5y9q";

    /**
     * 获取用户基础信息
     *
     * @return
     */
    @GetMapping("/current/user")
    public SgResponse getUserProfile() {
        return SgResponse.ok(SecurityHelper.getUser());
    }

    /**
     * 获取当前登录用户信息-SSO单点登录
     *
     * @param principal
     * @return
     */
    @GetMapping("/current/principal")
    public Principal principal(Principal principal) {
        return principal;
    }

    /**
     * 获取用户访问令牌 基于oauth2密码模式登录
     *
     * @return access_token
     */
    @PostMapping("/login/token")
    public Object getLoginToken(@RequestBody LoginRequest request, @RequestHeader HttpHeaders headers) {

        String password = RsaUtil.decode(PRI_KEY, request.getPassword());
        String username = request.getUsername();

        JSONObject result = oauth2LoginUtil.getToken(username, password, "password", headers);
        if (result.containsKey("access_token")) {
            return SgResponse.ok(result);
        } else {
            return result;
        }
    }

    /**
     * 退出移除令牌
     *
     * @param token
     */
    @PostMapping("/logout/token")
    public SgResponse removeToken(@RequestParam String token) {
        tokenStore.removeAccessToken(tokenStore.readAccessToken(token));
        return SgResponse.ok(null);
    }
}
