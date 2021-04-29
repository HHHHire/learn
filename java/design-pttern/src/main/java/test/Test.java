package test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.groupingBy;

/**
 * @author ddh
 * @date 2019/8/12 21:24
 * @description
 **/
public class Test {


    public static void main(String[] args) {
//        System.out.println(SingletonLazy.getInstance());
//        System.out.println(SingletonLazy.getInstance());
//        System.out.println(SingletonLazy.getInstance());
//        System.out.println(SingletonLazy.getInstance());
//        Integer count = 1;
//        Boolean flag = null;
//        try {
//            TestExc test = new TestException();
//            count = test.testException(count);
//        } catch (Exception e) {
//            flag = false;
//        }
//        System.out.println(count);
//        System.out.println(flag);
//
//        Map map = new HashMap<String, Object>();
//        map.put("1", "hello");
//        System.out.println(map.toString());
//
//
//        // ======================
//        UserTest userTest = new UserTest("name", "id");
//        System.out.println(userTest.hashCode());
//        System.out.println(System.identityHashCode(userTest));


        /*List<Map> list = new ArrayList<>();


        Map<String, Object> map = new HashMap<>();
        map.put("1", "hello");
        map.put("2", "world");
        map.put("3", "nihao");

        Map<String, Object> map2 = new HashMap<>();
        map2.put("1", "hello2");
        map2.put("2", "world2");
        map2.put("3", "nihao2");

        Map<String, Object> map3 = new HashMap<>();
        map3.put("1", "hello3");
        map3.put("2", "world3");
        map3.put("3", "nihao3");
        list.add(map);
        list.add(map2);
        list.add(map3);
        System.out.println(list);
        Map<String, List<Map>> collect = list.stream().collect(groupingBy(result -> String.valueOf(result.get("1"))));
        System.out.println(collect);
        LinkedHashMap<String, List<Map>> collect1 = list.stream().collect(groupingBy(result -> String.valueOf(result.get("1")), LinkedHashMap::new, Collectors.toList()));
        System.out.println(collect1);*/

        IntStream.range(1, 5).forEach(System.out::println);
    }



}
