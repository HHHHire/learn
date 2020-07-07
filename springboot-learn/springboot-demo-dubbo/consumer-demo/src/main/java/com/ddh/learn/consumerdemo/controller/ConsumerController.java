package com.ddh.learn.consumerdemo.controller;

import com.ddh.learn.api.service.ProviderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: ddh
 * @data: 2020/6/24 10:09
 * @description
 */
@RestController
public class ConsumerController {

    @Resource
    private ProviderService providerService;

    @GetMapping("say")
    public String say() {
        return providerService.say();
    }
}
