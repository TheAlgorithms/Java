package src.main.java.com.designpatterns.creational.abstractfactory;

public class Line implements Shape {
    @Override
    public double surfaceArea(float radius) {
        return 0;
    }

    @Override
    public ShapeType getShapeType() {
        return ShapeType.LINE;
    }
}
