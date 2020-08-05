package com.ddh.learn.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@RefreshScope
public class NacosConfigDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosConfigDemoApplication.class, args);
    }

    @Value("${share.config1:}")
    public String shareConfig1;

    @Value("${share.config2:}")
    public String shareConfig2;

    @GetMapping("/config1")
    public String getConfig1() {
        return "config1: " + shareConfig1;
    }

    @GetMapping("/config2")
    public String getConfig2() {
        return "config2: " + shareConfig2;
    }

}
