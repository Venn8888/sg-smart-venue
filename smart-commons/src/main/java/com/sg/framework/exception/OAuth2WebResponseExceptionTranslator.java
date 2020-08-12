package com.sg.framework.exception;

import com.sg.api.SgResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class OAuth2WebResponseExceptionTranslator implements WebResponseExceptionTranslator {


    @Override
    public ResponseEntity translate(Exception e) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        SgResponse responseData = BaseExceptionHandler.resolveException(e, request.getRequestURI());
        return ResponseEntity.status(responseData.getHttpStatus()).body(responseData);
    }
}
