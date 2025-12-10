package com.thealgorithms.datastructures.heaps;

/**
 * Class representing an element in a heap.
 *
 * <p>
 * A heap element contains two attributes: a key used for ordering in the heap
 * (which can be of type int or double, either as primitive types or as wrapper objects)
 * and an additional immutable object that can store any supplementary information the user desires.
 * Note that using mutable objects may compromise the integrity of this information.
 * </p>
 *
 * <p>
 * The key attribute is used to determine the order of elements in the heap,
 * while the additionalInfo attribute can carry user-defined data associated with the key.
 * </p>
 *
 * <p>
 * This class provides multiple constructors to accommodate various key types and includes
 * methods to retrieve the key and additional information.
 * </p>
 *
 * @author Nicolas Renard
 */
public class HeapElement {

    private final double key;
    private final Object additionalInfo;

    // Constructors
    /**
     * Creates a HeapElement with the specified key and additional information.
     *
     * @param key  the key of the element (primitive type double)
     * @param info any immutable object containing additional information, may be null
     */
    public HeapElement(double key, Object info) {
        this.key = key;
        this.additionalInfo = info;
    }

    /**
     * Creates a HeapElement with the specified key and additional information.
     *
     * @param key  the key of the element (primitive type int)
     * @param info any immutable object containing additional information, may be null
     */
    public HeapElement(int key, Object info) {
        this.key = key;
        this.additionalInfo = info;
    }

    /**
     * Creates a HeapElement with the specified key and additional information.
     *
     * @param key  the key of the element (object type Integer)
     * @param info any immutable object containing additional information, may be null
     */
    public HeapElement(Integer key, Object info) {
        this.key = key;
        this.additionalInfo = info;
    }

    /**
     * Creates a HeapElement with the specified key and additional information.
     *
     * @param key  the key of the element (object type Double)
     * @param info any immutable object containing additional information, may be null
     */
    public HeapElement(Double key, Object info) {
        this.key = key;
        this.additionalInfo = info;
    }

    /**
     * Creates a HeapElement with the specified key.
     *
     * @param key the key of the element (primitive type double)
     */
    public HeapElement(double key) {
        this.key = key;
        this.additionalInfo = null;
    }

    /**
     * Creates a HeapElement with the specified key.
     *
     * @param key the key of the element (primitive type int)
     */
    public HeapElement(int key) {
        this.key = key;
        this.additionalInfo = null;
    }

    /**
     * Creates a HeapElement with the specified key.
     *
     * @param key the key of the element (object type Integer)
     */
    public HeapElement(Integer key) {
        this.key = key;
        this.additionalInfo = null;
    }

    /**
     * Creates a HeapElement with the specified key.
     *
     * @param key the key of the element (object type Double)
     */
    public HeapElement(Double key) {
        this.key = key;
        this.additionalInfo = null;
    }

    // Getters
    /**
     * Returns the object containing the additional information provided by the user.
     *
     * @return the additional information
     */
    public Object getInfo() {
        return additionalInfo;
    }

    /**
     * Returns the key value of the element.
     *
     * @return the key of the element
     */
    public double getKey() {
        return key;
    }

    // Overridden object methods
    /**
     * Returns a string representation of the heap element.
     *
     * @return a string describing the key and additional information
     */
    @Override
    public String toString() {
        return "Key: " + key + " - " + (additionalInfo != null ? additionalInfo.toString() : "No additional info");
    }

    /**
     * @param o : an object to compare with the current element
     * @return true if the keys on both elements are identical and the
     * additional info objects are identical.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof HeapElement otherHeapElement) {
            return this.key == otherHeapElement.key && (this.additionalInfo != null ? this.additionalInfo.equals(otherHeapElement.additionalInfo) : otherHeapElement.additionalInfo == null);
        }
        return false;
    }

    /**
     * Returns a hash code value for the heap element.
     *
     * @return a hash code value for this heap element
     */
    @Override
    public int hashCode() {
        int result = 31 * (int) key;
        result += (additionalInfo != null) ? additionalInfo.hashCode() : 0;
        return result;
    }

    public String getValue() {
        return additionalInfo.toString();
    }
}
