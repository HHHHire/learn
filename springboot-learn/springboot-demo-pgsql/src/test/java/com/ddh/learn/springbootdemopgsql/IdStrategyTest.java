package com.ddh.learn.springbootdemopgsql;

import com.ddh.learn.springbootdemopgsql.dao.OrderRepository;
import com.ddh.learn.springbootdemopgsql.model.po.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/28 17:15
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootDemoPgsqlApplication.class)
public class IdStrategyTest {

    @Autowired
    private OrderRepository repository;

    @Test
    public void test1() {
        Order build = Order.builder().username("张三").addr("上海").build();
        repository.save(build);
    }
}
