package com.ddh.learn.utils.function;

import org.junit.Test;

import java.util.function.Consumer;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/5/7 15:35
 */
public class ConsumerTest {
    @Test
    public void test() {
        Consumer<Integer> con = x -> {
            int a = x * 3;
            System.out.println(a);
        };
        con.accept(2);
    }
}
