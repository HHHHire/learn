package book.decorator.first;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/17 11:26
 * @description: 饮料
 */
public abstract class Beverage {
    private String description;

    public Beverage() {
        description = "unknown beverage";
    }

    public String getDescription() {
        return description;
    }

    public abstract double cost();

    public abstract int getSize();
}
