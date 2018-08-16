package com.styzf.core.common.redis;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

@Component
public class RedisUtil {

    private static Logger logger = LoggerFactory.getLogger(RedisUtil.class);
    
    @Autowired
    @Qualifier("stringRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;

    public <T> Boolean setObject(String key, T t) {
        return setObject(key , t, 12 * 60 * 60L, TimeUnit.SECONDS);
    }
    public <T> Boolean setObject(String key, T t, Long timeout) {
        return setObject(key , t, timeout, TimeUnit.SECONDS);
    }
    public <T> Boolean setObject(String key, T t, Long timeout, TimeUnit unit) {
        String value = JSON.toJSONString(t);
        set(key ,value, timeout, unit);
        return Boolean.TRUE;
    }
    
    public Boolean set(String key, String value) {
        return set(key ,value, 12 * 60 * 60L, TimeUnit.SECONDS);
    }
    
    public Boolean set(String key, String value, Long timeout) {
        return set(key, value, timeout, TimeUnit.SECONDS);
    }

    public Boolean set(String key, String value, Long timeout, TimeUnit unit) {
        BoundValueOperations<String,String> boundValueOps = stringRedisTemplate.boundValueOps(key);
        boundValueOps.set(value, timeout, unit);
        return Boolean.TRUE;
    }
    
    public String get(String key) {
        BoundValueOperations<String,String> boundValueOps = stringRedisTemplate.boundValueOps(key);
        return boundValueOps.get();
    }
    
    public <T> T getObject(String key, Class<T> clazz) {
        String value = null;
        try {
            BoundValueOperations<String,String> boundValueOps = stringRedisTemplate.boundValueOps(key);
            value = boundValueOps.get();
            if (StringUtils.isBlank(value)) {
                return null;
            }
            return JSON.parseObject(value, clazz);
        } catch (Exception e) {
            logger.error("json解析错误：" + value);
            return null;
        }
    }
    
    public boolean remove(String key) {
        BoundValueOperations<String,String> boundValueOps = stringRedisTemplate.boundValueOps(key);
        return boundValueOps.persist();
    } 
    
    public boolean hset(String key, String member, String value) {
        return hset(key, member, value, 12 * 60 * 60L, TimeUnit.SECONDS);
    }
    
    public boolean hset(String key, String member, String value, Long timeout) {
        return hset(key, member, value, timeout, TimeUnit.SECONDS);
    }
    
    public boolean hset(String key, String member, String value, Long timeout, TimeUnit unit) {
        BoundHashOperations<String,Object,Object> boundHashOps = stringRedisTemplate.boundHashOps(key);
        boundHashOps.expire(timeout, unit);
        boundHashOps.put(member, value);
        return Boolean.TRUE;
    }
    
    public long hremove(String key,Object[] keys) {
        BoundHashOperations<String,Object,Object> boundHashOps = stringRedisTemplate.boundHashOps(key);
        return boundHashOps.delete(keys);
    }
    
    public Set<String> keys(String pattern) {
        return stringRedisTemplate.keys(pattern);
    }
}
