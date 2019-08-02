/**
 * @author ddh
 * @date 2019/8/2 11:42
 * @description 单例模式 懒汉式
 **/
public class LazyDemo {

    private LazyDemo() {
    }

    private static LazyDemo lazyDemo = null;

    /**
     * 静态工厂方法
     * @return LazyDemo
     */
    public static LazyDemo getInstance() {
        if (lazyDemo == null){
            lazyDemo = new LazyDemo();
        }
        return lazyDemo;
    }

    /*@Test
    public void test01() {

    }*/
}
