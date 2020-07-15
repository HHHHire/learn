package factory.easy;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/15 15:27
 * @desc
 */
public class MacComputer implements Computer {

    @Override
    public String say() {
        System.out.println("hello mac");
        return "hello mac";
    }
}
