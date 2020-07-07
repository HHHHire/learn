package com.ddh.learn.providerdemo.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.ddh.learn.api.service.ProviderService;

/**
 * @author: ddh
 * @data: 2020/6/24 9:53
 * @description
 */
@Service(version = "1.0.0", timeout = 1000)
//@Component
public class ProviderServiceImpl implements ProviderService {
    @Override
    public String say() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello word111";
    }
}
