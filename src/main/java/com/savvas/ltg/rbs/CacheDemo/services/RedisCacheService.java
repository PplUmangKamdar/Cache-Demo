/*
 * Copyright 2021, Savvas Learning Company LLC
 *
 * RedisCacheService.java
 */
package com.savvas.ltg.rbs.CacheDemo.services;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisCacheService {

    @Value("${redis.cache.enabled}")
    public String redisCacheEnabled;

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    public Object getValue(String key, String hashKey) {
        Object returnValue = null;
        String cacheKey = generateCacheKey(key, hashKey);
        if (BooleanUtils.toBoolean(redisCacheEnabled)) {
            if (StringUtils.isNotEmpty(cacheKey)) {
                returnValue = redisTemplate.opsForValue().get(cacheKey);
            }
        }
        return returnValue;
    }

    public void setValue(String key, String hashKey, Object value, long expiry) {
        String cacheKey = generateCacheKey(key, hashKey);
        if (BooleanUtils.toBoolean(redisCacheEnabled)) {
            if (StringUtils.isNotEmpty(cacheKey)) {
                redisTemplate.opsForValue().set(cacheKey, value);
                if (expiry > 0) {
                    redisTemplate.expire(cacheKey, expiry, TimeUnit.SECONDS);
                }
            }
        }
    }

    public void removeValue(String key, String hashKey) {
        String cacheKey = generateCacheKey(key, hashKey);
        if (BooleanUtils.toBoolean(redisCacheEnabled)) {
            if (StringUtils.isNotEmpty(cacheKey)) {
                redisTemplate.delete(cacheKey);
            }
        }
    }

    private String generateCacheKey(String key, String hashKey) {
        String cacheKey = null;
        if (StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(hashKey)) {
            cacheKey = key + ":" + hashKey;
        }
        return cacheKey;
    }
}
