package com.ddh.learn.concurrency.thread.juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/9/28 13:44
 */
public class ForkJoinDemo extends RecursiveTask<Integer> {

    private final int threshold = 5;
    private int first;
    private int last;

    public ForkJoinDemo(int first, int last) {
        this.first = first;
        this.last = last;
    }

    @Override
    protected Integer compute() {
        int result = 0;
        if (last - first <= threshold) {
            // 任务小，不用分开计算
            for (int i = first; i <= last; i++) {
                result += 1;
            }
        } else {
            // 拆分成小任务
            int middle = first + (last - first) / 2;
            ForkJoinDemo leftTask = new ForkJoinDemo(first, middle);
            ForkJoinDemo rightTask = new ForkJoinDemo(middle + 1, last);
            leftTask.fork();
            rightTask.fork();
            result = leftTask.join() + rightTask.join();
        }
        return result;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinDemo forkJoinDemo = new ForkJoinDemo(1, 10000);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        System.out.println(forkJoinPool.submit(forkJoinDemo).get());
    }
}
