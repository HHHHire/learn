package com.ddh.learn.java.predicate;

import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/11/9 17:22
 */
public class Predicate1 {
    public static void main(String[] args) {
        Predicate<Integer> predicate = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer > 5;
            }
        };

        Stream<Integer> integerStream = Stream.of(1, 22, 12, 4, 3, 6);
        System.out.println(integerStream.filter(predicate).collect(Collectors.toList()));
    }
}
