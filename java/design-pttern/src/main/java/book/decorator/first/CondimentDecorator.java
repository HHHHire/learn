package book.decorator.first;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/17 11:29
 * @description: 调料品
 */
public abstract class CondimentDecorator extends Beverage {
    @Override
    public abstract String getDescription();

    @Override
    public int getSize() {
        return 0;
    }
}
