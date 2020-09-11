package com.ddh.learn.sequence;

import com.ddh.learn.sequence.model.Order;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;

/**
 * @author deng
 */
@SpringBootApplication
public class FlowableSpringbootSequenceflowApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowableSpringbootSequenceflowApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(RuntimeService runtimeService) {
        return args -> {
            Order build = Order.builder().id("001")
                    .price(400)
                    .counts(20).build();
            HashMap<String, Object> map = new HashMap<>(1);
            map.put("order", build);
            runtimeService.startProcessInstanceByKey("oneTaskProcess", map);
        };
    }
}
