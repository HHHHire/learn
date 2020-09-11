package com.ddh.learn.gateway.bean;

import org.springframework.stereotype.Component;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/8/14 13:14
 */
@Component
public class Hello {
    public void hello() {
        System.out.println("hello world");
    }

    public void hello2() {
        System.out.println("this is two ... ");
    }

}
