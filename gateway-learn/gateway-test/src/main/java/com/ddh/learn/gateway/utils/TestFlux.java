package com.ddh.learn.gateway.utils;

import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/11 10:50
 * @description:
 */
public class TestFlux {
    public static void main(String[] args) {
        Flux.generate(sink -> {
            sink.next("hello world");
            sink.complete();
        }).subscribe(System.out::println);

        Random random = new Random();
        Flux.generate(ArrayList::new, (list, sink) -> {
            int value = random.nextInt(100);
            list.add(value);
            sink.next(value);
            if (list.size() == 10) {
                sink.complete();
            }
            return list;
        }).subscribe(System.out::println);

        Flux.create(fluxSink -> {
            IntStream.range(0, 10).forEach(fluxSink::next);
            fluxSink.complete();
        }).subscribe(System.out::println);
    }
}
