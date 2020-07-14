package com.ddh.learn.concurrency.thread.chapter2;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/8 22:04
 * @description
 */
public class BankVersion2 {
    private static final Integer MAX = 50;
    public static void main(String[] args) {
        // runnable就是将业务逻辑和线程控制分离开来
        final TicketWindowRunnable ticketWindowRunnable = new TicketWindowRunnable();
        Thread thread1 = new Thread(ticketWindowRunnable, "窗口一");
        Thread thread2 = new Thread(ticketWindowRunnable, "窗口二");
        Thread thread3 = new Thread(ticketWindowRunnable, "窗口三");

        thread1.start();
        thread2.start();
        thread3.start();

        // 另一种写法，这里直接通过lambda表达式来实现Runnable接口

        /*final Runnable ticketWindowRunnable1 = () -> {
            int index = 1;
            while (index <= MAX) {
                System.out.println(Thread.currentThread() + " 的号码是：" + index++);
            }
        };
        Thread thread = new Thread(ticketWindowRunnable1, "窗口1");*/
    }
}
