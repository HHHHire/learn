package cn.jxust.etu.java8.foreach;

import jdk.nashorn.internal.runtime.linker.LinkerCallSite;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @author: ddh
 * @date: 2019/11/12  11:01
 * @description:
 */
public class ForeachTest {
    List<String> list = Arrays.asList("aaaa", "dddd", "cccc", "dddd", "dddd");

    @Test
    public void test1() {
        list.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(list.indexOf(s));
                System.out.println(s);
            }
        });
    }
}
