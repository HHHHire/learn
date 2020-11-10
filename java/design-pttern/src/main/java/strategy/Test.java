package strategy;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/9/23 11:08
 * @desc: 策略模式，定义一系列算法，把每个都封装起来，可以互相替换
 */
public class Test {
    public static void main(String[] args) {
        Context context;
        // 弱对手
        context = new Context(new WeakStrategy());
        context.fight();
        // 普通对手
        context = new Context(new CommonStrategy());
        context.fight();
        // 强对手
        context = new Context(new StrongStrategy());
        context.fight();
    }
}
