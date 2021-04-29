package com.ddh.learn.gateway.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/4/24 11:28
 */
public class TestFilter implements GatewayFilter, Ordered {
    private static final Log log = LogFactory.getLog(GatewayFilter.class);
    private static final String ELAPSED_TIME_BEGIN = "elapsedTimeBegin";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        exchange.getAttributes().put(ELAPSED_TIME_BEGIN, System.currentTimeMillis());
        System.out.println("hello world TestFilter");
//        return chain.filter(exchange).then(
//                Mono.fromRunnable(() -> {
//                    Long startTime = exchange.getAttribute(ELAPSED_TIME_BEGIN);
//                    if (startTime != null) {
//                        log.info(exchange.getRequest().getURI().getRawPath() + ": " + (System.currentTimeMillis() - startTime) + "ms");
//                    }
//                })
//        );
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
