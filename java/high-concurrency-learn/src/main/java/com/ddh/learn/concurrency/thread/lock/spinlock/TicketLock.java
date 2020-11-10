package com.ddh.learn.concurrency.thread.lock.spinlock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/9/30 10:07
 * @desc 解决自旋锁的公平性问题
 */
public class TicketLock {
    /**
     * 排队号
     */
    private AtomicInteger ticketNum = new AtomicInteger();
    /**
     * 服务号
     */
    private AtomicInteger serviceNum = new AtomicInteger();
    /**
     * 存储每个线程的排队号
     */
    private ThreadLocal<Integer> localTicket = new ThreadLocal<>();

    public void lock() {
        // 当有线程想要获取锁时，分配一个递增的排队号
        int currentTicketNum = ticketNum.incrementAndGet();
        // 写入 ThreadLocal 中
        localTicket.set(currentTicketNum);
        // 如果新的排队号和服务号不同，就自旋等
        while (currentTicketNum != serviceNum.get()) {
            System.out.println("排队，等票");
        }
    }

    public void unlock() {
        // 从 ThreadLocal 中获取排队号
        int currentTicketNum = serviceNum.get();
        // 通过 CAS 释放旧锁，同时生成新锁
        serviceNum.compareAndSet(currentTicketNum, currentTicketNum + 1);
    }
}
