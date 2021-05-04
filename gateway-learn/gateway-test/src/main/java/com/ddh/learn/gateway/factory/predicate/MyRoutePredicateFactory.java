package com.ddh.learn.gateway.factory.predicate;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/4/22 10:19
 * 自定义路由谓词工厂
 */
@Component
public class MyRoutePredicateFactory extends AbstractRoutePredicateFactory<MyRoutePredicateFactory.Config> {

    private String user = "mt";

    public MyRoutePredicateFactory() {
        super(MyRoutePredicateFactory.Config.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(MyRoutePredicateFactory.Config config) {
        return serverWebExchange -> {
            if (config.sources.contains(user)) {
                return true;
            }
            return false;
        };
    }

    public static class Config {
        private List<String> sources = new ArrayList<>();

        public List<String> getSources() {
            return sources;
        }

        public MyRoutePredicateFactory.Config setSources(List<String> sources) {
            this.sources = sources;
            return this;
        }

        public MyRoutePredicateFactory.Config setSources(String... sources) {
            this.sources = Arrays.asList(sources);
            return this;
        }
    }
}
