package com.shiro.cache;

import com.shiro.util.RedisDUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import java.util.Collection;
import java.util.Set;

@Component
public class RedisCache<k, v> implements Cache<k, v> {

    private final Logger logger = LoggerFactory.getLogger(RedisCache.class);

    private final String SHIRO_CACHE = "SHIRO_CACHE_";

    @Autowired
    private RedisDUtils redisDUtils;

    @Override
    public v get(k k) throws CacheException {
        logger.info("从redis中获取授权");
        byte[] key = redisDUtils.getKey(k, SHIRO_CACHE);
        byte[] value = redisDUtils.getValue(key);
        if (value != null)
            return (v) SerializationUtils.deserialize(value);
        return null;
    }

    @Override
    public v put(k k, v v) throws CacheException {
        logger.info("将授权数据放入redis中");
        byte[] key = redisDUtils.getKey(k, SHIRO_CACHE);
        byte[] value = SerializationUtils.serialize(v);
        redisDUtils.setValue(key, value);
        redisDUtils.expire(key, 600);
        return v;
    }

    @Override
    public v remove(k k) throws CacheException {
        byte[] key = redisDUtils.getKey(k, SHIRO_CACHE);
        byte[] value = redisDUtils.getValue(key);
        redisDUtils.deleteValue(key);
        if (value != null)
            return (v) SerializationUtils.deserialize(value);
        return null;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<k> keys() {
        return null;
    }

    @Override
    public Collection<v> values() {
        return null;
    }
}
