package book.decorator.first;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/17 11:31
 * @description: 浓缩咖啡，饮料，被装饰对象
 */
public class Espresso extends Beverage {

    private int size;

    public Espresso(int size) {
        this.size = size;
    }

    @Override
    public String getDescription() {
        return "浓缩咖啡";
    }

    @Override
    public double cost() {
        int cost = 10;
        SizeEnum sizeEnum = SizeEnum.getByCode(size);
        switch (sizeEnum) {
            case TALL:
                cost += 1;
                break;
            case MEDIUM:
                cost += 2;
                break;
            case LARGE:
                cost += 4;
                break;
            default:
                break;
        }
        return cost;
    }

    @Override
    public int getSize() {
        return size;
    }
}
