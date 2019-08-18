package demo;

/**
 * @author ddh
 * @date 2019/8/18 20:51
 * @description 泛型类
 **/
public class Box<T> {
    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public static void main(String[] args) {
        Box<Integer> integerBox = new Box<>();
        Box<String> stringBox = new Box<>();

        integerBox.setT(10);
        stringBox.setT("adf");

        System.out.println(integerBox.getT());
        System.out.println(stringBox.getT());
    }
}
