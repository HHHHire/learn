package com.ddh.learn.concurrency.thread.chapter2;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/8 22:01
 * @description
 */
public class TicketWindowRunnable implements Runnable {

    private static final Integer MAX = 50;

    private static Integer index = 1;

    public void run() {
        while (index <= MAX) {
            System.out.println(Thread.currentThread() + " 的号码是：" + index++);
        }
    }
}
