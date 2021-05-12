package com.ddh.learn.gateway.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/10 19:49
 * @description:
 */
@Component
public class PreEventFilter implements GlobalFilter, Ordered {

    @Autowired
    private Event event;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String eventArg = exchange.getAttributes().get("EVENT_ARG").toString();
        switch (EventEnum.getByDesc(eventArg)) {
            case ON_BEFORE_UPLOAD:
                event.onBeforeUpload();
                break;
            case ON_AFTER_DOWNLOAD:
                event.onBeforeDownload();
                break;
            default:
                break;

        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 100;
    }
}
