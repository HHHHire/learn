package com.ddh.learn.order.controller;

import com.ddh.learn.order.model.Order;
import com.ddh.learn.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/4/22 21:24
 */
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/order/{id}")
    public Order getOrder(@PathVariable("id") Long id) {
        return orderService.getOrderById(id);
    }
}
