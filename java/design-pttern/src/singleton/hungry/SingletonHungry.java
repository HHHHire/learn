package singleton.hungry;

/**
 * @author ddh
 * @date 2019/8/12 21:17
 * @description 饿汉式 线程安全  容易产生垃圾对象
 * 优点：没有加锁效率高
 * 缺点：类加载时就初始化，浪费内存
 **/
public class SingletonHungry {
    public SingletonHungry() {
    }

    private static SingletonHungry instance = new SingletonHungry();

    public static SingletonHungry getInstance(){
        return instance;
    }
}
