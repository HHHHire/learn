package com.ddh.learn.concurrency.thread.test;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/11 17:51
 * @desc
 */
public class ThreadTest1 {
    private static boolean ready;
    private static int num;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready) {
                Thread.yield();
            }
            System.out.println(num);
        }
    }

    public static void main(String[] args) {
//        new ReaderThread().start();
//        num = 2;
//        ready = true;

        ThreadTest1 o = new ThreadTest1();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }


    }
}
