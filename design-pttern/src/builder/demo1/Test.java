package builder.demo1;

/**
 * @author ddh
 * @date 2019/8/21 17:44
 * @description
 **/
public class Test {
    public static void main(String[] args) {
        Student sex = new Student.Builder()
                .id(1)
                .address("zhejiang")
                .door(12)
                .name("zhangsan")
                .sex(1).build();
        System.out.println(sex.toString());
    }
}
