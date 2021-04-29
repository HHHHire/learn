package com.ddh.learn.gateway.config;

import com.ddh.learn.gateway.filter.TestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/4/24 11:30
 */
@Configuration
public class FilterConfig {
    @Bean
    public TestFilter testFilter() {
        return new TestFilter();
    }
}
