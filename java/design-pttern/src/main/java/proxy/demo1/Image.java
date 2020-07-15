package proxy.demo1;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/15 17:24
 * @desc 代理模式，静态代理。对于有些对象或方法，如果直接对其进行操作可能会带来许多的麻烦，
 * 因此可以选择代理模式，在访问此对象时添加一层访问层，即代理类。
 */
public interface Image {
    void display();
}
