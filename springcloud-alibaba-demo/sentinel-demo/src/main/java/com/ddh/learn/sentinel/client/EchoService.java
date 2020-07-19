package com.ddh.learn.sentinel.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/18 0:36
 */
@FeignClient(name = "nacos-provider", fallback = EchoServiceFallback.class, configuration = FeignConfiguration.class)
public interface EchoService {
    @GetMapping("/echo/{s}")
    String test(@PathVariable String s);
}
