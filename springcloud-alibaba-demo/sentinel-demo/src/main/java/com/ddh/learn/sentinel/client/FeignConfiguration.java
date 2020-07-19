package com.ddh.learn.sentinel.client;

import org.springframework.context.annotation.Bean;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/18 0:42
 */
public class FeignConfiguration {
    @Bean
    public EchoServiceFallback echoServiceFallback() {
        return new EchoServiceFallback();
    }
}
