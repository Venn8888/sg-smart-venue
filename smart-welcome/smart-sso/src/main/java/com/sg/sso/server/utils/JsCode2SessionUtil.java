package com.sg.sso.server.utils;

import com.sg.sso.server.enums.WeChatUrlEnum;
import com.sg.utils.MyHttpClientUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.codehaus.xfire.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.Arrays;

/**
 * @author venn
 * @version 1.0.0
 * @date 2020/5/26
 */
public class JsCode2SessionUtil {

    /**
     * 请求微信后台获取用户数据
     *
     * @param code wx.login获取到的临时code
     * @return 请求结果
     */
    public static String jsCode2session(String appid, String secret, String code, String grantType) throws Exception {
        //拼接请求url
        String url = WeChatUrlEnum.JS_CODE_2_SESSION.getUrl() + "?appid=" + appid + "&secret=" + secret + "&js_code=" + code + "&grant_type=" + grantType;
        return MyHttpClientUtils.sendGet(url);
    }

    /**
     * 请求微信后台获取access_token
     * @param appid
     * @param secret
     * @param grantType
     * @return
     * @throws Exception
     */
    public static String getAccessToken(String appid,String secret,String grantType) throws Exception {
        String url=WeChatUrlEnum.JS_ACCESS_TOKEN.getUrl()+"?grant_type="+grantType+"&appid="+appid+"&secret="+secret;
        return MyHttpClientUtils.sendGet(url);
    }

    /**
     * 解密用户敏感数据获取用户信息
     *
     * @param sessionKey    数据进行加密签名的密钥
     * @param encryptedData 包括敏感数据在内的完整用户信息的加密数据
     * @param iv            加密算法的初始向量
     * @return 解密数据
     */
    public static String getDecryptData(String encryptedData, String sessionKey, String iv) throws Exception {
        // 被加密的数据
        byte[] dataByte = Base64.decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.decode(sessionKey);
        // 偏移量
        byte[] ivByte = Base64.decode(iv);
        // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
        int base = 16;
        if (keyByte.length % base != 0) {
            int groups = keyByte.length / base + 1;
            byte[] temp = new byte[groups * base];
            Arrays.fill(temp, (byte) 0);
            System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
            keyByte = temp;
        }
        // 初始化
        Security.addProvider(new BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
        SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
        AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
        parameters.init(new IvParameterSpec(ivByte));
        // 初始化
        cipher.init(Cipher.DECRYPT_MODE, spec, parameters);
        byte[] resultByte = cipher.doFinal(dataByte);
        if (null != resultByte && resultByte.length > 0) {
            return new String(resultByte, StandardCharsets.UTF_8);
        }
        return null;
    }
}
