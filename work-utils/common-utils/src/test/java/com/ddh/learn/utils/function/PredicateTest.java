package com.ddh.learn.utils.function;

import org.junit.Test;

import java.util.function.Predicate;
import java.util.stream.IntStream;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/5/7 15:44
 */
public class PredicateTest {
    @Test
    public void test() {
        // 取偶数
        Predicate<Integer> pre = x -> x % 2 == 0;
        IntStream.range(0, 10).forEach(i -> {
            if (pre.test(i)) {
                System.out.println(i);
            }
        });
    }
}
