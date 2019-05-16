package src.main.java.com.designpatterns.factorypattern;

public class Pentagon implements Polygon {
    @Override
    public String getType() {
        return "Pentagon";
    }

    @Override
    public double area(double side) {
        return 3.847104 * side * side;
    }
}
