package com.ddh.learn.gateway.config.nacos;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/11 13:33
 * @description:
 */
@Configuration
@ConfigurationProperties(prefix = "nacos.router")
@Data
public class NacosConfig {
    public String SERVERADDR;
    public String NAMESPACE;
    public String NAME;
    public String FILEEXTENTION;
    public String GROUP;
    public final long DEFAULT_TIMEOUT = 10000;

//    @Value("${spring.cloud.nacos.config.server-addr}")
//    public static void setSERVERADDR(String SERVERADDR) {
//        NacosConfig.SERVERADDR = SERVERADDR;
//    }
//
//    @Value("${spring.cloud.nacos.config.namespace}")
//    public static void setNAMESPACE(String NAMESPACE) {
//        NacosConfig.NAMESPACE = NAMESPACE;
//    }
//
//    @Value("${spring.cloud.nacos.config.name}")
//    public static void setNAME(String NAME) {
//        NacosConfig.NAME = NAME;
//    }
//
//    @Value("${spring.cloud.nacos.config.file-extension}")
//    public static void setFILEEXTENTION(String FILEEXTENTION) {
//        NacosConfig.FILEEXTENTION = FILEEXTENTION;
//    }
}
