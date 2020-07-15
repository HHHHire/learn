package builder.demo1;

/**
 * @author ddh
 * @date 2019/8/21 17:36
 * @description 构建者模式
 **/
public class Student {
    private Integer id;
    private String name;
    private Integer sex;
    private Integer door;
    private String address;

    private Student(Builder builder) {
        id = builder.id;
        name = builder.name;
        sex = builder.sex;
        door = builder.door;
        address = builder.address;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", door=" + door +
                ", address='" + address + '\'' +
                '}';
    }

    public void print() {
        System.out.println(id + name + sex + door + address);
    }

    static class Builder {
        private Integer id;
        private String name;
        private Integer sex;
        private Integer door;
        private String address;

        Builder id(Integer id) {
            this.id = id;
            return this;
        }

        Builder name(String name) {
            this.name = name;
            return this;
        }

        Builder sex(Integer sex) {
            this.sex = sex;
            return this;
        }

        Builder door(Integer door) {
            this.door = door;
            return this;
        }

        Builder address(String address) {
            this.address = address;
            return this;
        }

        public Student build(){
            return new Student(this);
        }
    }
}
