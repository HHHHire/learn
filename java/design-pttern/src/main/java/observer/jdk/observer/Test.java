package observer.jdk.observer;

import java.util.Date;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/17 9:47
 * @description:
 */
public class Test {
    public static void main(String[] args) throws Exception {
        WechatSubject wechatSubject = new WechatSubject();
        WechatNormalUser zhangsan = new WechatNormalUser("zhangsan", wechatSubject);
        WechatVipUser lisi = new WechatVipUser("lisi", wechatSubject);
        wechatSubject.setMessage("hello world", new Date());
        Thread.sleep(2000);
        wechatSubject.setMessage("hello world2", new Date());
    }
}
