package com.ddh.learn.gateway.config;

import com.ddh.learn.gateway.filter.TestFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/4/22 21:59
 */
@Configuration
public class RouteConfig {
    private List<Route.AsyncBuilder> routes = new ArrayList<>();

    @Bean
    public RouteLocator getRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("path_route", r -> r.path("/product/**")
                        .uri("http://localhost:8502").filter(new TestFilter()))
                .build();
    }
}
