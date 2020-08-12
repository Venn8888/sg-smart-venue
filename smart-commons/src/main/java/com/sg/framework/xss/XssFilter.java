package com.sg.framework.xss;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Loger
 */
public class XssFilter implements Filter {

    
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        XssHttpServletRequestWrapper xssRequestWrapper = new XssHttpServletRequestWrapper(req);
        chain.doFilter(xssRequestWrapper, response);
    }

    
    public void destroy() {

    }


}