package proxy.jdk;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/15 17:43
 * @desc 真实对象
 */
public class RealSubject implements Subject {
    @Override
    public int sell() {
        System.out.println("买书");
        return 1;
    }

    @Override
    public String say() {
        System.out.println("说话");
        return "卖书";
    }
}
