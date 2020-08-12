package com.sg.framework.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 自定义oauth2异常提示
 * 
 */
@JsonSerialize(using = BaseOAuth2ExceptionSerializer.class)
public class BaseOAuth2Exception extends org.springframework.security.oauth2.common.exceptions.OAuth2Exception {
    private static final long serialVersionUID = 4257807899611076101L;

    public BaseOAuth2Exception(String msg) {
        super(msg);
    }
}