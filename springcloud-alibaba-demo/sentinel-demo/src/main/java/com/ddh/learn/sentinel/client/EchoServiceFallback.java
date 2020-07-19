package com.ddh.learn.sentinel.client;

import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/18 0:40
 */
public class EchoServiceFallback implements EchoService {
    @Override
    public String test(@PathVariable String s) {
        return "echo fallback";
    }
}
