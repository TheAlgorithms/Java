package src.main.java.com.designpatterns.creational.factory;

public class Square implements Polygon {

    @Override
    public String getType() {
        return "Square";
    }

    @Override
    public double area(double side) {
        return side * side;
    }
}
