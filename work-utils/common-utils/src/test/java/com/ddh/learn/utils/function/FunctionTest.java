package com.ddh.learn.utils.function;

import org.junit.Test;

import java.util.function.Function;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/5/7 13:43
 */
public class FunctionTest {

    /**
     * 匿名内部类方式实现 Function 接口
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
     * lambda 表达式，实现 Function 接口
     */
    @Test
    public void testFunction2() {
        Function<Integer, String> fun = x -> String.valueOf(x);
        String apply = fun.apply(12);
        System.out.println(apply);
    }

    /**
     * andThen
     */
    @Test
    public void testAndThen() {
        Function<Integer, Integer> fun = x -> x + 3;
        Integer apply = fun.andThen(x -> x * 4).apply(2);
        System.out.println(apply);
    }

    @Test
    public void testCompose() {
        Function<Integer, Integer> fun = x -> x * 3;
        Integer apply = fun.compose(x -> (int) x + 2).apply(2);
        System.out.println(apply);
    }

    @Test
    public void testIdentity() {
        Function<Object, Object> identity = Function.identity();
        Object apply = identity.apply(3);
        System.out.println(apply);
    }
}
