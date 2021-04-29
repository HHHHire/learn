package com.ddh.learn.order.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/4/22 14:52
 */
@Data
@Builder
@AllArgsConstructor
public class Product {
    private Long id;
    private String productName;
    private Integer productNum;
    private Double productPrice;
}
