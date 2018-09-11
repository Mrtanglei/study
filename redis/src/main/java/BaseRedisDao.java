import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis操作的基类
 *
 * @param <T>
 */
abstract class BaseRedisDao<T> {

    @Autowired
    RedisTemplate<String, T> redisTemplate;


    /**
     * 拼接redis键
     *
     * @param keyPrefix 键前缀
     * @param keySuffix 键后缀
     * @return
     */
    String getKey(RedisKeyPrefix keyPrefix, String keySuffix) {
        Assert.notNull(keyPrefix, "键前缀为空");
        return keyPrefix.name() + StringUtils.trimAllWhitespace(keySuffix);
    }

    /**
     * 向redis值中添加键值对
     *
     * @param key
     * @param value
     */
    final void setObjectValue(String key, T value) {
        ValueOperations<String, T> values = redisTemplate.opsForValue();
        values.set(key, value);
    }

    /**
     * 通过key获取redis对应的值
     *
     * @param key
     * @return
     */
    final T getObjectValue(String key) {
        ValueOperations<String, T> values = redisTemplate.opsForValue();
        return values.get(key);
    }

    /**
     * 向redis值中添加键值对
     *
     * @param keyPrefix
     * @param keySuffix
     * @param value
     */
    void setObjectValue(RedisKeyPrefix keyPrefix, String keySuffix, T value) {
        ValueOperations<String, T> values = redisTemplate.opsForValue();
        values.set(getKey(keyPrefix, keySuffix), value);
    }

    /**
     * 通过key获取redis对应的值
     *
     * @param keyPrefix
     * @param keySuffix
     */
    T getObjectValue(RedisKeyPrefix keyPrefix, String keySuffix) {
        ValueOperations<String, T> values = redisTemplate.opsForValue();
        return values.get(getKey(keyPrefix, keySuffix));
    }

    /**
     * 向redis插入值并设置过期时间
     *
     * @param keyPrefix
     * @param keySuffix
     * @param value
     * @param timeout
     * @param timeUnit
     */
    protected void setObjectWithTimeOut(RedisKeyPrefix keyPrefix, String keySuffix, T value, long timeout, TimeUnit timeUnit) {
        ValueOperations<String, T> values = redisTemplate.opsForValue();
        String key = getKey(keyPrefix, keySuffix);
        values.set(key, value, timeout, timeUnit);
    }

    /**
     * 从redis中删除数据
     *
     * @param keyPrefix
     * @param keySuffix
     */
    protected void removeObject(RedisKeyPrefix keyPrefix, String keySuffix) {
        String key = getKey(keyPrefix, keySuffix);
        redisTemplate.delete(key);
    }

    /**
     * 从新设置过期时间
     *
     * @param keyPrefix
     * @param keySuffix
     * @param timeout
     * @param timeUnit
     */
    protected void exipre(RedisKeyPrefix keyPrefix, String keySuffix, long timeout, TimeUnit timeUnit) {
        String key = getKey(keyPrefix, keySuffix);
        ValueOperations<String, T> values = redisTemplate.opsForValue();
        values.set(key, values.get(key), timeout, timeUnit);
    }

    /**
     * 获取匹配的所有key
     *
     * @param patten
     * @return
     */
    protected Set<String> getKeys(String patten) {
        return redisTemplate.keys(patten);
    }
}
