package com.ddh.learn.nacosprovider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ddh
 * @data: 2020/6/2 12:17
 * @description s
 */
@RestController
public class TestController {
    @GetMapping("/echo/{s}")
    public String test(@PathVariable String s) {
        System.out.println(s);
        return "Hello Nacos Discovery " + s;
    }

}
