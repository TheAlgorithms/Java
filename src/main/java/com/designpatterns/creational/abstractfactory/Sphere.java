package src.main.java.com.designpatterns.creational.abstractfactory;

public class Sphere implements Shape {
    @Override
    public double surfaceArea(float radius) {
        return 4 * Math.PI * radius * radius;
    }

    @Override
    public ShapeType getShapeType() {
        return ShapeType.SPHERE;
    }
}
