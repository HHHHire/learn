package com.example.taskdemo.service;

import org.springframework.stereotype.Service;

/**
 * @author ddh
 * @date 2019/8/1 10:53
 * @description
 **/
@Service
public class AsynService {

    public void hello(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("处理数据中");
    }
}
