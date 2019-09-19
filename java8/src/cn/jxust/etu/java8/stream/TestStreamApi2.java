package cn.jxust.etu.java8.stream;

import cn.jxust.etu.java8.lambda.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author: ddh
 * @date: 2019/9/19  15:59
 * @description: 中间操作
 */
public class TestStreamApi2 {
    private List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9999.99),
            new Employee("李四", 38, 4444.44),
            new Employee("王五", 28, 5555.55),
            new Employee("赵六", 55, 6666.66),
            new Employee("田七", 28, 7777.77),
            new Employee("田七", 28, 7777.77),
            new Employee("田七", 28, 7777.77)
    );
    /**
     * 筛选与切片
     * filter   接受Lambda，从流中排除某些元素
     * limit    截断流，使其元素不超过给定数量
     * skip(n)  跳过元素，返回一个扔掉了前n个元素的流。若流中元素不足n个，则返回一个空流。与limit(n)互补
     * distinct 筛选，通过流所生成元素的hashCode()和equals()去重复元素
     */
    /**
     * 内部迭代
     */
    @Test
    public void test1() {
        // 中间操作：不会执行任何操作
        Stream<Employee> stream = employees.stream()
                .filter((e) -> {
                    System.out.println("Stream API");
                    return e.getAge() > 35;
                });
        // 终止操作：一次性执行全部内容，即“惰性求值”
        stream.forEach(System.out::println);
    }

    /**
     * 外部迭代
     */
    @Test
    public void test2() {
        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    /**
     * limit 短路 只取前两条进行筛选，后面不做筛选
     * 注意 limit 的位置，若在筛选前则取 n 条数据进行筛选，若在筛选后则取筛选后的数据前 n 条
     */
    @Test
    public void test3(){
        employees.stream()
                .filter((e) -> {
                    System.out.println("短路");
                    return e.getSalary() > 5000;
                })
                .limit(2)
                .forEach(System.out::println);
    }

    /**
     * skip
     * distinct 去重需要提供 hashcode 和 equals
     */
    @Test
    public void test4() {
        employees.stream()
                .filter((e) -> e.getSalary() > 5000)
                .skip(1)
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * 映射：
     * map  接收 Lambda，将元素转换成且他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素
     * flatMap  接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     */
    @Test
    public void test5() {
        List<String> strings = Arrays.asList("aaa", "bbb", "dneg", "lis");
        strings.stream()
                .map((str) -> str.toUpperCase())
                .forEach(System.out::println);
        System.out.println("--------------------------");
        employees.stream()
                .map(Employee::getName)
//                .distinct()
                .forEach(System.out::println);
        System.out.println("--------------------------");

        Stream<Stream<Character>> stream = strings.stream()
                .map(TestStreamApi2::filterCharacter);// {{a,a,a},{b,b,b}...} 相当于集合的add()
        stream.forEach((sm) -> {
            sm.forEach(System.out::println);
        });
        System.out.println("-------------------------");

        Stream<Character> sm = strings.stream()
                .flatMap(TestStreamApi2::filterCharacter);// {a,a,a,b,b,b...} 相当于集合的addAll()
        sm.forEach(System.out::println);
    }

    public static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();
        for (char ch : str.toCharArray()) {
            list.add(ch);
        }
        return list.stream();
    }

    /**
     * 排序
     * sorted() 自然排序(Comparable)
     * sorted   (Comparator com) 定制排序(Comparable)
     */
    @Test
    public void test7() {
        List<String> list = Arrays.asList("ccc", "aaa", "ddd", "bbb");
        list.stream()
                .sorted()
                .forEach(System.out::println);
        System.out.println("--------------------------");

        employees.stream()
                .sorted((e1, e2) -> {
                    if (e1.getAge() == e2.getAge()) {
                        return e1.getName().compareTo(e2.getName());
                    } else {
                        return ((Integer)e1.getAge()).compareTo(e2.getAge());
                    }
                }).forEach(System.out::println);
    }
}
