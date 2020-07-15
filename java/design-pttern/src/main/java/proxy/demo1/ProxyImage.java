package proxy.demo1;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/15 17:28
 * @desc 代理类，
 */
public class ProxyImage implements Image {

    private String fileName;
    private RealImage realImage;

    private ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }

    public static void main(String[] args) {
        Image proxyImage = new ProxyImage("hello.txt");
        proxyImage.display();
        // 第二次调用就直接展示
        proxyImage.display();
    }
}
