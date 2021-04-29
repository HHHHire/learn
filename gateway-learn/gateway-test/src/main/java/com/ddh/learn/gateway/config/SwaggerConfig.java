package com.ddh.learn.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/4/27 16:03
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean("authApi")
    @Order(value = 1)
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(groupApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ddh.learn.gateway.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo groupApiInfo() {
        return new ApiInfoBuilder()
                .title("auth 服务")
                .description("<div style='font-size:14px;color:red;'>授权，认证服务</div>")
                .contact(new Contact("ddh", "hello", "none"))
                .version("1.0")
                .build();
    }
}
