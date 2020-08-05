package com.ddh.learn.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosGatewayConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosGatewayConsumerApplication.class, args);
    }

    @RestController
    class Consumer {
        @GetMapping("/consumer")
        public String helloConsumer() {
            return "hello Consumer!";
        }
    }

}
