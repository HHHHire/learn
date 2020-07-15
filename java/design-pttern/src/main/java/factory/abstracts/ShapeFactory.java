package factory.abstracts;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/15 16:05
 * @desc
 */
public class ShapeFactory extends AbstractFactory {

    private static final String CIRCLE = "circle";
    private static final String SQUARE = "square";

    @Override
    Color getColor(String color) {
        return null;
    }

    @Override
    Shape getShape(String shape) {
        if (shape == null || shape.isEmpty()) {
            return null;
        }
        if (CIRCLE.equalsIgnoreCase(shape)) {
            return new CircleShape();
        } else if (SQUARE.equalsIgnoreCase(shape)) {
            return new SquareShape();
        }
        return null;
    }
}
