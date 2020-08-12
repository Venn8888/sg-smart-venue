package com.sg.utils;


import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * rsa工具类
 */
public class RsaUtil {

    /**
     * Sha256验签
     *
     * @param content   签名前数据
     * @param sign      Base64后的签名数据
     * @param publicKey 公钥
     * @return 返回验签结果 true/false
     */
    public static boolean signCheckSha256(String content, String sign, String publicKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] encodedKey = Base64.decode(publicKey);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
            Signature signature = Signature.getInstance("SHA256WithRSA");
            signature.initVerify(pubKey);
            signature.update(content.getBytes());
            return signature.verify(Base64.decode(sign));
        } catch (Exception e) {
            throw new RuntimeException(e.toString());
        }
    }

    /**
     * Sha256签名
     *
     * @param content    待签名的内容
     * @param privateKey 私钥
     * @return 签名后的Base64数据
     */
    public static String signSha256(String content, String privateKey) {
        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decode(privateKey));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey priKey = keyFactory.generatePrivate(priPKCS8);
            Signature signature = Signature.getInstance("SHA256WithRSA");
            signature.initSign(priKey);
            signature.update(content.getBytes());
            return Base64.encode(signature.sign());
        } catch (Exception e) {
            throw new RuntimeException(e.toString());
        }
    }

    public static String encode(String pubKey, String plainText) {
        try {
            byte[] buffer = Base64.decode(pubKey);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(keySpec);
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(1, publicKey);
            byte[] bytes = cipher.doFinal(plainText.getBytes());
            return Base64.encode(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e.toString());
        }
    }

    public static String decode(String priKey, String base64Text) {
        try {
            byte[] buffer = Base64.decode(priKey);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privatekey = keyFactory.generatePrivate(keySpec);
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(2, privatekey);
            byte[] bytes = cipher.doFinal(Base64.decode(base64Text));
            return new String(bytes);
        } catch (Exception e) {
            throw new RuntimeException("rsa解密错误");
        }

    }

    public static void main(String[] args) {
        String pubKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKHCFq6ipumMUAfWtnjjSMaSYPBhqOWO\n"
                + "K9Fd4n1XhNSZGSadYg98D9cgYc0w5l0rZWuuFsqC8Af6JuksEbmcMAcCAwEAAQ==";

        String priKey = "MIIBUwIBADANBgkqhkiG9w0BAQEFAASCAT0wggE5AgEAAkEAocIWrqKm6YxQB9a2\n"
                + "eONIxpJg8GGo5Y4r0V3ifVeE1JkZJp1iD3wP1yBhzTDmXStla64WyoLwB/om6SwR\n"
                + "uZwwBwIDAQABAkBg82OE6BgCgwa0rAxSCGfmHHXdnasNa1j38718QqhqgyRCMawh\n"
                + "lqNv+o5yPX3xN/9So6120cs1AONqfqQLS0SBAiEA1fHBrpie2DGiti0b0bgGw0Yc\n"
                + "LLvr7j2z4XTqJHfpMt0CIQDBji9XkzWcix80qolZKdlXhPa/3FVMneDBQtEbzb9m\n"
                + "MwIgCtaMcUPaCCm7jG8Mkbs43HuYwctjUFZf3nQFyIMqlSECIB5H7VYpHLER/t7R\n"
                + "c010w6Dyl1vqz5l99aSmnGpaJQCLAiBUbznE9WlnD1ZZXwvMIgkEZb3vwJtjebXe\n"
                + "BYxGTJSmaA==";

/*        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            sb.append(random.nextInt(9));
        }
        System.out.println(sb.toString());

        String sha256 = RsaUtil.signSha256(sb.toString(), priKey);
        System.out.println(sha256);

        boolean checkSha256 = RsaUtil.signCheckSha256(sb.toString(), sha256, pubKey);
        System.out.println(checkSha256);

        String encode = RsaUtil.encode(pubKey, "123456");
        System.out.println("encode: " + encode);

        String decode = RsaUtil.decode(priKey, encode);
        System.out.println("decode:" + decode);*/

        String content = "{\"code\":\"10000\",\"msg\":\"Success\",\"buyer_logon_id\":\"135******77\",\"buyer_pay_amount\":\"0.01\",\"buyer_user_id\":\"2088702959351825\",\"fund_bill_list\":[{\"amount\":\"0.01\",\"fund_channel\":\"ALIPAYACCOUNT\"}],\"gmt_payment\":\"2020-07-15 14:43:48\",\"invoice_amount\":\"0.01\",\"out_trade_no\":\"4c930384af5a4be7b84684eea414675f\",\"point_amount\":\"0.00\",\"receipt_amount\":\"0.01\",\"total_amount\":\"0.01\",\"trade_no\":\"2020071522001451821429743024\"}";
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCo4ygd8WGWmSLc1J0pwNr1ZTSx\n" +
                "1N3ZbFu02+FI3ZUIyH52/0HqaSV9cUJzT0+2joITFwEGM+6k8+gFCtsTXs3aROW3\n" +
                "b059aldaeJPmS+qwR9+Vzt5hlIUh/+2OW1dWE1ys8Tg5yqW6YTpqjTbSKBDuzPyH\n" +
                "X63saNfbyLXS7AUafwIDAQAB";
        String sign = "dN1S4/yqLrlXGZ8eljIPAq2fY1mjQ6n1a9Cf6EiQz+9DjoxnSiYLfsVm6hOI1tIbcG/UtEwhQKnXmcPro4h/Wc7qmL49X1o7rzgQaVuWLb0pI0AT+2/uH0KOs35BMB2Va19pbl6pG81aKRHjrm03RUjJKLx6xza5YmzmByc/W3vIb/pLqsFjrJAlqgOhyRismVDkY+vdJzhX2YG4ug9SGFKY0c97Algvrwqm445OJYIUMskqKRMS+8j+ozcTUfqpJcjoVuLx2AyT8ZdCYfkwmJalLV9XNaczb5eq1NUPSSCgZLeD5UOgIvkAKQ29Oag6vxexiffEB+ECcNroNL8K+A==";
        System.out.println(RsaUtil.signCheckSha256(content, publicKey, sign));
    }
}
