package com.shiro.util;

import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.Set;

@Component
public class RedisDUtils {

    @Resource
    private JedisPool jedisPool;

    private Jedis getResource() {
        return jedisPool.getResource();
    }

    public byte[] getKey(Object key, String profix) {
        if (key != null) {
            if (key instanceof String) {
                if (StringUtils.hasText((String) key)) {
                    return (profix + key).getBytes();
                }
            }
            //            return (profix + SerializationUtils.serialize(key).toString()).getBytes();
            return SerializationUtils.serialize(key);
        }

        return null;
    }

    public void setValue(byte[] key, byte[] value) {
        Jedis jedis = getResource();
        try {
            jedis.set(key, value);
        } finally {
            jedis.close();
        }
    }

    /**
     * 过期时间 单位秒
     *
     * @param key
     * @param i
     */
    public void expire(byte[] key, int i) {
        Jedis jedis = getResource();
        try {
            jedis.expire(key, i);
        } finally {
            jedis.close();
        }
    }

    public byte[] getValue(byte[] key) {
        if (key != null) {
            Jedis jedis = getResource();
            try {
                return jedis.get(key);
            } finally {
                jedis.close();
            }
        }
        return null;
    }

    public void deleteValue(byte[] key) {
        if (key != null) {
            Jedis jedis = getResource();
            try {
                jedis.del(key);
            } finally {
                jedis.close();
            }
        }
    }

    public Set<byte[]> keys(String profix) {
        Jedis jedis = getResource();
        try {
            Set<byte[]> keys = jedis.keys((profix + "*").getBytes());
            return keys;
        } finally {
            jedis.close();
        }
    }
}
