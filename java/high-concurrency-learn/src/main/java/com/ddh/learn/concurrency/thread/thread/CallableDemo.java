package com.ddh.learn.concurrency.thread.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/9/28 10:09
 */
public class CallableDemo {
    static class MyCallable implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            return 123;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallable myCallable = new MyCallable();
        FutureTask<Integer> task = new FutureTask<>(myCallable);
        Thread thread = new Thread(task);
        thread.start();
        System.out.println(task.get());
    }
}
