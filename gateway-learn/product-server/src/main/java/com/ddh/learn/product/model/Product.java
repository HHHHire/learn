package com.ddh.learn.product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/4/22 14:52
 */
@Data
@Builder
@AllArgsConstructor
public class Product implements Serializable {
    private Long id;
    private String productName;
    private Integer productNum;
    private Double productPrice;
}
