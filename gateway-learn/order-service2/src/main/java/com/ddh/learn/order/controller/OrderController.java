package com.ddh.learn.order.controller;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/4/22 21:24
 */
@RestController
public class OrderController {

    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable("id") Long id) {
        return "hello order service2";
    }

    @PostMapping("/order")
    public String postOrder(@RequestParam String name, @RequestParam Long id) {
        return name;
    }
}
