package com.sg.gateway.spring.server.util;

import com.sg.framework.constants.Constants;
import com.sg.framework.util.DateUtil;
import com.sg.framework.util.EncryptUtil;
import com.sg.framework.util.RandomValueUtil;
import com.sg.framework.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 
 */
@Slf4j
public class SignatureUtil {

    /**
     * 5分钟有效期
     */
    private final static long MAX_EXPIRE = 5 * 60;

    public static void main(String[] args) throws Exception {
        //参数签名算法测试例子
        HashMap<String, String> signMap = new HashMap<String, String>();
        signMap.put("appId", "gateway");
        signMap.put("signType", SignType.SHA256.name());
        signMap.put("timestamp", DateUtil.getCurrentTimestampStr());
        signMap.put("nonce", RandomValueUtil.randomAlphanumeric(16));
        String sign = SignatureUtil.getSign(signMap, "123456");
        System.out.println("签名结果:" + sign);
        signMap.put("sign", sign);
        System.out.println(signMap);
        System.out.println(SignatureUtil.validateSign(signMap, "123456"));
    }

    /**
     * 验证参数
     *
     * @param paramsMap
     * @throws Exception
     */
    public static void validateParams(Map<String, String> paramsMap) throws Exception {
        Assert.notNull(paramsMap.get(Constants.SIGN_APP_ID_KEY), "签名验证失败:appId不能为空");
        Assert.notNull(paramsMap.get(Constants.SIGN_NONCE_KEY), "签名验证失败:nonce不能为空");
        Assert.notNull(paramsMap.get(Constants.SIGN_TIMESTAMP_KEY), "签名验证失败:timestamp不能为空");
        Assert.notNull(paramsMap.get(Constants.SIGN_SIGN_TYPE_KEY), "签名验证失败:ignType不能为空");
        Assert.notNull(paramsMap.get(Constants.SIGN_SIGN_KEY), "签名验证失败:sign不能为空");
        if (!SignatureUtil.SignType.contains(paramsMap.get(Constants.SIGN_SIGN_TYPE_KEY))) {
            throw new IllegalArgumentException(String.format("签名验证失败:signType必须为:%s,%s", SignatureUtil.SignType.MD5, SignatureUtil.SignType.SHA256));
        }
        try {
            DateUtil.parseDate(paramsMap.get(Constants.SIGN_TIMESTAMP_KEY), "yyyyMMddHHmmss");
        } catch (ParseException e) {
            throw new IllegalArgumentException("签名验证失败:timestamp格式必须为:yyyyMMddHHmmss");
        }
    }

    /**
     * @param paramMap     必须包含
     * @param clientSecret
     * @return
     */
    public static boolean validateSign(Map<String, String> paramMap, String clientSecret) {
        try {
            validateParams(paramMap);
            String sign = paramMap.get(Constants.SIGN_SIGN_KEY);
            String timestamp = paramMap.get(Constants.SIGN_TIMESTAMP_KEY);
            Long clientTimestamp = Long.parseLong(timestamp);
            //判断时间戳 timestamp=201808091113
            if ((DateUtil.getCurrentTimestamp() - clientTimestamp) > MAX_EXPIRE) {
                log.debug("validateSign fail timestamp expire");
                return false;
            }
            //重新生成签名
            String signNew = getSign(paramMap, clientSecret);
            //判断当前签名是否正确
            if (signNew.equals(sign)) {
                return true;
            }
        } catch (Exception e) {
            log.error("validateSign error:{}", e.getMessage());
            return false;
        }
        return false;
    }


    /**
     * 得到签名
     *
     * @param paramMap     参数集合不含clientSecret
     *                     必须包含clientId=客户端ID
     *                     signType = SHA256|MD5 签名方式
     *                     timestamp=时间戳
     *                     nonce=随机字符串
     * @param clientSecret 验证接口的clientSecret
     * @return
     */
    public static String getSign(Map<String, String> paramMap, String clientSecret) {
        if (paramMap == null) {
            return "";
        }
        //排序
        Set<String> keySet = paramMap.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        String signType = paramMap.get(Constants.SIGN_SIGN_TYPE_KEY);
        SignType type = null;
        if (StringUtil.isNotBlank(signType)) {
            type = SignType.valueOf(signType);
        }
        if (type == null) {
            type = SignType.MD5;
        }
        for (String k : keyArray) {
            if (k.equals(Constants.SIGN_SIGN_KEY) || k.equals(Constants.SIGN_SECRET_KEY)) {
                continue;
            }
            if (paramMap.get(k).trim().length() > 0) {
                // 参数值为空，则不参与签名
                sb.append(k).append("=").append(paramMap.get(k).trim()).append("&");
            }
        }
        //暂时不需要个人认证
        sb.append(Constants.SIGN_SECRET_KEY+"=").append(clientSecret);
        String signStr = "";
        //加密
        switch (type) {
            case MD5:
                signStr = EncryptUtil.md5Hex(sb.toString()).toLowerCase();
                break;
            case SHA256:
                signStr = EncryptUtil.sha256Hex(sb.toString()).toLowerCase();
                break;
            default:
                break;
        }
        return signStr;
    }


    public enum SignType {
        MD5,
        SHA256;

        public static boolean contains(String type) {
            for (SignType typeEnum : SignType.values()) {
                if (typeEnum.name().equals(type)) {
                    return true;
                }
            }
            return false;
        }
    }

}
