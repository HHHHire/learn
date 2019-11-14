package cn.jxust.etu.java8.stream;

import cn.jxust.etu.java8.lambda.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: ddh
 * @date: 2019/9/19  19:51
 * @description:
 */
public class TestStreamApi3 {
    private List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9999.99, Employee.Status.FREE),
            new Employee("李四", 38, 4444.44, Employee.Status.BUSY),
            new Employee("王五", 28, 5555.55, Employee.Status.VOCATION),
            new Employee("赵六", 55, 6666.66, Employee.Status.FREE),
            new Employee("田七", 28, 7777.77, Employee.Status.BUSY),
            new Employee("田七", 28, 7777.77, Employee.Status.BUSY)
            );

    /**
     * 查找与匹配
     * allMatch 检查是否匹配所有元素
     * anyMatch---检查是否至少匹配一个元素
     * noneMatch---检查是否没有匹配所有元素
     * findFirst---返回第一个元素
     * findAny---返回当前流中的任意元素
     * count---返回流中元素的总个数
     * max---返回流中最大值
     * min---返回流中最小值
     */
    @Test
    public void test1() {
        boolean b1 = employees.stream()
                .allMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b1);

        boolean b2 = employees.stream()
                .anyMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b2);

        Optional<Employee> op = employees.stream()
                .sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
                .findFirst();
        System.out.println(op.get());
    }

    @Test
    public void test2() {
        Optional<Double> min = employees.stream()
                .map(Employee::getSalary)
                .max(Double::compareTo);
        System.out.println(min.get());
    }

    /**
     * 归约
     * reduce(T identity, BinaryOperator / reduce(BinaryOperator) 可以将流中的元素反复结合起来，得到一个值
     */
    @Test
    public void test3() {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        Integer sum = list.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println(sum);

        System.out.println("---------------------------");
        Optional<Double> reduce = employees.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println(reduce);
    }

    /**
     * 收集
     * collect---将流转换为其他形式，接收一个Collector 接口的实现，用于给Stream中元素做汇总的方法
     * 把流放到不同类型中
     * 计算总数，平均值...分组，分区
     */
    @Test
    public void test4(){
        List<String> list = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        list.forEach(System.out::println);
        System.out.println("-------------------------");
        Set<String> set = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());
        set.forEach(System.out::println);
        System.out.println("-------------------------");
        HashSet<String> hashSet = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));
        hashSet.forEach(System.out::println);

        System.out.println("-------------------------");
        //总和
        Double sum = employees.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(sum);

        System.out.println("--------------------------");
        Map<Employee.Status, List<Employee>> collect = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(collect);
    }
}
