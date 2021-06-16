package com.ddh.learn.springbootdemopgsql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/12/8 23:16
 */
@RestController
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    private static String str = "";



    @GetMapping("/test")
    public String test() {
//        try {
////            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return "hello world";
    }

    @GetMapping("/get")
    public String testGet() throws InterruptedException {
//        CountDownLatch countDownLatch = new CountDownLatch(5);
        ThreadLocal<String> local = new ThreadLocal<>();
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            while (true) {
//                System.out.println("run..");
                restTemplate.getForEntity("http://localhost:8080/test", String.class);
                try {
                    lock.lock();
                    System.out.println("rest 当前线程名: {}" + Thread.currentThread().getName());
                    str = Thread.currentThread().getName();
                    local.set(str);
                    Thread.sleep(20000);
                    condition.signal();
                    break;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                } finally {
                    lock.unlock();
                }
//                countDownLatch.countDown();
            }
        });
//        countDownLatch.await();
//        System.out.println("po");
        try{
            lock.lock();
            System.out.println("main 当前线程名: " + Thread.currentThread().getName() + ", 变量为: " + str);
            condition.await(30, TimeUnit.SECONDS);
//            if (condition.await(5, TimeUnit.SECONDS)) {
//                System.out.println("end1");
//            } else {
//                System.out.println("end2");
//            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        System.out.println("end 当前线程名: " + Thread.currentThread().getName() + ", 变量为: " + local.get());
        executorService.shutdownNow();

        return "hello";
    }

    @GetMapping("/get1")
    public void testGet2() {
        for (int i = 20; i > 0; i--) {
            try{
                restTemplate.getForEntity("http://localhost:8080/test", String.class);
                Thread.sleep(1000);
            }catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
