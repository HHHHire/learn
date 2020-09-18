package com.ddh.learn.oss;

import com.aliyun.oss.OSS;
import com.ddh.learn.oss.config.OssConfig;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/9/11 16:00
 * 文件控制
 */
public class FileControTest {
    @Autowired
    private OssConfig config;
    @Autowired
    private OSS ossClinet;

    private String bucketName = "hhhhire";

    @Test
    public void test() {

    }
}
