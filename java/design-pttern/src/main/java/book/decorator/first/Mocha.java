package book.decorator.first;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/17 13:20
 * @description: 摩卡，调料，装饰者
 */
public class Mocha extends CondimentDecorator {

    private Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ",摩卡";
    }

    @Override
    public double cost() {
        return beverage.cost() + 2.5;
    }
}
