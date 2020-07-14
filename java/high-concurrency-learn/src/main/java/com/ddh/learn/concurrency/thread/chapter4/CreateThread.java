package com.ddh.learn.concurrency.thread.chapter4;

import java.util.Optional;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/9 22:51
 * @desc
 */
public class CreateThread {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            Optional.of("hello").ifPresent(System.out::println);
            try {
                Thread.sleep(100_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");
        t.start();

        Optional.of(t.getName()).ifPresent(System.out::println);
        // 获取线程的id，由此可以知道在创建它之前有几个线程已经创建
        Optional.of(t.getId()).ifPresent(System.out::println);
        // 获取线程优先级
        Optional.of(t.getPriority()).ifPresent(System.out::println);
    }
}
