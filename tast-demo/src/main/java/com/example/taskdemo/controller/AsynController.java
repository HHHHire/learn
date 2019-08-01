package com.example.taskdemo.controller;

import com.example.taskdemo.service.AsynService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ddh
 * @date 2019/8/1 10:55
 * @description
 **/
@RestController
public class AsynController {

    @Autowired
    AsynService asynService;

    //这是一个异步方法
    @Async
    @GetMapping("/hello")
    public String hello(){  
        asynService.hello();
        return "success";
    }
}
