package proxy.cglib;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/15 21:01
 * @desc
 */
public class Engineer {
    public void eat() {
        System.out.println("工程师正在吃饭");
    }

    public final void work() {
        System.out.println("工程师正在工作");
    }

    private void play() {
        System.out.println("工程师正在玩");
    }
}
