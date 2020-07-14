package com.ddh.learn.concurrency.thread.chapter3;

import java.util.Arrays;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/8 23:37
 * @description 当没有指定线程组时，新建的线程的线程组就默认是创建它的线程的线程组。
 */
public class CreateThread {
    public static void main(String[] args) {

        Thread thread = new Thread();
        thread.start();

//        System.out.println(thread.getThreadGroup().getName());
//        System.out.println(Thread.currentThread().getThreadGroup().getName());
        // 获取当前线程的线程组
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        // 创建线程数组
        Thread[] threads = new Thread[threadGroup.activeCount()];
        // 把线程组中的每个线程复制到指定的数组中
        threadGroup.enumerate(threads);
        Arrays.asList(threads).forEach(System.out::println);
    }
}
