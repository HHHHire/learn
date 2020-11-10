package strategy;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/9/23 11:07
 */
public class CommonStrategy implements FightStrategy {
    @Override
    public void fight() {
        System.out.println("对手一般，随便打打");
    }
}
