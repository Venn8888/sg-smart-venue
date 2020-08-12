package com.sg.utils;

import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * @author venn
 * @version 1.0.0
 * @date 2020/5/28
 */
@Component
public class MobileMsgUtil {
    /**
     * 随机生成6位随机验证码 方法说明
     */
    public static String createRandomCode() {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }

    /**
     * 获取过期时间：当前时间加10分钟
     *
     * @return Date
     */
    public static Date getExpiredTime() {
        //得到当前时间
        Calendar nowTime = Calendar.getInstance();
        //增加10分钟
        nowTime.add(Calendar.MINUTE, 10);
        return nowTime.getTime();
    }

}
