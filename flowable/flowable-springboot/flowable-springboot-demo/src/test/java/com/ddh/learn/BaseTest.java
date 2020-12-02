package com.ddh.learn;

import com.ddh.learn.demo.FlowableSpringbootDemoApplication;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/11/15 15:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FlowableSpringbootDemoApplication.class)
@Transactional
public abstract class BaseTest {
    @Before
    public void setUp() {

    }
}
