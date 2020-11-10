package com.ddh.learn.concurrency.thread.lock.spinlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/9/30 9:08
 * @desc 自旋锁，实现可重入锁
 */
public class ReentrantSpinLock {
    private AtomicReference<Thread> cas = new AtomicReference<>();
    private int counts = 0;

    public void lock() {
        Thread current = Thread.currentThread();
        System.out.println("lock " + current.getName());
        // 如果当前线程已经获取到了锁，可重入
        if (current == cas.get()) {
            System.out.println(++counts);
            return;
        }

        // 如果没有获取到锁，通过CAS自旋
        while (!cas.compareAndSet(null, current)) {
            // 一直循环
            System.out.println(current.getName() + " say hello " + counts);
        }
        System.out.println(cas.get().getName());
    }

    public void unlock() {
        Thread current = Thread.currentThread();
        System.out.println("unlock " + current.getName());
        if (cas.get() == current) {
            // 如果大于0，表示当前线程多次获得了锁，释放锁，通过counts-1来模拟
            if (counts > 0) {
                counts--;
            } else {
                // 如果counts=0，可以将锁释放
                cas.compareAndSet(current, null);
            }
        }
    }

    public static void main(String[] args) {
        ReentrantSpinLock reentrantSpinLock = new ReentrantSpinLock();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(reentrantSpinLock::lock);
//        executorService.execute(reentrantSpinLock::lock);
//        executorService.execute(reentrantSpinLock::unlock);
//        executorService.execute(reentrantSpinLock::unlock);
    }
}
