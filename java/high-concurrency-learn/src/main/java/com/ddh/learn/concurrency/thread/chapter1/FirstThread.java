package com.ddh.learn.concurrency.thread.chapter1;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/7 19:28
 * @description
 */
public class FirstThread {

    public static void main(String[] args) {
        new Thread("READ-THREAD"){
            @Override
            public void run() {
                readData();
            }
        }.start();

        new Thread("WRITE-THREAD"){
            @Override
            public void run() {
                writeData();
            }
        }.start();

    }

    private static void readData() {
        try {
            System.out.println("read data from db.");
            Thread.sleep(1000*3L);
            System.out.println("handle...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("finish....");
    }

    private static void writeData() {
        try {
            System.out.println("write data to file.");
            Thread.sleep(1000*30L);
            System.out.println("handle...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("finish....");
    }
}
