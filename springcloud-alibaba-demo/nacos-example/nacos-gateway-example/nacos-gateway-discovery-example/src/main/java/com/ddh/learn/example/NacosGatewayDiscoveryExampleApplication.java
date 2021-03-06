package com.ddh.learn.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosGatewayDiscoveryExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosGatewayDiscoveryExampleApplication.class, args);
    }

}
