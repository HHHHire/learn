package com.ddh.learn.sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath:config/dubbo-provider.xml"})
public class ServerSsoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerSsoApplication.class, args);
    }

}
