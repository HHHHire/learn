package com.ddh.learn.gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/4/27 13:40
 */
@Service
public class DynamicRouteService implements ApplicationEventPublisherAware {
    @Autowired
    private RouteDefinitionWriter routeDefinitionWriter;

    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    /**
     * 增加路由
     */
    public String add(RouteDefinition routeDefinition) {
        routeDefinitionWriter.save(Mono.just(routeDefinition)).subscribe();
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
        return "success";
    }

    /**
     * 删除路由
     */
    public Mono<ResponseEntity<Object>> delete(String id) {
        return this.routeDefinitionWriter.delete(Mono.just(id)).then(Mono.defer(() -> {
            return Mono.just(ResponseEntity.ok().build());
        })).onErrorResume((t) -> {
            return t instanceof NotFoundException;
        }, (t) -> {
            return Mono.just(ResponseEntity.notFound().build());
        });
    }

    /**
     * 更新路由
     */
    public String update(RouteDefinition routeDefinition) {
        try {
            delete(routeDefinition.getId());
        } catch (Exception e) {
            return "更新路由失败" + routeDefinition.getId();
        }

        try {
            add(routeDefinition);
            return "success";
        } catch (Exception e) {
            return "fail";
        }
    }

}
