package com.ddh.learn.java.consumer;

import java.util.function.Consumer;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/11/9 15:04
 * @des: Consumer 就是看作一段要执行的语句，然后调用accept执行，多个consumer可以通过andThen添加
 */
public class Consumer1 {
    public static void main(String[] args) {
        testAccept();
        testAndThen();
    }

    public static void testAccept() {
        Consumer<Integer> tConsumer = x -> System.out.println("平方：" + x * x);
        tConsumer.accept(2);
    }

    public static void testAndThen() {
        Consumer<Integer> tConsumer1 = x -> System.out.println("值：" + x);
        Consumer<Integer> tConsumer2 = x -> System.out.println("和：" + (x + x));
        Consumer<Integer> tConsumer3 = x -> System.out.println("平方：" + x * x);
        // 相加
        tConsumer1.andThen(tConsumer2).andThen(tConsumer3).accept(1);
    }
}
