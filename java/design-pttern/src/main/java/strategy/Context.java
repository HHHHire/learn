package strategy;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/9/23 11:09
 * @desc: 环境类，通过传入的策略不同来调用不同的策略方法
 */
public class Context {
    private FightStrategy fightStrategy;

    public Context(FightStrategy fightStrategy) {
        this.fightStrategy = fightStrategy;
    }

    public void fight() {
        fightStrategy.fight();
    }
}
