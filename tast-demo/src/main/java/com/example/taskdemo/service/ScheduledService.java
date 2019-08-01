package com.example.taskdemo.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author ddh
 * @date 2019/8/1 11:20
 * @description
 **/
@Service
public class ScheduledService {

    /**
     * on the second as well as minute, hour, day of month, month and day of week.
     * 例子："0 * * * * MON-FRI"
     * cron (秒，分，时，日，月，星期几)
     * 每个整分钟执行
     */
    @Scheduled(cron = "0 * * * * MON-FRI")
    public void hello(){
        System.out.println("hello ....");
    }
}
