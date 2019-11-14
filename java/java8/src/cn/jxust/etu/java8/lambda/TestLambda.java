package cn.jxust.etu.java8.lambda;

import org.junit.Test;

import java.util.*;

/**
 * @author: ddh
 * @date: 2019/9/18  17:19
 * @description:
 */
public class TestLambda {
    /**
     * 原来的匿名内部类
     */
    @Test
    public void test01() {
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        TreeSet<Integer> ts = new TreeSet<>(com);
    }

    /**
     * Lambda 表达式 代替内部类
     */
    @Test
    public void test02() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        TreeSet<Integer> ts = new TreeSet<>(com);
    }

    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9999.99),
            new Employee("李四", 38, 4444.44),
            new Employee("王五", 28, 5555.55),
            new Employee("赵六", 55, 6666.66)
    );

    /**
     * 需求：获取当前公司中员工年龄大于35的员工信息
     */
    public List<Employee> filterEmployees(List<Employee> list) {
        List<Employee> emps = new ArrayList<>();
        for (Employee emp : list) {
            if (emp.getAge() >= 35) {
                emps.add(emp);
            }
        }
        return emps;
    }

    @Test
    public void test03() {
        List<Employee> employees = filterEmployees(this.employees);
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    /**
     * Stream API
     */
    @Test
    public void test04() {
        employees.stream()
                .filter((e) -> e.getSalary() >= 5000)
                .limit(2)
                .forEach(System.out::println);

        System.out.println("-----------------------");

        employees.stream()
                .map(Employee::getName)
                .forEach(System.out::println);
    }
}

