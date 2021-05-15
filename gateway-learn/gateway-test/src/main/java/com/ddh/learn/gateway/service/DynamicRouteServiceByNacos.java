package com.ddh.learn.gateway.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.ddh.learn.gateway.config.nacos.NacosConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/11 13:39
 * @description:
 */
@Component
@DependsOn({"nacosConfig"})
public class DynamicRouteServiceByNacos {

//    @Value("${spring.cloud.nacos.config.server-addr}")
//    private String serverAddr;
//
//    @Value("${spring.cloud.nacos.config.name}")
//    private String name;
//
//    @Value("${spring.cloud.nacos.config.namespace}")
//    private String namespace;
//
//    @Value("${spring.cloud.nacos.config.file-extension}")
//    private String fileExtention;

    @Autowired
    private DynamicRouteService dynamicRouteService;

    @Autowired
    private NacosConfig nacosConfig;

    private ConfigService configService;

    private static Logger logger = LoggerFactory.getLogger(DynamicRouteServiceByNacos.class);

    @PostConstruct
    public void init() {
        ConfigService configService = initConfigService();
        if (configService == null) {
            return;
        }

        try {
            String config = configService.getConfig(nacosConfig.getNAME(), nacosConfig.getGROUP(), nacosConfig.getDEFAULT_TIMEOUT());
            List<RouteDefinition> routeDefinitions = JSON.parseArray(config, RouteDefinition.class);
            routeDefinitions.forEach(r -> dynamicRouteService.add(r));
            listenRouteUpdate();
        } catch (NacosException e) {
            System.out.println("没有读到配置文件");
            e.printStackTrace();
        }

    }

    private void listenRouteUpdate() {
        try {
            configService.addListener(nacosConfig.getNAME(), nacosConfig.getGROUP(), new Listener() {
                @Override
                public Executor getExecutor() {
                    return null;
                }

                @Override
                public void receiveConfigInfo(String configInfo) {
                    logger.info("监听到了:{}", configInfo);
                    List<RouteDefinition> routeDefinitions = JSON.parseArray(configInfo, RouteDefinition.class);
                    dynamicRouteService.updateByList(routeDefinitions);
                }
            });
        } catch (NacosException e) {
            e.printStackTrace();
        }
    }

    private ConfigService initConfigService() {
        Properties properties = new Properties();
        properties.setProperty("serverAddr", nacosConfig.getSERVERADDR());
        properties.setProperty("namespace", nacosConfig.getNAMESPACE());
        try {
            return configService = NacosFactory.createConfigService(properties);
        } catch (NacosException e) {
            e.printStackTrace();
            return null;
        }
    }
}
