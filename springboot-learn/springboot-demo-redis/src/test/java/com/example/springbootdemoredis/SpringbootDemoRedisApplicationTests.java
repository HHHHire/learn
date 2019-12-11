package com.example.springbootdemoredis;

import com.example.springbootdemoredis.entity.Data;
import com.example.springbootdemoredis.util.MapUtil;
import com.example.springbootdemoredis.util.RedisPoolUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.beans.BeanMap;

import java.util.HashMap;

@SpringBootTest
class SpringbootDemoRedisApplicationTests {

    @Autowired
    RedisPoolUtil redisPoolUtil;

    @Test
    void contextLoads() {
    }

    @Test
    public void test2() {
        Data build = Data.builder().userName("zhangsan")
                .userAge(12)
                .userAddress("jiangxi").build();
        HashMap<String, String> map = MapUtil.create("userName", "zhangsan", "userAge", "19", "userAddress", "jiangxi");
        System.out.println(map);

        BeanMap beanMap = BeanMap.create(build);
        System.out.println(beanMap);
        redisPoolUtil.hSet("user1", map);

    }
}
