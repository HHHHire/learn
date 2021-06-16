package com.ddh.learn.reactive;

import cn.hutool.core.util.StrUtil;
import javafx.util.Pair;

import java.util.concurrent.Flow;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/6/2 16:39
 * @description:
 */
public class NumberSubscriber implements Flow.Subscriber<Pair<Integer, Integer>> {

    private Flow.Subscription subscription;

    private int c;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        this.subscription.request(1);
        System.out.println(StrUtil.format("ThreadName: {}, {}",
                Thread.currentThread().getName(), subscription));
    }

    @Override
    public void onNext(Pair<Integer, Integer> item) {
        Integer a = item.getKey();
        Integer b = item.getValue();
        this.c = a + b;
        System.out.println(StrUtil.format("ThreadName: {}, {}, {}", Thread.currentThread().getName(),
                item, this.c));
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {
        System.out.println(StrUtil.format("ThreadName: {}, 结束了", Thread.currentThread().getName()));
    }
}
