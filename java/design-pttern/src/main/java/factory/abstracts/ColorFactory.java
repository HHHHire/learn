package factory.abstracts;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/15 16:11
 * @desc
 */
public class ColorFactory extends AbstractFactory {

    private static final String RED = "red";
    private static final String BLUE = "blue";

    @Override
    Color getColor(String color) {
        if (color == null || color.isEmpty()) {
            return null;
        }
        if (RED.equalsIgnoreCase(color)) {
            return new RedColor();
        } else if (BLUE.equalsIgnoreCase(color)) {
            return new BlueColor();
        }
        return null;
    }

    @Override
    Shape getShape(String shape) {
        return null;
    }
}
