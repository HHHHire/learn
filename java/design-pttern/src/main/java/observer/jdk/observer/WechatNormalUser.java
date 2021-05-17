package observer.jdk.observer;

import java.util.Date;
import java.util.Observable;
import java.util.Observer;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/17 9:22
 * @description: 普通用户
 */
public class WechatNormalUser implements Observer, DisplayElement {

    private String username;

    private String msg;

    private Date date;

    public WechatNormalUser(String username, Observable wechatSubject) {
        this.username = username;
        // 添加到subject，相当于注册
        wechatSubject.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        WechatSubject wechatSubject = (WechatSubject) o;
        this.msg = wechatSubject.getMsg();
        this.date = wechatSubject.getDate();
        display();
    }


    @Override
    public void display() {
        System.out.println("当前用户是: " + username);
        System.out.println("推送到的消息是: " + msg + "  日期是:" + date);
    }
}
