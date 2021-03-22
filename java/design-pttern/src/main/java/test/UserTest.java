package test;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/3/17 9:07
 */
public class UserTest {
    private String name;
    private String id;

    public UserTest(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
