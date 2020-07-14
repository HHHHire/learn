package com.ddh.learn.concurrency.thread.chapter4;

import java.util.Optional;
import java.util.stream.IntStream;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/9 23:16
 * @desc 当前线程会在等待该线程(t1,t2)执行完毕后在开始去执行，而t1、t2则是并行执行
 */
public class ThreadJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> IntStream.range(1, 1000)
                .forEach(i -> System.out.println(Thread.currentThread().getName() + "--" + i)));
        Thread t2 = new Thread(() -> IntStream.range(1, 1000)
                .forEach(i -> System.out.println(Thread.currentThread().getName() + "--" + i)));
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        Optional.of("All of task is finish done").ifPresent(System.out::println);
        IntStream.range(1, 1000).forEach(i -> System.out.println(Thread.currentThread().getName() + "--" + i));

        // 自己等待自己死亡，陷入死循环
//        Thread.currentThread().join();
    }
}
