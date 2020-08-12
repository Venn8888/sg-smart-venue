package com.sg.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * @author venn
 * @version 1.0.0
 * @date 2020/5/26
 */
public class MyHttpClientUtils {

    /**
     * 发送get请求
     *
     * @param url
     * @return
     */
    public static String sendGet(String url) throws Exception {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        String result = null;

        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            result = EntityUtils.toString(entity, "UTF-8");
        }
        httpGet.releaseConnection();

        return result;
    }


    /**
     * 发送post请求
     *
     * @param url
     * @param params 可使用JSONObject转JSON字符串
     * @return
     */
    public static String sendPost(String url, String params) throws Exception {

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(params, "UTF-8"));
        HttpResponse response = httpClient.execute(httpPost);

        return EntityUtils.toString(response.getEntity(), "UTF-8");
    }

}
