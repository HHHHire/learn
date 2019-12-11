package com.example.springbootdemoredis.service;

import com.example.springbootdemoredis.entity.Data;
import com.example.springbootdemoredis.util.RedisPool;
import com.example.springbootdemoredis.util.RedisPoolUtil;
import com.example.springbootdemoredis.util.SerializeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

/**
 * @author: ddh
 * @data: 2019/11/20 14:35
 * @description
 **/
@Service
public class HelloServiceImpl {

    final RedisPoolUtil redisPoolUtil;

    @Autowired
    public HelloServiceImpl(RedisPoolUtil redisPoolUtil) {
        this.redisPoolUtil = redisPoolUtil;
    }

//    @Scheduled(cron = "0 0/5 14 * * ?")
    public void hello() {
        System.out.println("hello world!");
        for (int i = 0; i < 3; i++) {
            Data build = Data.builder().userName("zhangsan" + i)
                    .userAddress("jiangxi" + i)
                    .userAge(i).build();
            redisPoolUtil.setValueByte(("user:" + i).getBytes(), SerializeUtil.serialize(build));
            Jedis jedis = RedisPool.getJedis();
//            jedis.set(("user:" + i).getBytes(), SerializeUtil.serialize(build));
//            byte[] bytes = jedis.get(("user:" + i).getBytes());
            byte[] bytes = redisPoolUtil.getValueByte(("user:" + i).getBytes());
            System.out.println(SerializeUtil.unserialize(bytes));
        }
    }
}
