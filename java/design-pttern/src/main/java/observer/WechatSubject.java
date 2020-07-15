package observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/15 16:35
 * @desc
 */
public class WechatSubject implements Subject {

    private List<Observer> list = new ArrayList<>();

    @Override
    public void addOb(Observer observer) {
        list.add(observer);
    }

    @Override
    public void notifyUsers(String msg) {
        for (Observer observer : list) {
            observer.update(msg);
        }
    }
}
