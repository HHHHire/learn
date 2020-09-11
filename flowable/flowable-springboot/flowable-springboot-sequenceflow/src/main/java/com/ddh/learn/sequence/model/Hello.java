package com.ddh.learn.sequence.model;

import org.springframework.stereotype.Component;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/8/14 11:22
 */
@Component
public class Hello {
    public void hello() {
        System.out.println("hello world!!!!!!!!!!");
    }

    public void hello2() {
        System.out.println("hello world222222222222222222");
    }

    public void hello3() {
        System.out.println("默认顺序流");
    }
}
