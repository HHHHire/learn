package com.ddh.learn.order.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/4/22 21:19
 */
@Data
@Builder
@AllArgsConstructor
public class Order implements Serializable {
    private Long id;
    private String orderNo;
    private String orderAddress;
    private Double totalPrice;
    private List<Product> productList;
}
