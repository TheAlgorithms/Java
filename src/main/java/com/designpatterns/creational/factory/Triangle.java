package src.main.java.com.designpatterns.creational.factory;

public class Triangle implements Polygon {
    @Override
    public String getType() {
        return "Triangle";
    }

    @Override
    public double area(double side) {
        return 0.433013 * side * side;
    }
}
