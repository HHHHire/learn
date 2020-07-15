package factory.method;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/15 15:46
 * @desc
 */
public class BFactory extends BaseFactory {

    @Override
    BaseProduct manufacture() {
        return new BProduct();
    }
}
