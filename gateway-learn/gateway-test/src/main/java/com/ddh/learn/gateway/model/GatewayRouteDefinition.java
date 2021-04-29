package com.ddh.learn.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/4/27 13:33
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GatewayRouteDefinition {
    private String id;
    private List<GatewayPredicateDefinition> predicates;
    private List<GatewayFilterDefinition> filters;
    private String uri;
    private int order = 0;
}
