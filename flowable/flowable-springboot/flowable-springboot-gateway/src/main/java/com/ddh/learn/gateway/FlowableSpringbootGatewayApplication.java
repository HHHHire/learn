package com.ddh.learn.gateway;

import org.flowable.engine.RuntimeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author deng
 */
@SpringBootApplication
public class FlowableSpringbootGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowableSpringbootGatewayApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(RuntimeService runtimeService) {
        return args -> {
//            runtimeService.startProcessInstanceByKey("paralle-gateway");
            runtimeService.startProcessInstanceByKey("event-gateway");
        };
    }
}
