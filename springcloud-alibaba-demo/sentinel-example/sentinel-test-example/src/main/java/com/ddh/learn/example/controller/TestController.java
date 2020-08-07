package com.ddh.learn.example.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/8/6 9:57
 */
@RestController
public class TestController {

    private int couts = 1;

    @GetMapping("/test")
    public String test() {
        return "test success";
    }

    @GetMapping("/test2")
    public String test2() {
        return "test2 success";
    }

    @GetMapping("/exception")
    public String exception() {
        if (couts++ % 3 == 0) {
            throw new RuntimeException();
        }
        return "success";
    }

    @GetMapping("/test3")
    @SentinelResource("test3")
    public String test3(@RequestParam(required = false) String name, @RequestParam(required = false) Integer age) {
        return name + age;
    }
}
