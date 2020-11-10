package com.ddh.learn.concurrency.thread.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/9/28 11:14
 */
public class ReentrantLockDemo {
    private Lock lock = new ReentrantLock();

    public void func() {
        lock.lock();
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockDemo reentrantLockDemo = new ReentrantLockDemo();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(reentrantLockDemo::func);
        executorService.execute(reentrantLockDemo::func);
    }
}
