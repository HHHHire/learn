package com.ddh.learn.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/8/14 13:13
 */
@Data
@Builder
@AllArgsConstructor
public class Order {
    private Integer price;
    private Integer counts;
}
