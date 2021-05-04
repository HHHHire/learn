package com.ddh.learn.gateway.controller;

import com.ddh.learn.gateway.model.GatewayFilterDefinition;
import com.ddh.learn.gateway.model.GatewayPredicateDefinition;
import com.ddh.learn.gateway.model.GatewayRouteDefinition;
import com.ddh.learn.gateway.service.DynamicRouteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/4/27 13:39
 */
@RestController
@RequestMapping("/route")
@Api(tags = "DynamicRouteController", description = "动态路由")
public class DynamicRouteController {
    @Autowired
    private DynamicRouteService dynamicRouteService;

    @Autowired
    private RouteDefinitionLocator routeDefinitionLocator;

    @Autowired
    private RouteLocator routeLocator;

    /**
     * 获取所有路由信息
     */
    @ApiOperation(value = "获取所有路由信息", httpMethod = "GET")
    @GetMapping("/all")
    public Flux<RouteDefinition> getRouteDefinitions() {
        return routeDefinitionLocator.getRouteDefinitions();
    }

    /**
     * 获取所有路由信息
     */
    @ApiOperation(value = "获取所有路由信息V2", httpMethod = "GET")
    @GetMapping("/all/v2")
    public Flux<Route> getRouteDefinitionsV2() {
        Flux<Route> routes = routeLocator.getRoutes();
        System.out.println(routes);
        return null;
    }

    /**
     * 增加路由
     */
    @ApiOperation(value = "增加路由", httpMethod = "POST")
    @PostMapping("/add")
    public String addRoute(@RequestBody GatewayRouteDefinition gatewayRouteDefinition) {
        String flag = "fail";
        RouteDefinition routeDefinition = convertRouteDefinition(gatewayRouteDefinition);
        flag = dynamicRouteService.add(routeDefinition);
        return flag;

    }

    /**
     * 删除路由
     */
    @ApiOperation(value = "删除路由", httpMethod = "DELETE")
    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Object>> delete(@PathVariable String id) {
        return dynamicRouteService.delete(id);
    }

    /**
     * 更新路由
     */
    @ApiOperation(value = "更新路由", httpMethod = "PUT")
    @PutMapping("/update")
    public String update(@RequestBody GatewayRouteDefinition gatewayRouteDefinition) {
        RouteDefinition routeDefinition = convertRouteDefinition(gatewayRouteDefinition);
        return dynamicRouteService.update(routeDefinition);
    }



    /**
     * 转换对象
     */
    private RouteDefinition convertRouteDefinition(GatewayRouteDefinition gatewayRouteDefinition) {
        RouteDefinition routeDefinition = new RouteDefinition();
        routeDefinition.setId(gatewayRouteDefinition.getId());
        routeDefinition.setOrder(gatewayRouteDefinition.getOrder());

        // 设置断言
        List<GatewayPredicateDefinition> predicates = gatewayRouteDefinition.getPredicates();
        List<PredicateDefinition> predicateDefinitions = predicates.stream().map(p -> {
            PredicateDefinition predicateDefinition = new PredicateDefinition();
            predicateDefinition.setName(p.getName());
            predicateDefinition.setArgs(p.getArgs());
            return predicateDefinition;
        }).collect(Collectors.toList());
        routeDefinition.setPredicates(predicateDefinitions);

        // 设置过滤器
        List<GatewayFilterDefinition> filters = gatewayRouteDefinition.getFilters();
        List<FilterDefinition> filterDefinitions = filters.stream().map(f -> {
            FilterDefinition filterDefinition = new FilterDefinition();
            filterDefinition.setName(f.getName());
            filterDefinition.setArgs(f.getArgs());
            return filterDefinition;
        }).collect(Collectors.toList());
        routeDefinition.setFilters(filterDefinitions);

        // 设置 uri
        URI uri;
        if (gatewayRouteDefinition.getUri().startsWith("http")) {
            uri = UriComponentsBuilder.fromHttpUrl(gatewayRouteDefinition.getUri()).build().toUri();
        } else {
            uri = URI.create(gatewayRouteDefinition.getUri());
        }
        routeDefinition.setUri(uri);

        return routeDefinition;
    }
}
