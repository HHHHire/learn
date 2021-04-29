package com.ddh.learn.order.service;

import com.ddh.learn.order.model.Order;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/4/22 21:23
 */
public interface OrderService {
    Order getOrderById(Long id);
}
