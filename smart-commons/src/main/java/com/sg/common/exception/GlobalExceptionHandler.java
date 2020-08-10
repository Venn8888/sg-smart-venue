package com.sg.common.exception;

import com.sg.common.api.SgResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 统一异常处理器
 *
 * @author LYD
 * @date 2017/7/3
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {


    /**
     * 统一异常处理
     * AuthenticationException
     *
     * @param ex
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler({AuthenticationException.class})
    public static SgResponse authenticationException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        SgResponse resultBody = BaseExceptionHandler.resolveException(ex, request.getRequestURI());
        return resultBody;
    }

    /**
     * OAuth2Exception
     *
     * @param ex
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler({OAuth2Exception.class, InvalidTokenException.class})
    public static SgResponse oauth2Exception(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        SgResponse resultBody = BaseExceptionHandler.resolveException(ex, request.getRequestURI());
        return resultBody;
    }

    /**
     * 自定义异常
     *
     * @param ex
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler({BaseException.class})
    public static SgResponse openException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        SgResponse resultBody = BaseExceptionHandler.resolveException(ex, request.getRequestURI());
        return resultBody;
    }

    /**
     * 其他异常
     *
     * @param ex
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler({Exception.class})
    public static SgResponse exception(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        SgResponse resultBody = BaseExceptionHandler.resolveException(ex, request.getRequestURI());
        return resultBody;
    }

}
