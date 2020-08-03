package com.sg.sso.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.*;

/**
 * @author venn
 * @version 1.0.0
 * @date 2020/8/3
 */
public class WebUtil {
    private static WebApplicationContext webApplicationContext;
    private static final String staticSuffix = ".css,.js,.png,.jpg,.gif,.jpeg,.bmp,.ico,.swf,.psd,.htc,.htm,.html,.crx,.xpi,.exe,.ipa,.apk,.woff2,.ico,.swf,.ttf,.otf,.svg,.woff";
    private static final String[] staticFiles = StringUtils.split(".css,.js,.png,.jpg,.gif,.jpeg,.bmp,.ico,.swf,.psd,.htc,.htm,.html,.crx,.xpi,.exe,.ipa,.apk,.woff2,.ico,.swf,.ttf,.otf,.svg,.woff", ",");
    private static final String urlSuffix = ".html";

    public WebUtil() {
    }

    public static String[] getStaticFiles() {
        return staticFiles;
    }

    public static void setCookie(HttpServletResponse response, String name, String value) {
        setCookie(response, name, value, 86400);
    }

    public static void setCookie(HttpServletResponse response, String name, String value, String path) {
        setCookie(response, name, value, path, 86400);
    }

    public static void setCookie(HttpServletResponse response, String name, String value, int maxAge) {
        setCookie(response, name, value, "/", maxAge);
    }

    public static void setCookie(HttpServletResponse response, String name, String value, String path, int maxAge) {
        Cookie cookie = new Cookie(name, (String)null);
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);

        try {
            cookie.setValue(URLEncoder.encode(value, "utf-8"));
        } catch (UnsupportedEncodingException var7) {
            var7.printStackTrace();
        }

        response.addCookie(cookie);
    }

    public static void removeCookie(HttpServletResponse response, String name) {
        Cookie cookie = new Cookie(name, (String)null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        cookie.setValue((String)null);
        response.addCookie(cookie);
    }

    public static String getCookie(HttpServletRequest request, String name) {
        return getCookie(request, (HttpServletResponse)null, name, false);
    }

    public static String getCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        return getCookie(request, response, name, true);
    }

    public static String getCookie(HttpServletRequest request, HttpServletResponse response, String name, boolean isRemove) {
        String value = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Cookie[] var6 = cookies;
            int var7 = cookies.length;

            for(int var8 = 0; var8 < var7; ++var8) {
                Cookie cookie = var6[var8];
                if (cookie.getName().equals(name)) {
                    try {
                        value = URLDecoder.decode(cookie.getValue(), "utf-8");
                    } catch (UnsupportedEncodingException var11) {
                        var11.printStackTrace();
                    }

                    if (isRemove) {
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                }
            }
        }

        return value;
    }

    public static void setExpiresHeader(HttpServletResponse response, long expiresSeconds) {
        response.setDateHeader("Expires", System.currentTimeMillis() + expiresSeconds * 1000L);
        response.setHeader("Cache-Control", "private, max-age=" + expiresSeconds);
    }

    public static void setNoCacheHeader(HttpServletResponse response) {
        response.setDateHeader("Expires", 1L);
        response.addHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache, no-store, max-age=0");
    }

    public static void setLastModifiedHeader(HttpServletResponse response, long lastModifiedDate) {
        response.setDateHeader("Last-Modified", lastModifiedDate);
    }

    public static void setEtag(HttpServletResponse response, String etag) {
        response.setHeader("ETag", etag);
    }

    public static boolean checkIfModifiedSince(HttpServletRequest request, HttpServletResponse response, long lastModified) {
        long ifModifiedSince = request.getDateHeader("If-Modified-Since");
        if (ifModifiedSince != -1L && lastModified < ifModifiedSince + 1000L) {
            response.setStatus(304);
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkIfNoneMatchEtag(HttpServletRequest request, HttpServletResponse response, String etag) {
        String headerValue = request.getHeader("If-None-Match");
        if (headerValue != null) {
            boolean conditionSatisfied = false;
            if (!"*".equals(headerValue)) {
                StringTokenizer commaTokenizer = new StringTokenizer(headerValue, ",");

                while(!conditionSatisfied && commaTokenizer.hasMoreTokens()) {
                    String currentToken = commaTokenizer.nextToken();
                    if (currentToken.trim().equals(etag)) {
                        conditionSatisfied = true;
                    }
                }
            } else {
                conditionSatisfied = true;
            }

            if (conditionSatisfied) {
                response.setStatus(304);
                response.setHeader("ETag", etag);
                return false;
            }
        }

        return true;
    }

    public static void setFileDownloadHeader(HttpServletResponse response, String fileName) {
        try {
            String encodedfileName = new String(fileName.getBytes(), "ISO8859-1");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedfileName + "\"");
        } catch (UnsupportedEncodingException var3) {
            var3.getMessage();
        }

    }

    public static Map<String, Object> getParametersWith(ServletRequest request, String prefix) {
        Assert.notNull(request, "Request must not be null");
        Enumeration paramNames = request.getParameterNames();
        Map<String, Object> params = new TreeMap();
        String pre = prefix;
        if (prefix == null) {
            pre = "";
        }

        while(paramNames != null && paramNames.hasMoreElements()) {
            String paramName = (String)paramNames.nextElement();
            if ("".equals(pre) || paramName.startsWith(pre)) {
                String unprefixed = paramName.substring(pre.length());
                String[] values = request.getParameterValues(paramName);
                if (values != null && values.length != 0) {
                    if (values.length > 1) {
                        params.put(unprefixed, values);
                    } else {
                        params.put(unprefixed, values[0]);
                    }
                } else {
                    values = new String[0];
                }
            }
        }

        return params;
    }

    public static String getBodyString(ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;

        try {
            inputStream = cloneInputStream(request.getInputStream());
            reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line = "";

            while((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException var17) {
            var17.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException var16) {
                    var16.printStackTrace();
                }
            }

            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException var15) {
                    var15.printStackTrace();
                }
            }

        }

        return sb.toString();
    }

    public static InputStream cloneInputStream(ServletInputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];

        try {
            int len;
            while((len = inputStream.read(buffer)) > -1) {
                byteArrayOutputStream.write(buffer, 0, len);
            }

            byteArrayOutputStream.flush();
        } catch (IOException var5) {
            var5.printStackTrace();
        }

        InputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        return byteArrayInputStream;
    }

    public static Map<String, String> getParameterMap(HttpServletRequest request) {
        String contentType = request.getHeader("Content-Type");
        Map<String, String> returnMap = new HashMap();
        if (!"application/json".equals(contentType) && !"application/json;charset=UTF-8".equals(contentType)) {
            if ("application/x-www-form-urlencoded".equals(contentType)) {
                Map properties = request.getParameterMap();
                Iterator entries = properties.entrySet().iterator();
                String name = "";

                for(String value = ""; entries.hasNext(); ((Map)returnMap).put(name, value)) {
                    Map.Entry entry = (Map.Entry)entries.next();
                    name = (String)entry.getKey();
                    Object valueObj = entry.getValue();
                    if (null == valueObj) {
                        value = "";
                    } else if (!(valueObj instanceof String[])) {
                        value = valueObj.toString();
                    } else {
                        String[] values = (String[])((String[])valueObj);

                        for(int i = 0; i < values.length; ++i) {
                            value = values[i] + ",";
                        }

                        value = value.substring(0, value.length() - 1);
                    }
                }
            }
        } else {
            String body = getBodyString(request);
            if (StringUtils.isNotBlank(body)) {
                try {
                    returnMap = (Map) JSONObject.parseObject(body, Map.class);
                } catch (Exception var11) {
                }
            }
        }

        return (Map)returnMap;
    }

    public static String encodeParameterWithPrefix(Map<String, Object> params, String prefix) {
        StringBuilder queryStringBuilder = new StringBuilder();
        String pre = prefix;
        if (prefix == null) {
            pre = "";
        }

        Iterator it = params.entrySet().iterator();

        while(it.hasNext()) {
            Map.Entry<String, Object> entry = (Map.Entry)it.next();
            queryStringBuilder.append(pre).append((String)entry.getKey()).append("=").append(entry.getValue());
            if (it.hasNext()) {
                queryStringBuilder.append("&");
            }
        }

        return queryStringBuilder.toString();
    }

    public static String encodeHttpBasic(String userName, String password) {
        String encode = userName + ":" + password;
        return "Basic " + EncodeUtil.encodeBase64(encode.getBytes());
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        return request.getHeader("X-Requested-With") != null && "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()) || request.getHeader("Content-Type") != null && request.getHeader("Content-Type").startsWith("application/json");
    }

    public static String getHost(HttpServletRequest request) {
        String unknown = "unknown";
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }

        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }

        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }

        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if (ip != null && ip.length() > 0) {
            String[] ips = ip.split(",");
            if (ips.length > 0) {
                ip = ips[0];
            }
        }

        return ip;
    }

    public static boolean isStaticFile(String uri) {
        return StringUtils.endsWithAny(uri, staticFiles) && !StringUtils.endsWithAny(uri, new String[]{".html"}) && !StringUtils.endsWithAny(uri, new String[]{".jsp"}) && !StringUtils.endsWithAny(uri, new String[]{".java"});
    }

    public static void writeJson(HttpServletResponse response, Object object) {
        writeJson(response, JSON.toJSONString(object), "application/json;charset=UTF-8");
    }

    public static void writeJson(HttpServletResponse response, String string, String type) {
        try {
            response.setContentType(type);
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException var4) {
        }

    }

    public static String getServerUrl(HttpServletRequest request) {
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        return url;
    }

    public static String getContextPath(HttpServletRequest request) {
        return request.getContextPath();
    }

    public static HttpServletRequest getHttpServletRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception var1) {
            return null;
        }
    }

    public static Map<String, String> getHttpHeaders(HttpServletRequest request) {
        Map<String, String> map = new LinkedHashMap();
        if (request != null) {
            Enumeration<String> enumeration = request.getHeaderNames();
            if (enumeration != null) {
                while(enumeration.hasMoreElements()) {
                    String key = (String)enumeration.nextElement();
                    String value = request.getHeader(key);
                    map.put(key, value);
                }
            }
        }

        return map;
    }

    public static String getServerUrl() {
        HttpServletRequest request = getRequest();
        String serverName = request.getScheme() + "://" + request.getServerName();
        if (request.getServerPort() != 80) {
            serverName = serverName + ":" + request.getServerPort();
        }

        if (StringUtils.isNotEmpty(request.getContextPath())) {
            serverName = serverName + request.getContextPath();
        }

        return serverName;
    }

    public static String getServerName() {
        HttpServletRequest request = getRequest();
        String serverName = request.getServerName();
        if (request.getServerPort() != 80) {
            serverName = serverName + ":" + request.getServerPort();
        }

        return serverName;
    }

    public static HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    public static void setWebApplicationContext(WebApplicationContext webApplicationContext) {
        WebUtil.webApplicationContext = webApplicationContext;
    }

    public static ServletContext getServletContext() {
        return null != webApplicationContext ? webApplicationContext.getServletContext() : null;
    }

    public static Object getApplicationValue(String key) {
        ServletContext context = getServletContext();
        return context.getAttribute(key);
    }

    public static void setApplicationValue(String key, Object value) {
        ServletContext context = getServletContext();
        context.setAttribute(key, value);
    }

    public static String getBasePath() {
        HttpServletRequest request = getRequest();
        String path = request.getContextPath();
        String basePath = getServerUrl() + path + "/";
        return basePath;
    }

    public static String getRequestURI() {
        HttpServletRequest request = getRequest();
        if (request != null) {
            String uri = getServerUrl() + request.getServletPath();
            return uri;
        } else {
            return "";
        }
    }

    public static String getRequestParameter(String name) {
        return getRequestParameter(name, "");
    }

    public static String getRequestParameter(String name, String defaultValue) {
        String s = getRequest().getParameter(name);
        return s == null ? defaultValue : s;
    }
}
