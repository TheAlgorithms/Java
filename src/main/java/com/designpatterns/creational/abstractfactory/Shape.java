package src.main.java.com.designpatterns.creational.abstractfactory;

public interface Shape {
    /**
     * calculates the surface area for the shape object
     *
     * @param radius the radius or length of shape whose area is to be calculated
     * @return total surface area for the shape
     */
    double surfaceArea(float radius);

    /**
     * A property to identity the type of the shape for testing the pattern
     *
     * @return an enum describing the shape type
     */
    ShapeType getShapeType();
}
