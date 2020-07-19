package com.ddh.learn.consumer;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/19 0:15
 */
@Service
public class ReceiveService {
    @StreamListener("input1")
    public void receiveInput1(String revieveMsg) {
        System.out.println("input1 receive: " + revieveMsg);
    }
}
