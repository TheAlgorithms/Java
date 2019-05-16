package src.main.java.com.designpatterns.factorypattern;

public interface Polygon {
    /**
     * Should be overriden to describe the type of each polygon
     *
     * @return a String value describing the name of the polygon
     */
    String getType();

    /**
     * Calculates the area of the regular polygon
     *
     * @param side The length of the side of regular polygon
     * @return area of the polygon
     */
    double area(double side);
}


