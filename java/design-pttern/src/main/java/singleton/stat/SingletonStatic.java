package singleton.stat;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/15 14:30
 * @desc 静态内部类，静态内部类不同于静态方法或静态代码块，只有在调用它时，他才会初始化。
 * 所以在第一次加载SingletonStatic时，不会初始化STAT。只有第一次调用getInstance时虚拟机才会加载SingletonHolder，
 * 初始化STAT。这样不仅能保证线程安全，也能保证SingletonStatic类的唯一性。
 */
@SuppressWarnings("unused")
public class SingletonStatic {

    private SingletonStatic() {
    }

    private static SingletonStatic getInstance() {
        return SingletonHolder.STAT;
    }

    private static class SingletonHolder {
        private static final SingletonStatic STAT = new SingletonStatic();
    }
}
