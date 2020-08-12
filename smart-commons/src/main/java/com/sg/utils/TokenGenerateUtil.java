package com.sg.utils;

import java.util.UUID;

/**
 * token生成工具类
 *
 * @author venn
 * @version 1.0.0
 * @date 2020/5/26
 */
public class TokenGenerateUtil {

    /**
     * 随机token生成
     *
     * @return token
     */
    public static String generateToken() {
        return System.currentTimeMillis() + UUID.randomUUID().toString().replaceAll("-", "");
    }
}
