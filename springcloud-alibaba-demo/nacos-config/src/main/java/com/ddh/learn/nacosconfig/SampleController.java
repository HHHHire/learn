package com.ddh.learn.nacosconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/8/5 13:56
 */
@RestController
@RefreshScope
public class SampleController {
    @Value("${user.name:}")
    private String userName;

    @Value("${user.age:1}")
    private int age;

    @Value("${name:}")
    private String name;

    @GetMapping("/hello")
    public String hello() {
        return "hello nacos config username: " + userName + " age: " + age;
    }

    @GetMapping("/name")
    public String getName() {
        return "nacos-test.yml name is :" + name;
    }
}
