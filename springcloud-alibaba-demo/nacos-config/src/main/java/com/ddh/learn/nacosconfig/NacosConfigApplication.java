package com.ddh.learn.nacosconfig;

import ch.qos.logback.core.util.TimeUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class NacosConfigApplication {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(NacosConfigApplication.class, args);
        // 读取nacos-config的配置文件信息
        while (true) {
            String name = applicationContext.getEnvironment().getProperty("user.name");
            String age = applicationContext.getEnvironment().getProperty("user.age");
            String env = applicationContext.getEnvironment().getProperty("current.env");
            System.out.println(env + " : " + name + ":" + age);
            TimeUnit.SECONDS.sleep(2);
        }

    }

}
