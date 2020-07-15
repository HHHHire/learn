package observer;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/15 16:30
 * @desc
 */
public class WechatUser implements Observer {

    private String name;

    WechatUser(String name) {
        this.name = name;
    }

    @Override
    public void update(String msg) {
        System.out.println("name:" + name + ",msg:" + msg);
    }
}
