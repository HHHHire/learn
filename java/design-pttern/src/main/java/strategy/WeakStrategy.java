package strategy;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/9/23 11:06
 */
public class WeakStrategy implements FightStrategy {
    @Override
    public void fight() {
        System.out.println("对手较弱，用脚趾打");
    }
}
