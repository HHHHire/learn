package book.decorator.first;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/17 13:23
 * @description:
 */
public class Test {
    public static void main(String[] args) {
        // 定义一个咖啡（饮料）包含size大小，1表示中杯、、、、被装饰者
        Espresso espresso = new Espresso(1);
        // 定义一个牛奶（装饰者），传入被装饰者，
        Milk milk = new Milk(espresso);
        Mocha mocha = new Mocha(milk);
        System.out.println(mocha.getDescription());
        System.out.println(mocha.cost());
    }
}
