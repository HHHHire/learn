package proxy.demo1;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/15 17:25
 * @desc 实际操作类
 */
public class RealImage implements Image {

    private String fileName;

    RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk();
    }

    @Override
    public void display() {
        System.out.println("display: " + fileName);
    }

    /**
     * 从磁盘中加载
     */
    private void loadFromDisk() {
        System.out.println("loading: " + fileName + "from disk");
    }
}
