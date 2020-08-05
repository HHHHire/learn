//package com.ddh.learn.nacosconfig;
//
//import com.alibaba.cloud.nacos.NacosConfigProperties;
//import com.alibaba.nacos.api.config.ConfigFactory;
//import com.alibaba.nacos.api.config.ConfigService;
//import com.alibaba.nacos.api.config.listener.Listener;
//import com.alibaba.nacos.api.exception.NacosException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.io.StringReader;
//import java.util.Properties;
//import java.util.concurrent.Executor;
//
///**
// * 使用NacosConfigProperties一直没有成功，还是用configService
// * @author: dengdh@dist.com.cn
// * @data: 2020/8/5 14:03
// */
//@Component
//public class SampleRunner implements ApplicationRunner {
//
//    @Value("${user.name:}")
//    private String userName;
//
//    @Value("${user.age:1}")
//    private int age;
//
//    private final NacosConfigProperties configProperties;
//
//    private ConfigService configService;
//
//    public SampleRunner(NacosConfigProperties configProperties) {
//        this.configProperties = configProperties;
//    }
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//
//        configService = initConfigServer();
//        if (configService == null) {
//            return;
//        }
//
////        String config = configService.getConfig("nacos-config.properties", "DEFAULT_GROUP", 30000L);
////        System.out.println("config info : " + config);
//
//        System.out.println(String.format("username=%s, age=%d", userName, age));
//
////        configProperties.setNamespace("6e3b5a1c-9715-4d99-adc9-12c7836145ad");
////        String conf = configProperties.configServiceInstance().getConfig("nacos-config.properties", "DEFAULT_GROUP", 30000L);
////        System.out.println("conf info is : " + conf);
//
//        configProperties.configServiceInstance().addListener("nacos-config.properties",
//                "DEFAULT_GROUP", new Listener() {
//                    @Override
//                    public Executor getExecutor() {
//                        return null;
//                    }
//
//                    @Override
//                    public void receiveConfigInfo(String configInfo) {
//                        Properties properties = new Properties();
//                        try {
//                            properties.load(new StringReader(configInfo));
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        System.out.println("changed: " + properties);
//                    }
//                });
//    }
//
//    private ConfigService initConfigServer() {
//        Properties properties = new Properties();
//        properties.setProperty("serverAddr", "127.0.0.1:8848");
//        properties.setProperty("namespace", "6e3b5a1c-9715-4d99-adc9-12c7836145ad");
//        try {
//            return ConfigFactory.createConfigService(properties);
//        } catch (NacosException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}
