package observer;

import java.util.List;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/15 16:32
 * @desc
 */
public interface Subject {
    void addOb(Observer observer);
    void notifyUsers(String msg);
}
