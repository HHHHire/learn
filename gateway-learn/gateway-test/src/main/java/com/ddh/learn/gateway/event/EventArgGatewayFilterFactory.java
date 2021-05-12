package com.ddh.learn.gateway.event;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;

import java.util.Arrays;
import java.util.List;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/10 19:50
 * @description:
 */
public class EventArgGatewayFilterFactory extends AbstractGatewayFilterFactory<EventArgGatewayFilterFactory.Config> {

    private static final String ARG = "arg";

    public EventArgGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(ARG);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            exchange.getAttributes().put("EVENT_ARG", config.getArg());
            return chain.filter(exchange);
        });
    }

    public static class Config {
        private String arg;

        public String getArg() {
            return arg;
        }

        public void setArg(String arg) {
            this.arg = arg;
        }
    }


}
