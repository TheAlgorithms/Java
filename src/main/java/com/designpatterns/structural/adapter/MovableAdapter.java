package src.main.java.com.designpatterns.structural.adapter;

/**
 * An Adapter pattern acts as a connector between two incompatible interfaces that otherwise cannot be connected
 * directly. An Adapter wraps an existing class with a new interface so that it becomes compatible with the client’s
 * interface.
 * <br>
 * The main motive behind using this pattern is to convert an existing interface into another interface that the client
 * expects. It’s usually implemented once the application is designed.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Adapter_pattern">Adapter Pattern</a>
 */
public interface MovableAdapter {
    // Returns the speed of the movable in KPH
    double getSpeed();
}
