package org.seckill.dao.redis;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.seckill.entity.Seckill;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class SeckillRedisDao {

    //相当于数据库连接池
    private final JedisPool jedisPool;

    private final static String KEY = "SECKILL_";

    private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);

    public SeckillRedisDao(String ip, int port) {
        //        jedisPool = new JedisPool(ip, port);
        JedisPoolConfig config = new JedisPoolConfig();
        jedisPool = new JedisPool(config, "localhost", 6379, 2000, "icepoint123");
    }

    public Seckill getSeckill(long id) {
        Jedis jedis = jedisPool.getResource();
        try {
            String key = KEY + id;
            //自定义序列化
            byte[] bytes = jedis.get(key.getBytes());
            if (bytes != null) {
                Seckill seckill = schema.newMessage();
                ProtobufIOUtil.mergeFrom(bytes, seckill, schema);
                return seckill;
            }
        } finally {
            jedis.close();
        }
        return null;
    }

    public void putSeckill(Seckill seckill) {
        if (seckill != null && seckill.getId() > 0) {
            Jedis jedis = jedisPool.getResource();
            String key = KEY + seckill.getId();
            byte[] bytes = ProtobufIOUtil.toByteArray(seckill, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
            //单位为秒
            int timeOut = 60 * 60;
            String result = jedis.setex(key.getBytes(), timeOut, bytes);
            if (!"OK".equals(result))
                throw new RuntimeException("redis缓存失败：" + result);
        }
    }
}
