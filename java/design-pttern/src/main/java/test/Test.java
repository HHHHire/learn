package test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

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
        Integer count = 1;
        Boolean flag = null;
        try {
            TestExc test = new TestException();
            count = test.testException(count);
        } catch (Exception e) {
            flag = false;
        }
        System.out.println(count);
        System.out.println(flag);

        Map map = new HashMap<String, Object>();
        map.put("1", "hello");
        System.out.println(map.toString());


        // ======================
        UserTest userTest = new UserTest("name", "id");
        System.out.println(userTest.hashCode());
        System.out.println(System.identityHashCode(userTest));
    }



}
