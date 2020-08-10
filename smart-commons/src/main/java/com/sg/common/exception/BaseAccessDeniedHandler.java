package com.sg.common.exception;

import com.sg.common.api.SgResponse;
import com.sg.common.utils.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义访问拒绝
 * 
 */
@Slf4j
public class BaseAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException exception) throws IOException, ServletException {
        SgResponse resultBody = BaseExceptionHandler.resolveException(exception,request.getRequestURI());
        WebUtil.writeJson(response, resultBody);
    }
}
