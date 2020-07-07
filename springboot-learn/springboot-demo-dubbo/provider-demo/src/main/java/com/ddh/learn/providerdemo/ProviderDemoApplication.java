package com.ddh.learn.providerdemo;

import com.alibaba.dubbo.config.ServiceConfig;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.ddh.learn.api.service.ProviderService;
import com.ddh.learn.providerdemo.service.ProviderServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EnableDubbo
@ImportResource({"classpath:config/spring-dubbo-provider.xml"})
public class ProviderDemoApplication {

    public static void main(String[] args) {
//        ServiceConfig<ProviderService> con = new ServiceConfig<>();
//        con.setTimeout(5000);
        SpringApplication.run(ProviderDemoApplication.class, args);

    }

}
