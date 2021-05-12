package com.ddh.learn.utils.function;

import org.junit.Test;

import java.util.function.Function;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/5/7 13:43
 */
public class FunctionTest {

    /**
     * 匿名内部类
     */
    @Test
    public void testFunction() {
        Function<Integer, String> fun = new Function<Integer, String>() {
            @Override
            public String apply(Integer x) {
                return String.valueOf(x);
            }
        };
    }

    /**
     * lambad 表达式
     */
    @Test
    public void testFunction2() {
        Function<Integer, String> fun = x -> String.valueOf(x);
        String apply = fun.apply(12);
        System.out.println(apply);
    }

    @Test
    public
}
