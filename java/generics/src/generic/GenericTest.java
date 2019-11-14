package generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ddh
 * @date 2019/8/18 20:57
 * @description 类型通配符
 **/
public class GenericTest {
    private static void getData(List<?> data) {
        System.out.println(data.get(0));
    }

    private static void getUperNumber(List<? extends Number> data) {
        System.out.println(data.get(0));
    }

    public static void main(String[] args) {
        ArrayList<String> name = new ArrayList<>();
        ArrayList<Integer> age = new ArrayList<>();
        ArrayList<Number> number = new ArrayList<>();

        name.add("icon");
        age.add(1);
        number.add(123);

        getData(name);
        getData(age);
        getData(number);

        getUperNumber(age);
        getUperNumber(number);
    }
}
