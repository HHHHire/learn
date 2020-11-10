package com.ddh.learn.concurrency.thread.safe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/9/28 14:22
 */
public class ThreadUnsafeDemo {
    private AtomicInteger counts= new AtomicInteger();

    public void setCounts() {
        counts.incrementAndGet();
    }

    public int getCounts() {
        return counts.get();
    }

    public static void main(String[] args) throws InterruptedException {
        int threadSize = 1000;
        ThreadUnsafeDemo threadUnsafeDemo = new ThreadUnsafeDemo();
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < threadSize; i++) {
            executorService.execute(() -> {
                threadUnsafeDemo.setCounts();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println(threadUnsafeDemo.getCounts());
    }
}
