package com.thealgorithms.datastructures.crdt;

import java.util.HashSet;
import java.util.Set;

/**
 * GSet (Grow-only Set) is a state-based CRDT (Conflict-free Replicated Data Type)
 * that allows only the addition of elements and ensures that once an element is added,
 * it cannot be removed. The merge operation of two G-Sets is their union.
 * This implementation supports adding elements, looking up elements, comparing with other G-Sets,
 * and merging with another G-Set to create a new G-Set containing all unique elements from both sets.
 * (https://en.wikipedia.org/wiki/Conflict-free_replicated_data_type)
 *
 * @author itakurah (Niklas Hoefflin) (https://github.com/itakurah)
 */

public class GSet<T> {
    private final Set<T> elements;

    /**
     * Constructs an empty G-Set.
     */
    public GSet() {
        this.elements = new HashSet<>();
    }

    /**
     * Adds an element to the G-Set.
     *
     * @param e the element to be added
     */
    public void addElement(T e) {
        elements.add(e);
    }

    /**
     * Checks if the given element is present in the G-Set.
     *
     * @param e the element to be checked
     * @return true if the element is present, false otherwise
     */
    public boolean lookup(T e) {
        return elements.contains(e);
    }

    /**
     * Compares the G-Set with another G-Set to check if it is a subset.
     *
     * @param other the other G-Set to compare with
     * @return true if the current G-Set is a subset of the other, false otherwise
     */
    public boolean compare(GSet<T> other) {
        return other.elements.containsAll(elements);
    }

    /**
     * Merges the current G-Set with another G-Set, creating a new G-Set
     * containing all unique elements from both sets.
     *
     * @param other the G-Set to merge with
     */
    public void merge(GSet<T> other) {
        elements.addAll(other.elements);
    }
}
