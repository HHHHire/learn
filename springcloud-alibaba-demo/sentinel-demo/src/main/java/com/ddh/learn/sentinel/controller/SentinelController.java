package com.ddh.learn.sentinel.controller;

import com.alibaba.csp.sentinel.adapter.reactor.SentinelReactorTransformer;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.ddh.learn.sentinel.client.EchoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/17 17:16
 */
@RestController
public class SentinelController {

    @Resource
    private EchoService echoService;

    @GetMapping("/hello")
    @SentinelResource("hello")
    public Mono<String> hello() {
        return Mono.just("simple string")
                .transform(new SentinelReactorTransformer<>("otherResourceName"));
    }

    @GetMapping("/feign")
    public String feign() {
        echoService.test("lihua");
        return null;
    }
}
