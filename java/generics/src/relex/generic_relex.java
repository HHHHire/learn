package relex;

import org.junit.Test;

import java.lang.reflect.ParameterizedType;

/**
 * @author ddh
 * @date 2019/8/21 21:18
 * @description 反射泛型
 **/
public class generic_relex {

    abstract class A<T> {
        public A(){
            /*Class clazz = this.getClass(); //得到子类类型
            Type type = clazz.getGenericSuperclass(); // 获取传递给父类参数化类型
            ParameterizedType p = (ParameterizedType)type;// 它就是 A<String>
            Type[] types = p.getActualTypeArguments();// 它就是一个Class数组
            Class c = (Class) types[0];// 它就是String
            System.out.println(c.getName());*/
//            types[0].getTypeName();
//            System.out.println(types[0].getTypeName());

            Class c = (Class)((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            System.out.println(c.getName());
        }
    }

    class B extends A<String> {

    }

    class C extends A<String> {

    }

    @Test
    public void test(){
        new B();
    }
}
