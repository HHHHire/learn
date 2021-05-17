package observer.jdk.observer;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/17 9:59
 * @description: 每个用户的展示接口，每种用户都有不同的展示方式，这里就有点像策略模式了
 */
public interface DisplayElement {
    void display();
}
