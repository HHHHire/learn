package factory.method;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/15 15:45
 * @desc 工厂方法模式
 */
public class AFactory extends BaseFactory {
    @Override
    BaseProduct manufacture() {
        return new AProduct();
    }
}
