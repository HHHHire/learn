package strategy;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/12 10:42
 * @description: 父类：勇士
 * 定义它的一系列行为，如果是公共的不变的，就在这里实现。如果是会变动的，就抽取成接口，具体再去实现，这里就定义个接口
 */
public abstract class Warrior {
    /**
     * 格斗能力，接口，具体实现根据需求来
     */
    public FightAbility fightAbility;

    /**
     * 下面这些都是不变的行为，所以直接在这实现了
     */
    public void eat() {
        System.out.println("都会吃饭");
    }
    public void run() {
        System.out.println("打不过，我会跑");
    }
}
