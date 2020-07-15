package factory.easy;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/15 15:26
 * @desc 简单工厂模式
 */
public class EasyFactory {

    public static Computer getComputer(String brand) {
        switch (brand) {
            case "mac": {
                return new MacComputer();
            }
            case "thinkpad": {
                return new ThinkPadComputer();
            }
            default:
                return null;
        }

    }

    public static void main(String[] args) {
        Computer mac = EasyFactory.getComputer("mac");
        if (mac != null) {
            mac.say();
        }
    }
}
