package com.ddh.learn.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosGatewayProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosGatewayProviderApplication.class, args);
    }

    @RestController
    class Provider {
        @GetMapping("/provider")
        public String helloProvider() {
            return "hello Provider!";
        }
    }
}
