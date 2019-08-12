package singleton.hungry;

/**
 * @author ddh
 * @date 2019/8/12 21:17
 * @description 饿汉式 线程安全
 **/
public class SingletonHungry {
    public SingletonHungry() {
    }

    private static SingletonHungry instance = new SingletonHungry();

    public static SingletonHungry getInstance(){
        return instance;
    }
}
