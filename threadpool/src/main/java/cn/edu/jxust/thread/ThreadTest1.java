package cn.edu.jxust.thread;

import org.junit.Test;

/**
 * @author: ddh
 * @date: 2019/10/30  9:40
 * @description:
 */
public class ThreadTest1 {
    private static int count = 1;
    public static synchronized void inc() {
        count ++;
        System.out.println(Thread.currentThread().getName() + " inc " + count);
    }

    public static synchronized void dec() {
        count --;
        System.out.println(Thread.currentThread().getName() + " dec " + count);
    }

    static class T1 implements Runnable {

        public void run() {
            inc();
        }
    }

    static class T2 implements Runnable {

        public void run() {
            dec();
        }
    }

    @Test
    public void test() {
        ThreadTest1 t = new ThreadTest1();
        T1 t1 = new T1();
        T2 t2 = new T2();
        for (int i = 0; i < 2; i++) {
            Thread thread1 = new Thread(t1);
            thread1.start();
            Thread thread2 = new Thread(t2);
            thread2.start();
        }
    }

    public static void main(String[] args) {
        T1 t1 = new T1();
        T2 t2 = new T2();
        for (int i = 0; i < 2; i++) {
            Thread thread1 = new Thread(t1);
            Thread thread2 = new Thread(t2);
            thread1.start();
            thread2.start();
        }

    }
}
