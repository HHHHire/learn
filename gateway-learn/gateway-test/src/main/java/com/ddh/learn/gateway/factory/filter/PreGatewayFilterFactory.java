package com.ddh.learn.gateway.factory.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.util.Arrays;
import java.util.List;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/4/24 0:24
 * 前置过滤器
 */
public class PreGatewayFilterFactory extends AbstractGatewayFilterFactory<PreGatewayFilterFactory.Config> {

    private static final String TEMP = "temp";

    public PreGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(TEMP);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
//            ServerHttpRequest.Builder mutate = exchange.getRequest().mutate();
//            ServerHttpRequest request = exchange.getRequest();
//            return chain.filter(exchange.mutate().request(request).build());
            System.out.println(config.getTemp());
            System.out.println("hello world PreGatewayFilterFactory");
            return chain.filter(exchange);
        };
    }

    // 参数
    public static class Config{
        private String temp;

        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }
    }
}
