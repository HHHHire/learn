package com.ddh.learn.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath:config/dubbo-consumer-web2.xml"})
public class ServerWeb2Application {

    public static void main(String[] args) {
        SpringApplication.run(ServerWeb2Application.class, args);
    }

}
