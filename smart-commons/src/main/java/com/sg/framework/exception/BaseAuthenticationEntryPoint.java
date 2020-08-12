package com.sg.framework.exception;

import com.sg.api.SgResponse;
import com.sg.framework.util.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义未认证处理
 *
 * 
 */
@Slf4j
public class BaseAuthenticationEntryPoint implements AuthenticationEntryPoint {

    
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException exception) throws IOException, ServletException {
        SgResponse resultBody = BaseExceptionHandler.resolveException(exception,request.getRequestURI());
        response.setStatus(resultBody.getHttpStatus());
        WebUtil.writeJson(response, resultBody);
    }
}