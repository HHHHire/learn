package cn.jxust.etu.java8.lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * @author: ddh
 * @date: 2019/9/18  20:57
 * @description: Lambda 表达式的语法格式
 */
public class TestLambda2 {
    /**
     * 无参数，无返回值
     */
    @Test
    public void test1() {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world");
            }
        };
        r1.run();
        System.out.println("-----------------------");
        Runnable r2 = () -> System.out.println("heloo world");
        r2.run();
    }

    /**
     * 有一个参数，无返回值
     */
    @Test
    public void test2() {
        Consumer<String> con = (x) -> System.out.println(x);
        con.accept("hello world");
    }

    @Test
    public void test3() {
        Comparator<Integer> com = (x, y) -> {
            System.out.println("hello world");
            return Integer.compare(x, y);
        };
    }

    @Test
    public void test4(){
        Integer operation = operation(100, (x) -> x * x);
        System.out.println(operation);

        Integer operation1 = operation(200, (x) -> x + 300);
        System.out.println(operation1);
    }

    public Integer operation(Integer num, MyFun myFun) {
        return myFun.getValue(num);
    }
}
