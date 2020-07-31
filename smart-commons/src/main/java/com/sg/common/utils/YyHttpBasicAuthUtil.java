package com.sg.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: Http 工具类
 * @author: liu weichen
 * @create: 2020/7/17
 */
public class YyHttpBasicAuthUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(YyHttpBasicAuthUtil.class);

    /**
     * 创建HTTPURLConnection
     *
     * @param requestURL    需要访问的URL地址
     * @param requestMethod 请求方式
     * @return HttpURLConnection
     * @throws Exception
     */
    private static HttpURLConnection createHttpURLConnection(String requestURL, String requestMethod) throws Exception {
        URL uploadURL = new URL(requestURL);
        HttpURLConnection connection = (HttpURLConnection) uploadURL.openConnection();
        connection.setConnectTimeout(50000);
        connection.setReadTimeout(30000);
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setUseCaches(false);
        connection.setRequestProperty("Connection", "Keep-Alive");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod(requestMethod);
        return connection;
    }

    private static String getBasicAuth(String appId, String appSecret) {
        String secret = String.format("%s:%s", appId, appSecret);
        String encoding = new String(Base64.encodeBase64(secret.getBytes(StandardCharsets.US_ASCII)));

        return String.format("Basic %s", encoding);
    }

    public static String process(String requestURL, String appId, String appSecret, String requestMethod, JSONObject params) {
        String result = null;
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = createHttpURLConnection(requestURL, requestMethod);
            // 资源访问授权
            urlConnection.setRequestProperty("Authorization", getBasicAuth(appId, appSecret));
            urlConnection.setConnectTimeout(5000);
            urlConnection.setReadTimeout(3000);
            urlConnection.connect();

            OutputStreamWriter osw = new OutputStreamWriter(urlConnection.getOutputStream(), StandardCharsets.UTF_8);
            BufferedWriter writer = new BufferedWriter(osw);
            if (params != null) {
                writer.write(params.toJSONString());
            }
            writer.flush();
            writer.close();
            LOGGER.info("请求响应状态: {}, 响应结果: {}", urlConnection.getResponseCode(), urlConnection.getResponseMessage());
            if (HttpURLConnection.HTTP_OK == urlConnection.getResponseCode()) {
                byte[] data = new byte[1024];
                InputStream resp = urlConnection.getInputStream();
                StringBuffer sb = new StringBuffer();
                int len = 0;
                while ((len = resp.read(data)) > -1) {
                    sb.append(new String(data, 0, len, StandardCharsets.UTF_8));
                }
                resp.close();
                result = sb.toString();
            } else {
                result = "{" + urlConnection.getResponseCode() + ":" + urlConnection.getResponseMessage() + "}";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return result;
    }


    public static void main(String[] args) throws Exception {
        List<String> devices = new ArrayList<>();
        devices.add("ZJT0123456789");
        devices.add("ZJT00123456789");
        String appId = "940217";
        String appSecret = "5bf14b1a1e1b7711e1aba7493ababac5";
        String topic = "data_sync";

        for (int i = 0; i < devices.size(); i++) {
            JSONObject object = new JSONObject();
            object.put("deviceID", devices.get(i));
            object.put("topic", topic);

            JSONObject incre = new JSONObject();
            incre.put("memberId", i);
            incre.put("name", "A" + i);
            incre.put("gender", "m");
            incre.put("birthday", "2020-07-24");
            incre.put("face", "member/2020072315455201807512.jpg");
            incre.put("organizationId", "726897922775449600");

            JSONObject payload = new JSONObject();
            payload.put("incre_sync", incre);
            object.put("payload", payload.toJSONString());
            System.out.println(object);
            System.out.println(process("http://apps-dev.iot.timework.cn/api/v1/device_publish", appId, appSecret, "POST", object));
        }
    }
}
