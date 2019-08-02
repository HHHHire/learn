/**
 * @author ddh
 * @date 2019/8/2 12:01
 * @description 饿汉式
 **/
public class HungryDemo {

    private HungryDemo() {
    }

    private static final HungryDemo hungryDemo = new HungryDemo();

    public static HungryDemo getInstance() {
        return hungryDemo;
    }
}
