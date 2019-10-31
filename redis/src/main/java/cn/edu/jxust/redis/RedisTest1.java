package cn.edu.jxust.redis;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @author: ddh
 * @date: 2019/10/28  15:08
 * @description:
 */
public class RedisTest1 {
    private String host = "47.100.88.162";
    private Integer port = 6379;
    private Jedis jedis;

    @Before
    public void beforeTest() {
        jedis = new Jedis(host, port);
        jedis.auth("123456");
    }

    @After
    public void afterTest() {
        jedis.close();
    }

    @Test
    public void test1() {
        Jedis jedis = new Jedis(host, port);
        jedis.auth("123456");

        System.out.println(jedis.ping());
    }

    @Test
    public void test2() {
        Jedis jedis = new Jedis(host, port);
        jedis.auth("123456");
        jedis.set("userName", "zhangsan");
        jedis.set("userName:1", "李四");
        String userName = jedis.get("userName");
        String s = jedis.get("userName:1");
        System.out.println(userName);
        System.out.println(s);
        jedis.close();
    }

    @Test
    public void test3() {
        String key = "applicationName";
        if (jedis.exists(key)) {
            System.out.println("redis:");
            System.out.println(jedis.get(key));
        } else {
            System.out.println("mysql:");
            jedis.set(key, "hello world");
        }
    }
}
