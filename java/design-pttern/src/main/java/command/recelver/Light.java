package command.recelver;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/6/29 2:08
 * @description: 灯
 */
public class Light {
    private String des;

    public Light(String des) {
        this.des = des;
    }

    public void onLight() {
        System.out.println(des + " 开灯");
    }

    public void offLight() {
        System.out.println(des + " 关灯");
    }
}
