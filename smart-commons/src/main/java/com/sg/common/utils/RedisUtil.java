package com.sg.common.utils;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Redis缓存辅助类
 * 
 * @author 
 * @version
 */
public final class RedisUtil {
    private static RedisTemplate<Serializable, Serializable> redisTemplate = null;

    // 获取连接
    @SuppressWarnings("unchecked")
    private static RedisTemplate<Serializable, Serializable> getRedis() {
        if (redisTemplate == null) {
            synchronized (RedisUtil.class) {
                if (redisTemplate == null) {
                    redisTemplate = (RedisTemplate<Serializable, Serializable>)SpringUtil.getBean("redisTemplate");;
                }
            }
        }
        
        return redisTemplate;
    }

    public static final Serializable get(final String key) {
        return getRedis().opsForValue().get(key);
    }

    public static final void set(final String key, final Serializable value) {
        getRedis().boundValueOps(key).set(value);
    }
    
    public static final void set(final String key, final Serializable value, int timeout) {
        getRedis().boundValueOps(key).set(value);
        expire(key, timeout);
    }

    public static final Boolean exists(final String key) {
        return getRedis().hasKey(key);
    }

    public static final void del(final String key) {
        getRedis().delete(key);
    }

    public static final void delAll(final String pattern) {
        getRedis().delete(getRedis().keys(pattern));
    }

    public static final String type(final String key) {
        return getRedis().type(key).getClass().getName();
    }

    /**
     * 在某段时间后失效
     * 
     * @return
     */
    public static final Boolean expire(final String key, final int seconds) {
        return getRedis().expire(key, seconds, TimeUnit.SECONDS);
    }

    /**
     * 在某个时间点失效
     * 
     * @param key
     * @param unixTime
     * @return
     */
    public static final Boolean expireAt(final String key, final long unixTime) {
        return getRedis().expireAt(key, new Date(unixTime));
    }

    public static final Long ttl(final String key) {
        return getRedis().getExpire(key, TimeUnit.SECONDS);
    }

    public static final void setrange(final String key, final long offset, final String value) {
        getRedis().boundValueOps(key).set(value, offset);
    }

    public static final String getrange(final String key, final long startOffset, final long endOffset) {
        return getRedis().boundValueOps(key).get(startOffset, endOffset);
    }

    public static final Serializable getSet(final String key, final String value) {
        return getRedis().boundValueOps(key).getAndSet(value);
    }

    /** 递增 */
    public static Long increment(final String redisKey) {
        return getRedis().execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.incr(redisKey.getBytes());
            }
        });
    }
}
