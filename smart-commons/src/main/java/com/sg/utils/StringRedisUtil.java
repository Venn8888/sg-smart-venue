package com.sg.utils;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * redis工具类
 *
 * @author: venn
 */
@Component
public class StringRedisUtil {

    private final StringRedisTemplate stringRedisTemplate;

    public StringRedisUtil(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 字符串获取值
     *
     * @param key 键
     */
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }


    /**
     * 字符串存入值
     *
     * @param key   键
     * @param value 值
     */
    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * 字符串存入值
     *
     * @param value  值
     * @param expire 过期时间（毫秒计）
     * @param key    键
     */
    public void set(String key, String value, long expire) {
        stringRedisTemplate.opsForValue().set(key, value, expire, TimeUnit.MILLISECONDS);
    }

    /**
     * 删出key
     * 这里跟下边deleteKey（）最底层实现都是一样的，应该可以通用
     *
     * @param key 键
     */
    public void delete(String key) {
        stringRedisTemplate.opsForValue().getOperations().delete(key);
    }


    /**
     * 删除key下所有值
     *
     * @param key 查询的key
     */
    public void deleteKey(String key) {
        stringRedisTemplate.opsForHash().getOperations().delete(key);
    }


    /**
     * 判断key下是否有值
     *
     * @param key 判断的key
     */
    public Boolean hasKey(String key) {
        return stringRedisTemplate.opsForHash().getOperations().hasKey(key);
    }


}
