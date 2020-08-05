package com.ddh.learn.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosGatewayProviderExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosGatewayProviderExampleApplication.class, args);
    }

    @RestController
    class EchoController {
        @GetMapping("/echo/{str}")
        public String echo(@PathVariable String str) {
            return "you say: " + str;
        }

        @GetMapping("/divide")
        public String divide(@RequestParam("a") Integer a, @RequestParam("b") Integer b) {
            return String.valueOf(a / b);
        }
    }

}
