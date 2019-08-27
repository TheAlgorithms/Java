package com.designpatterns.creational.prototype;

/**
 * The prototype pattern is used when the type of objects to create is determined by a prototypical instance, which
 * is cloned to produce new objects. <p>
 * This pattern is used to:
 * 1. avoid subclasses of an object creator in the client application, like the factory method pattern does.
 * 2. avoid the inherent cost of creating a new object in the standard way (e.g., using the 'new' keyword) when it is
 * prohibitively expensive for a given application.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Prototype_pattern">Prototype Pattern</a>
 */
public abstract class Color implements Cloneable {

    String colorName;

    public abstract String addColor();

    /**
     * This method should be called from the client instead of writing code that invokes the "new" operator on a
     * hard-coded class name.
     *
     * @return a clone for the object
     */
    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}
