package com.ddh.learn.concurrency.thread.chapter2;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/8 21:05
 * @description 银行叫号窗口
 */
public class TicketWindow extends Thread {
    private static Integer INDEX = 1;
    private static final Integer MAX = 50;
    private String name;

    TicketWindow(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (INDEX <= MAX) {
            System.out.println("窗口" + name + "叫号:" + INDEX++);
        }
    }
}
