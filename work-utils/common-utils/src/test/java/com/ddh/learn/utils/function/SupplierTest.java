package com.ddh.learn.utils.function;

import org.junit.Test;

import java.util.function.Supplier;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/5/7 16:01
 */
public class SupplierTest {
    @Test
    public void test() {
        String name = "你好中国";
        Supplier<String> sup = () -> name.length() + "";
        System.out.println(sup.get());

    }
}
