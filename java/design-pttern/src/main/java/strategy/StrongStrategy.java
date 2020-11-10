package strategy;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/9/23 11:08
 */
public class StrongStrategy implements FightStrategy {

    @Override
    public void fight() {
        System.out.println("对手很强，要认真");
    }
}
