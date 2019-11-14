package cn.jxust.etu.java8.lambda.exer;

import cn.jxust.etu.java8.lambda.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author: ddh
 * @date: 2019/9/19  8:38
 * @description:
 */
public class LambdaTest {
    private List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9999.99),
            new Employee("李四", 38, 4444.44),
            new Employee("王五", 28, 5555.55),
            new Employee("赵六", 55, 6666.66),
            new Employee("田七", 28, 7777.77)
    );

    @Test
    public void test1() {
        Collections.sort(employees, (e1, e2) -> {
            if (e1.getAge() == e2.getAge()) {
                return e1.getName().compareTo(e2.getName());
            } else {
                return Integer.compare(e1.getAge(), e2.getAge());
            }
        });

        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    /**
     * 用于处理字符
     */
    private String strHandler(String str, MyFunction mf) {
        return mf.getValue(str);
    }

    @Test
    public void test2() {
        String trimStr = strHandler("\t\t\t   hello world   ", (str) -> str.trim());
        System.out.println(trimStr);

        String upperStr = strHandler("hello world", str -> str.toUpperCase());
        System.out.println(upperStr);
    }

    /**
     * 对两个Long型数据进行处理
     */
    public void op(Long l1, Long l2, MyFunction2<Long, Long> mf) {
        System.out.println(mf.getValue(l1, l2));
    }

    @Test
    public void test3() {
        op(100L, 200L, (x, y) -> x + y);

        op(200L, 200L, (x, y) -> x * y);
    }

    /**
     * 数组引用
     */
    @Test
    public void test4() {
        Function<Integer, String[]> fun = (x) -> new String[x];
        String[] strs = fun.apply(10);
        System.out.println(strs.length);

        Function<Integer, String[]> fun2 = String[]::new;
        String[] strs2 = fun2.apply(20);
        System.out.println(strs2.length);
    }

    /**
     * 构造器引用
     */
    @Test
    public void test5() {
        Supplier<Employee> sup = () -> new Employee();

        //构造器引用方式(无参)
        Supplier<Employee> sup2 = Employee::new;
        Employee employee = sup2.get();
        System.out.println(employee);

        //有参
        Function<Integer, Employee> sup3 = (x) -> new Employee(x);

        Function<Integer, Employee> sup4 = Employee::new;
        Employee apply = sup4.apply(11);
        System.out.println(apply);

        BiFunction<String, Integer, Employee> bf = Employee::new;
        Employee employee1 = bf.apply("张三", 18);
        System.out.println(employee1);
    }
}
