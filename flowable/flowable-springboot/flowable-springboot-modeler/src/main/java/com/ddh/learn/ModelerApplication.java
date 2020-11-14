package com.ddh.learn;

import org.flowable.spring.boot.FlowableSecurityAutoConfiguration;
//import org.flowable.ui.idm.conf.SecurityConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {"com.ddh.learn"},
        exclude = {FlowableSecurityAutoConfiguration.class})
public class ModelerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModelerApplication.class, args);
    }

}
