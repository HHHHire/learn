package com.ddh.learn.gateway.event;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/10 19:58
 * @description:
 */
@Configuration
public class FilterFactoryConfig2 {
    @Bean
    public EventArgGatewayFilterFactory eventArgGatewayFilterFactory() {
        return new EventArgGatewayFilterFactory();
    }
}
