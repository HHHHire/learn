package observer;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/15 16:36
 * @desc
 */
public class Test {
    public static void main(String[] args) {
        Subject subject = new WechatSubject();
        WechatUser user1 = new WechatUser("李华");
        WechatUser user2 = new WechatUser("张三");
        subject.addOb(user1);
        subject.addOb(user2);

        subject.notifyUsers("hello everyone!");
    }
}
