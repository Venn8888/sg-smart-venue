package com.sg.common.exception;

import com.sg.common.api.SgResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class OAuth2WebResponseExceptionTranslator implements WebResponseExceptionTranslator {

    
    @Override
    public ResponseEntity translate(Exception e) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        SgResponse responseData = BaseExceptionHandler.resolveOauthException(e,request.getRequestURI());
        return ResponseEntity.status(200).body(responseData);
    }
}
