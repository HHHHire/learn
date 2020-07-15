package factory.abstracts;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/15 16:04
 * @desc 抽象工厂模式
 */
public abstract class AbstractFactory {
    abstract Color getColor(String color);
    abstract Shape getShape(String shape);
}
