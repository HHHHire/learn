package com.ddh.learn.redis.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/4/22 14:14
 */
@Component
public class TestUtil {
    @Value("${test.value}")
    public String value;

}
