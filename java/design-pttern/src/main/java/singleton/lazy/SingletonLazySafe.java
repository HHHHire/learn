package singleton.lazy;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/15 14:07
 * @desc 懒汉模式-线程安全
 */
@SuppressWarnings("unused")
public class SingletonLazySafe {
    private static SingletonLazySafe safe = null;

    private SingletonLazySafe() {
    }

    /**
     * 加同步锁
     *
     * @return SingletonLazySafe对象
     */
    public static synchronized SingletonLazySafe getInstance() {
        if (safe == null) {
            return new SingletonLazySafe();
        }
        return safe;
    }

}
