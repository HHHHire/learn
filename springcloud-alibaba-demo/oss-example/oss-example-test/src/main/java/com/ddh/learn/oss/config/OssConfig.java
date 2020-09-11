package com.ddh.learn.oss.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/9/9 23:02
 */
@ConfigurationProperties(prefix = "oss")
@Configuration
@Data
public class OssConfig {
    private String bucketName;
    private String accessKeyId;
    private String accessKeySecret;
    private String endpoint;

    @Bean(value = "ossClient")
    public OSS getOssClient() {
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }
}
