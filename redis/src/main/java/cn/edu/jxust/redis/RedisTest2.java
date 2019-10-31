package cn.edu.jxust.redis;

import cn.edu.jxust.redis.entity.User;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: ddh
 * @date: 2019/10/28  19:56
 * @description:
 */
public class RedisTest2 {
    @Test
    public void test1() {
        Jedis jedis = RedisPoolUtil.getJedis();

        jedis.set("userName", "zhangsan");
        System.out.println(jedis.get("userName"));
        RedisPoolUtil.closeJedis(jedis);
    }

    @Test
    public void test2() {
        Jedis jedis = RedisPoolUtil.getJedis();
        String id = "10";
        String key = User.getKeyName() + id;
        if (jedis.exists(key)) {
            Map<String, String> map = jedis.hgetAll(key);
            User user = new User();
            user.setId(map.get("id"));
            user.setUserName(map.get("userName"));
            System.out.println("redis:" + user);
        } else {
            // 从数据库中获取数据 存入redis
            User user = User.builder()
                    .id("1")
                    .userName("李四").build();
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("id", user.getId());
            map.put("userName", user.getUserName());
            jedis.hmset(key, map);

            System.out.println("mysql:" + user);
        }
    }
}
