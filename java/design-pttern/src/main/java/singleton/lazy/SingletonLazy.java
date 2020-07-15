package singleton.lazy;

/**
 * @author ddh
 * @date 2019/8/12 21:27
 * @desc 懒汉式 线程不安全  慢加载
 * 效率很低， 99%情况下不需要同步
 * 优点：第一次调用才初始化，避免浪费内存
 * 缺点：必须加锁 synchronized 才能保证单例，但加锁会影响效率
 **/
public class SingletonLazy {
    private static SingletonLazy singletonLazy = null;

    private SingletonLazy() {
    }

    public static synchronized SingletonLazy getInstance() {
        if (singletonLazy == null) {
            singletonLazy = new SingletonLazy();
        }
        return singletonLazy;
    }
}
