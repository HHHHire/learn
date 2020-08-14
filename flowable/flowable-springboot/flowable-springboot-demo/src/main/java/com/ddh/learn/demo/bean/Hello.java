package com.ddh.learn.demo.bean;

import org.springframework.stereotype.Component;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/8/14 10:02
 */
@Component
public class Hello {
    public void hello() {
        System.out.println("hello world");
    }

    public void catchEvent() {
        System.out.println("success catch event!!!!!!");
    }
}
