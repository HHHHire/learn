package com.ddh.learn.consumerdemo;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.alibaba.dubbo.registry.Registry;
import com.alibaba.dubbo.registry.RegistryFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EnableDubbo
@ImportResource({"classpath:/config/spring-dubbo-consumer.xml"})
public class ConsumerDemoApplication {

    public static void main(String[] args) {
//        RegistryFactory registryFactory = ExtensionLoader.getExtensionLoader(RegistryFactory.class).getAdaptiveExtension();
//        Registry registry = registryFactory.getRegistry(URL.valueOf("zookeeper://127.0.0.1:2181"));
//        registry.register(URL.valueOf("condition://0.0.0.0/com.ddh.learn.api.service.ProviderService?version=1.0.0&name=路由规则2&category=routers&dynamic=false&rule=" + URL.encode(" => host != 192.168.99.1")));
        SpringApplication.run(ConsumerDemoApplication.class, args);
    }
}
