package demo;

/**
 * @author ddh
 * @date 2019/8/21 11:37
 * @description 泛型接口
 **/
public class GenericInterface {
    public static void main(String[] args) {
        InterImpl inter = new InterImpl();
        inter.show("hello world!");

        InterImpl1<String> stringInterImpl1 = new InterImpl1<>();
        stringInterImpl1.show("hello world!");
    }
}

interface Inter<T> {
    void show(T t);
}

class InterImpl implements Inter<String> {

    @Override
    public void show(String t) {
        System.out.println("show:  " + t);
    }
}

class InterImpl1<T> implements Inter<T>{

    @Override
    public void show(T t) {
        System.out.println("show1:  " + t);
    }
}