package com.ddh.learn.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/8/4 16:06
 */
@Configuration
@PropertySource(value = {"classpath:application.properties","classpath:bootstrap.properties"})
public class GatewayConfig {
    public static Long DEFAULT_TIME_OUT = 30000L;
    public static String NACOS_SERVER_ADDR;
    public static String NACOS_NAMESPACE;
    public static String NACOS_ROUTE_DATA_ID;
    public static String NACOS_ROUTE_GROUP;

    @Value("${spring.cloud.nacos.discovery.server-addr}")
    public void setNacosServerAddr(String serverAddr) {
        NACOS_SERVER_ADDR = serverAddr;
    }

    @Value("${spring.cloud.nacos.discovery.namespace}")
    public void setNacosNamespace(String namespace) {
        NACOS_NAMESPACE = namespace;
    }

    @Value("${nacos.gateway.route.config.data-id}")
    public void setNacosRouteDataId(String dataId) {
        NACOS_ROUTE_DATA_ID = dataId;
    }

    @Value("${nacos.gateway.route.config.group}")
    public void setNacosRouteGroup(String group) {
        NACOS_ROUTE_GROUP = group;
    }
}
