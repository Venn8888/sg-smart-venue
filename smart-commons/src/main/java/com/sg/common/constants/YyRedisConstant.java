package com.sg.common.constants;

/**
 * @ClassName YyRedisConstant
 * @Description: TODO
 * @Author admin
 * @Date 2020/7/7
 * @Version V1.0
 **/
public class YyRedisConstant {

    /**
     * 课程实体订单key
     */
    public static String COURSE_DOC_REDIS_KEY="com:yy:course:courseDoc:";
    /**
     * 课程实体订单失效时间
     * 毫秒
     */
    public static long COURSE_DOC_REDIS_KEY_EXPIRE=1800000;
    /**
     * 购课短信验证码key
     */
    public static String BUY_COURE_REDIS_KEY="smsCodeBuyCourseRedisKey";
    /**
     * 购课短信验证码有效期
     */
    public static long BUY_COURE_REDIS_KEY_EXPIRE=60000;

    public static final String SMS_CODE_BUY_COURSE_REDIS_KEY = "buyCourseSmsCodeRedisKey";
}
