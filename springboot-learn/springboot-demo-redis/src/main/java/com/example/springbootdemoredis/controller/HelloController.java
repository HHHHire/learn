package com.example.springbootdemoredis.controller;

import com.example.springbootdemoredis.service.HelloServiceImpl;
import com.example.springbootdemoredis.util.RedisPoolUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ddh
 * @data: 2019/11/20 14:33
 * @description
 **/
@RestController
@RequestMapping("/hello")
public class HelloController {
    final RedisPoolUtil redisPoolUtil;
    final HelloServiceImpl helloService;

    public HelloController(RedisPoolUtil redisPoolUtil, HelloServiceImpl helloService) {
        this.redisPoolUtil = redisPoolUtil;
        this.helloService = helloService;
    }

    @GetMapping
    public String hello() {
        helloService.hello();
        return "hello world";
    }
}
