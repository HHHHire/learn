package com.ddh.learn.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/4/12 23:07
 */
@SpringBootTest
public class ApplicatoinTest {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void geo() {
        
        redisTemplate.opsForGeo().add()
    }
}
