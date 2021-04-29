package com.ddh.learn.gateway.config;

import com.ddh.learn.gateway.factory.filter.PostGatewayFilterFactory;
import com.ddh.learn.gateway.factory.filter.PreGatewayFilterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/4/24 0:47
 * 将自定义工厂注入到容器中
 */
@Configuration
public class FilterFactoryConfig {
    @Bean
    public PreGatewayFilterFactory preGatewayFilterFactory() {
        return new PreGatewayFilterFactory();
    }

    @Bean
    public PostGatewayFilterFactory postGatewayFilterFactory() {
        return new PostGatewayFilterFactory();
    }
}
