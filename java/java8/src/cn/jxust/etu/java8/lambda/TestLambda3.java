package cn.jxust.etu.java8.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author: ddh
 * @date: 2019/9/19  9:23
 * @description:
 */
public class TestLambda3 {
    // 产生指定个数的整数，并放入集合中
    public List<Integer> getNumList(int num, Supplier<Integer> sup) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Integer n = sup.get();
            list.add(n);
        }
        return list;
    }

    @Test
    public void test1() {
        List<Integer> numList = getNumList(10, () -> (int)(Math.random() * 100));
        for (Integer integer : numList) {
            System.out.println(integer);
        }
    }
}
