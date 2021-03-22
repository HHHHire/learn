package com.ddh.learn.utils.http;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/3/14 0:18
 */
@Configuration
public class HttpConfig {
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
