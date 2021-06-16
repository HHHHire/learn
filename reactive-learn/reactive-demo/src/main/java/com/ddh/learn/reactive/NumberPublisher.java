package com.ddh.learn.reactive;

import javafx.util.Pair;

import java.util.concurrent.*;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/6/2 16:47
 * @description:
 */
public class NumberPublisher implements Flow.Publisher<Pair<Integer, Integer>> {
    private int a;
    private int b;

    private BlockingQueue<Pair<Integer, Integer>> queue = new LinkedBlockingQueue<>();

    @Override
    public void subscribe(Flow.Subscriber<? super Pair<Integer, Integer>> subscriber) {

    }

    class NumberSubscription implements Flow.Subscription {
        private final ExecutorService executor = ForkJoinPool.commonPool();
        private final Flow.Subscriber<? super Pair<Integer, Integer>> subscriber;

        public NumberSubscription(Flow.Subscriber<? super Pair<Integer, Integer>> subscriber) {
            this.subscriber = subscriber;
        }

        @Override
        public void request(long n) {
            for (int i = 0; i < n; i++) {
                executor.submit(() -> {
                    subscriber.onNext(queue.poll());
                });
            }
        }

        @Override
        public void cancel() {
            executor.shutdown();
        }
    }
}
