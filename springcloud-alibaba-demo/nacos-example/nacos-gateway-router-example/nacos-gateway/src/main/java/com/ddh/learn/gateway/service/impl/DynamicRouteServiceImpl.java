package com.ddh.learn.gateway.service.impl;

import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/8/4 16:17
 */
@Service
public class DynamicRouteServiceImpl implements ApplicationEventPublisherAware {
    private final RouteDefinitionWriter routeDefinitionWriter;

    /**
     * 发布事件
     */
    private ApplicationEventPublisher publisher;

    public DynamicRouteServiceImpl(RouteDefinitionWriter routeDefinitionWriter, ApplicationEventPublisher publisher) {
        this.routeDefinitionWriter = routeDefinitionWriter;
        this.publisher = publisher;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    /**
     * 添加路由
     *
     * @param definition 路由信息
     */
    public void addRouter(RouteDefinition definition) {
        // 保存，至内存中
        routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        // 发布
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
    }

    /**
     * 更新路由
     *
     * @param definition 路由信息
     */
    public void updateRouter(RouteDefinition definition) {
        // 先删除
        try {
            routeDefinitionWriter.delete(Mono.just(definition.getId())).subscribe();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 在保存
        addRouter(definition);
    }

    /**
     * 删除路由
     *
     * @param definition 路由信息
     */
    public void deleteRouter(RouteDefinition definition) {
        try {
            routeDefinitionWriter.delete(Mono.just(definition.getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
