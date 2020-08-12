package com.sg.sso.server.enums;

/**
 * @author venn
 * @version 1.0.0
 * @date 2020/5/26
 */
public enum WeChatUrlEnum {

    /**
     * 登录凭证校验url
     */
    JS_CODE_2_SESSION("https://api.weixin.qq.com/sns/jscode2session"),
    JS_ACCESS_TOKEN("https://api.weixin.qq.com/cgi-bin/token")
    ;

    private String url;

    WeChatUrlEnum() {
    }

    WeChatUrlEnum(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public WeChatUrlEnum setUrl(String url) {
        this.url = url;
        return this;
    }
}
