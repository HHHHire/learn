package com.ddh.learn.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/4/27 13:34
 */
@Data
@Builder
@AllArgsConstructor
public class GatewayFilterDefinition {
    private String name;
    private Map<String, String> args;
}
