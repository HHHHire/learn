package factory.abstracts;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/15 16:13
 * @desc 工厂选择器
 */
public class FactorySelector {

    private static final String SHAPE = "shape";
    private static final String COLOR = "color";

    public static AbstractFactory selectFactory(String type) {
        if (type == null || type.isEmpty()) {
            return null;
        }
        if (COLOR.equalsIgnoreCase(type)) {
            return new ColorFactory();
        } else if (SHAPE.equalsIgnoreCase(type)) {
            return new ShapeFactory();
        }
        return null;
    }

    public static void main(String[] args) {
        AbstractFactory colorFactory = FactorySelector.selectFactory("color");
        Color color = colorFactory.getColor("red");
        color.showColor();

    }
}
