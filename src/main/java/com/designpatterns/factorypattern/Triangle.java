package src.main.java.com.designpatterns.factorypattern;

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
