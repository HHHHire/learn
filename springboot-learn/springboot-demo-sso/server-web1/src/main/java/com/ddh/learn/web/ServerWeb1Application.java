package com.ddh.learn.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath:config/dubbo-consumer.xml"})
public class ServerWeb1Application {

    public static void main(String[] args) {
        SpringApplication.run(ServerWeb1Application.class, args);
    }

}
