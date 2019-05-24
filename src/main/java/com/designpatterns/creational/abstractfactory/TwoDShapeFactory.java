package src.main.java.com.designpatterns.creational.abstractfactory;

public class TwoDShapeFactory extends AbstractShapeFactory {
    @Override
    public Shape getShape(ShapeType name) {
        if (ShapeType.LINE == name) {
            return new Line();
        } else if (ShapeType.CIRCLE == name) {
            return new Circle();
        }
        return null;
    }
}
