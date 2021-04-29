package com.ddh.learn.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/4/22 23:03
 */
public class ServiceTest {
//    @Value("${spring.application.name}")
//    private String name;

//    @Autowired
//    private Environment env;

    public String getName()  {
        try {
            Properties properties = PropertiesLoaderUtils.loadAllProperties("application.yml");
            String property = properties.getProperty("test_name");
            return property;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
