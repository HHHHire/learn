package singleton.check;

/**
 * @author ddh
 * @date 2019/8/12 21:42
 * @description 双重检锁
 **/
@SuppressWarnings("unused")
public class SingletonCheck {
    private volatile static SingletonCheck check = null;

    private SingletonCheck() {
    }

    public static SingletonCheck getInstance() {
        if (check == null) {
            synchronized (SingletonCheck.class) {
                if (check == null) {
                    return new SingletonCheck();
                }
            }
        }
        return check;
    }
}
