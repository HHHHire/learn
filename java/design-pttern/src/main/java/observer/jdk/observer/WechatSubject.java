package observer.jdk.observer;

import java.util.Date;
import java.util.Observable;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/17 9:10
 * @description: 微信主题，负责推送
 */
public class WechatSubject extends Observable {
    private String msg;
    private Date date;

    public WechatSubject() {
    }

    public void messageChanged() {
        setChanged();
        notifyObservers();
    }

    public void setMessage(String msg, Date date) {
        this.msg = msg;
        this.date = date;
        messageChanged();
    }

    public String getMsg() {
        return msg;
    }

    public Date getDate() {
        return date;
    }
}
