package com.ddh.learn.produce;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/8/7 9:54
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RocketmqTest {
    @Autowired
    private RocketMQTemplate template;

    @Test
    public void test1() {
        Master master = Master.builder().name("zhang").age(14).build();

        template.convertAndSend("test-topic", master);
    }
}
