package strategy;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/9/23 11:08
 * @desc: 策略模式，定义一系列算法，把每个都封装起来，可以互相替换
 */
public class Test {
    public static void main(String[] args) {
        Context context;
        // 一个格斗很弱的勇士
        context = new Context(new WeakAbility());
        context.getFightAbility();
        context.eat();
        context.run();
        // 普通对手
        context = new Context(new CommonAbility());
        context.getFightAbility();
        // 强对手，动态设置，而不是通过构造方法
        context.setFightAbility(new StrongAbility());
        context.getFightAbility();
    }
}
