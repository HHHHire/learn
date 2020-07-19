package com.ddh.learn.sentinel.template;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/18 15:39
 */
public class SentinelRestTemplate {
    @Bean
    @com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate(blockHandler = "handleException",
            blockHandlerClass = ExceptionUtil.class)
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
