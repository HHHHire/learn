package com.ddh.learn.concurrency.thread.chapter2;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/8 21:13
 * @description
 */
public class Bank {
    public static void main(String[] args) {
        // 此时线程和逻辑混淆在一起
        TicketWindow ticketWindow1 = new TicketWindow("一号，");
        ticketWindow1.start();

        TicketWindow ticketWindow2 = new TicketWindow("二号，");
        ticketWindow2.start();

        TicketWindow ticketWindow3 = new TicketWindow("三号，");
        ticketWindow3.start();
    }
}
