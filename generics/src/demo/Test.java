package demo;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ddh
 * @date 2019/8/18 21:17
 * @description
 **/
public class Test {
    private static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws NoSuchMethodException {
        list.add(11);

//        list.add("adf");

        Class<? extends List> aClass = list.getClass();
        Method add = aClass.getDeclaredMethod("add", Object.class);
        add.invoke()


    }
}
