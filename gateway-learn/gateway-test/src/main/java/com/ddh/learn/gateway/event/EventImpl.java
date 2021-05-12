package com.ddh.learn.gateway.event;

import org.springframework.stereotype.Component;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/10 20:19
 * @description:
 */
@Component
public class EventImpl implements Event {
    @Override
    public void onBeforeUpload() {
        System.out.println("onBeforeUpload");
    }

    @Override
    public void onBeforeDownload() {
        System.out.println("onBeforeDownload");
    }
}
