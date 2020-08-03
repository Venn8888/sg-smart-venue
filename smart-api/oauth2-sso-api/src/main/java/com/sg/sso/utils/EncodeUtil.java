package com.sg.sso.utils;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author venn
 * @version 1.0.0
 * @date 2020/8/3
 */
public class EncodeUtil {

    private static final String ENCODING = "UTF-8";
    private static final char[] BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    public EncodeUtil() {
    }

    public static String encodeHex(byte[] input) {
        return new String(Hex.encodeHex(input));
    }

    public static byte[] decodeHex(String input) {
        try {
            return Hex.decodeHex(input.toCharArray());
        } catch (DecoderException var2) {
            return null;
        }
    }

    public static String encodeBase64(byte[] input) {
        return new String(Base64.encodeBase64(input));
    }

    public static String encodeBase64(String input) {
        try {
            return new String(Base64.encodeBase64(input.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException var2) {
            return null;
        }
    }

    public static byte[] decodeBase64(String input) {
        return Base64.decodeBase64(input.getBytes());
    }

    public static String decodeBase64String(String input) {
        try {
            return new String(Base64.decodeBase64(input.getBytes()), "UTF-8");
        } catch (UnsupportedEncodingException var2) {
            return null;
        }
    }

    public static String encodeBase62(byte[] input) {
        char[] chars = new char[input.length];

        for(int i = 0; i < input.length; ++i) {
            chars[i] = BASE62[(input[i] & 255) % BASE62.length];
        }

        return new String(chars);
    }

    public static String encodeUrl(String part) {
        try {
            return URLEncoder.encode(part, "UTF-8");
        } catch (UnsupportedEncodingException var2) {
            return null;
        }
    }

    public static String decodeUrl(String part) {
        try {
            return URLDecoder.decode(part, "UTF-8");
        } catch (UnsupportedEncodingException var2) {
            return null;
        }
    }

}
