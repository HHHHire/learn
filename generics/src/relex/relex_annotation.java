package relex;

import org.junit.Test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

/**
 * @author ddh
 * @date 2019/8/21 21:49
 * @description 反射注解
 **/
public class relex_annotation {
    @Test
    public void test(){
        // 得到作用目标
        Class<MyAnno> myAnno = MyAnno.class;
        // 获取指定类型的注解
        AMyAnno annotation = myAnno.getAnnotation(AMyAnno.class);

        System.out.println(annotation.sex() + ", " + annotation.name() + ", " + annotation.age());
    }

    @Test
    public void test1() throws NoSuchMethodException {
        // 得到作用目标
        Class<MyAnno> myAnno = MyAnno.class;
        Method fun1 = myAnno.getMethod("fun1");
        // 获取指定类型的注解
        AMyAnno annotation = fun1.getAnnotation(AMyAnno.class);

        System.out.println(annotation.sex() + ", " + annotation.name() + ", " + annotation.age());
    }
}

@AMyAnno(name = "zhangsan", age = 10, sex = "男")
class MyAnno{
    @AMyAnno(name = "fun", age = 1, sex = "女")
    public void fun1(){

    }
}

@Retention(RetentionPolicy.RUNTIME)
@interface  AMyAnno{
    String name();
    int age();
    String sex();
}
