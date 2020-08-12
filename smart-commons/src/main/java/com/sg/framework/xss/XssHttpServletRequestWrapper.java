package com.sg.framework.xss;


import java.io.IOException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.sg.framework.util.StringUtil;

/**
 * 
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private HttpServletRequest request;

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        this.request = request;
    }

    
    public String getParameter(String name) {
        String value = request.getParameter(name);
        if (!StringUtil.isEmpty(value)) {
            value = StringUtil.stripXss(value).trim();
        }
        return value;
    }

    
    public String[] getParameterValues(String name) {
        String[] parameterValues = super.getParameterValues(name);
        if (parameterValues == null) {
            return null;
        }
        for (int i = 0; i < parameterValues.length; i++) {
            String value = parameterValues[i];
            parameterValues[i] = StringUtil.stripXss(value).trim();
        }
        return parameterValues;
    }

    
    public ServletInputStream getInputStream() throws IOException {
        return super.getInputStream();
    }
} 
