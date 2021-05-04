package com.ddh.learn.order.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/4/22 21:24
 */
@RestController
public class OrderController {

    @GetMapping("/order/{id}")
    public Mono<String> getOrder(@PathVariable("id") Long id) {
        return Mono.just("hello order service2");
    }
}
