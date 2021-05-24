package book.decorator.first;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/17 13:17
 * @description: 脱咖啡因咖啡，饮料，被装饰者
 */
public class Decaf extends Beverage{

    @Override
    public String getDescription() {
        return "脱咖啡因咖啡";
    }

    @Override
    public double cost() {
        return 12;
    }

    @Override
    public int getSize() {
        return 0;
    }
}
