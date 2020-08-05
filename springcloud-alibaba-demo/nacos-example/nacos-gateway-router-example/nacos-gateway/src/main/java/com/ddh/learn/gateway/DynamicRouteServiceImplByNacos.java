package com.ddh.learn.gateway;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.ddh.learn.gateway.config.GatewayConfig;
import com.ddh.learn.gateway.service.impl.DynamicRouteServiceImpl;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/8/4 16:19
 */
@Component
@DependsOn("gatewayConfig")
public class DynamicRouteServiceImplByNacos {
    private final DynamicRouteServiceImpl dynamicRouteService;
    private ConfigService configService;

    public DynamicRouteServiceImplByNacos(DynamicRouteServiceImpl dynamicRouteService) {
        this.dynamicRouteService = dynamicRouteService;
    }

    @PostConstruct
    public void init() {
        configService = initConfigService();
        if (configService == null) {
            return;
        }
        try {
            // 获取网关当前配置信息
            String configInfo = configService.getConfig(GatewayConfig.NACOS_ROUTE_DATA_ID, GatewayConfig.NACOS_ROUTE_GROUP, GatewayConfig.DEFAULT_TIME_OUT);
            List<RouteDefinition> routeDefinitions = JSON.parseArray(configInfo, RouteDefinition.class);
            routeDefinitions.forEach(dynamicRouteService::addRouter);
        } catch (NacosException e) {
            e.printStackTrace();
        }
        dynamicRouteByNacosListener(GatewayConfig.NACOS_ROUTE_DATA_ID, GatewayConfig.NACOS_ROUTE_GROUP);
    }

    /**
     * 监听nacos下发的路由配置
     *
     * @param dataId data id
     * @param group  group
     */
    private void dynamicRouteByNacosListener(String dataId, String group) {
        try {
            configService.addListener(dataId, group, new Listener() {
                @Override
                public Executor getExecutor() {
                    return null;
                }

                @Override
                public void receiveConfigInfo(String configInfo) {
                    List<RouteDefinition> routeDefinitions = JSON.parseArray(configInfo, RouteDefinition.class);
                    routeDefinitions.forEach(dynamicRouteService::updateRouter);
                }
            });
        } catch (NacosException e) {
            e.printStackTrace();
        }
    }

    private ConfigService initConfigService() {
        Properties properties = new Properties();
        properties.setProperty("serverAddr", GatewayConfig.NACOS_SERVER_ADDR);
        properties.setProperty("namespace", GatewayConfig.NACOS_NAMESPACE);
        try {
            return NacosFactory.createConfigService(properties);
        } catch (NacosException e) {
            e.printStackTrace();
        }
        return null;
    }
}
