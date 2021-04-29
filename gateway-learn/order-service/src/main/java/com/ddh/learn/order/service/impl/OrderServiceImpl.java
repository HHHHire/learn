package com.ddh.learn.order.service.impl;

import com.ddh.learn.order.feign.ProductFeignClient;
import com.ddh.learn.order.model.Order;
import com.ddh.learn.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/4/22 21:23
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductFeignClient productFeignClient;

    @Override
    public Order getOrderById(Long id) {
        return Order.builder()
                .id(id)
                .orderNo("order-001")
                .orderAddress("中国")
                .totalPrice(7777.77)
                .productList(Arrays.asList(productFeignClient.getProduct(1L)))
                .build();
    }
}
