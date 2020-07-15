package factory.easy;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/15 15:28
 * @desc
 */
public class ThinkPadComputer implements Computer {
    @Override
    public String say() {
        System.out.println("hello Thinkpad");
        return "hello Thinkpad";
    }
}
