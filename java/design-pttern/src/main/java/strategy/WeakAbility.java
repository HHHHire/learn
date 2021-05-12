package strategy;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/9/23 11:06
 */
public class WeakAbility implements FightAbility {

    @Override
    public void fightAbility() {
        System.out.println("我的技术较弱");
    }
}
