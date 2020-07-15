package singleton.enumm;

import java.io.ObjectStreamException;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/15 14:41
 * @desc 枚举单例，线程安全，不会被重复创建，但是在反序列化时会重复创建实例，可以通过重写readObject()方法，
 * 来保证不被反序列化生成对象。
 */
public class SingletonEnum {

    private SingletonEnum() {

    }

    /**
     * 枚举单例
     */
    private enum Singleton {
        /**
         * 实例
         */
        INSTANCE;

        private SingletonEnum instance;

        Singleton() {
            instance = new SingletonEnum();
        }

        private SingletonEnum getInstance() {
            return instance;
        }

        /**
         * 如果单例类实现了序列化接口Serializable, 就可以通过反序列化破坏单例，所以我们可以不实现序列化接口,
         * 如果非得实现序列化接口，
         * 可以重写反序列化方法readResolve(), 反序列化时直接返回相关单例对象
         */
        private Object readResolve() throws ObjectStreamException {
            return instance;
        }
    }

    static SingletonEnum getInstance() {
        return Singleton.INSTANCE.getInstance();
    }
}
