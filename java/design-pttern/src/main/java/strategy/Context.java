package strategy;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/9/23 11:09
 * @desc: 环境类，通过传入的策略不同来调用不同的策略方法
 */
public class Context extends Warrior {

    public Context(FightAbility fightAbility) {
        super.fightAbility = fightAbility;
    }

    /**
     * 动态设置他的格斗能力
     */
    public void setFightAbility(FightAbility fightAbility) {
        super.fightAbility = fightAbility;
    }

    public void getFightAbility() {
        fightAbility.fightAbility();
    }
}
