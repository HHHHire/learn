package cn.edu.jxust.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author: ddh
 * @date: 2019/10/28  19:49
 * @description: redis 连接池
 */
public class RedisPoolUtil {
    private static JedisPool jedisPool;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        // 最大连接数
        config.setMaxTotal(100);
        // 空闲时连接数
        config.setMaxIdle(10);

        jedisPool = new JedisPool(config, "47.100.88.162", 6379);
    }

    public static Jedis getJedis() {
        Jedis resource = jedisPool.getResource();
        resource.auth("123456");
        return resource;
    }

    public static void closeJedis(Jedis jedis) {
        jedis.close();
    }
}
