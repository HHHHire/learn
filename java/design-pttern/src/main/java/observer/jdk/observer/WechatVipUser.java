package observer.jdk.observer;

import java.util.Date;
import java.util.Observable;
import java.util.Observer;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/17 10:01
 * @description:
 */
public class WechatVipUser implements Observer, DisplayElement {

    private String username;

    private String msg;

    private Date date;

    public WechatVipUser(String username, Observable observable) {
        this.username = username;
        observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WechatSubject) {
            WechatSubject wechatSubject = (WechatSubject) o;
            this.msg = wechatSubject.getMsg();
            this.date = wechatSubject.getDate();
            display();
        }
    }

    @Override
    public void display() {
        System.out.println("当前vip用户是: " + username);
        System.out.println("推送到的消息是: " + msg + "  日期是:" + date);
    }
}
