package cn.jxust.etu.java8.stream;

import cn.jxust.etu.java8.lambda.Employee;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author: ddh
 * @date: 2019/9/19  15:21
 * @description: Stream 的三个操作步骤: 1.创建Stream 2.中间操作 3.终止操作(终端操作)
 */
public class TestStreamApi {

    /**
     * 创建 Stream
     */
    @Test
    public void test1() {
         // 1. 通过Collection 系列集合提供的stream() 或 parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();

        // 2. 通过 Arrays 中的静态方法 stream() 获取数组流
        Employee[] employees = new Employee[10];
        Stream<Employee> stream1 = Arrays.stream(employees);

        // 3.通过 Stream 类中的静态方法 of()
        Stream<String> stream2 = Stream.of("aa", "bb", "cc");

        // 4. 创建无限流
        // 迭代 stream3.forEach 即是终止操作
        Stream<Integer> stream3 = Stream.iterate(0, (x) -> x + 2);
        stream3.limit(10).forEach(System.out::println);

        // 生成
        Stream.generate(() -> Math.random())
                .limit(5)
                .forEach(System.out::println);

    }
}
