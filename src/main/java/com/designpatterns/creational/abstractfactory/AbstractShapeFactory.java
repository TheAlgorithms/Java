package com.designpatterns.creational.abstractfactory;

/**
 * The abstract factory pattern provides a way to encapsulate a group of individual factories that have a common theme
 * without specifying their concrete classes. In normal usage, the client software creates a concrete implementation of
 * the abstract factory and then uses the generic interface of the factory to create the concrete objects that are part
 * of the theme. The client doesn't know (or care) which concrete objects it gets from each of these internal factories,
 * since it uses only the generic interfaces of their products.
 * 
 * This pattern separates the details of implementation of a set of objects from their general usage and relies on
 * object composition, as object creation is implemented in methods exposed in the factory interface.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Abstract_factory_pattern">Abstract Factory Pattern</a>
 */
public abstract class AbstractShapeFactory {
    /**
     * Creates the appropriate shape object depending on the type of the shape
     *
     * @param name enum defining the name of the shape
     * @return shape object
     */
    public abstract Shape getShape(ShapeType name);
}
