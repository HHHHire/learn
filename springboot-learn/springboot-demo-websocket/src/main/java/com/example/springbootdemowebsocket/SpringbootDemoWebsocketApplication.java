package com.example.springbootdemowebsocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringbootDemoWebsocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemoWebsocketApplication.class, args);
    }

}
