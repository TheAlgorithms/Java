package src.main.java.com.designpatterns.factorypattern;

/**
 * In class-based programming, the factory method pattern is a creational pattern that uses factory methods to deal
 * with the problem of creating objects without having to specify the exact class of the object that will be created.
 * This is done by creating objects by calling a factory method—either specified in an interface and implemented by
 * child classes, or implemented in a base class and optionally overridden by derived classes—rather than by calling
 * a constructor.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Factory_method_pattern">Factory Pattern</a>
 */
public class PolygonFactory {
    /**
     * Factory pattern implementation for the Polygon Interface to return the correct regular polygon object
     * depending on the number of sides it has.
     *
     * @param numberOfSides in the polygon to initialize.
     * @return the object having the respective number of sides
     */
    public Polygon getPolygon(int numberOfSides) {
        switch (numberOfSides) {
            case 3:
                return new Triangle();
            case 4:
                return new Square();
            case 5:
                return new Pentagon();
            default:
                return null;
        }
    }
}
