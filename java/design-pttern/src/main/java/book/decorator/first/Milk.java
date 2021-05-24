package book.decorator.first;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/17 13:17
 * @description: 奶，调料，装饰者
 */
public class Milk extends CondimentDecorator {

    private Beverage beverage;

    public Milk(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ",牛奶";
    }

    @Override
    public double cost() {
        return beverage.cost() + 2;
    }
}
