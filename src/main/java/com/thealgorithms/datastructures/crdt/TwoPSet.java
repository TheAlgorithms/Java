package com.thealgorithms.datastructures.crdt;

import java.util.HashSet;
import java.util.Set;

/**
 * TwoPhaseSet (2P-Set) is a state-based CRDT (Conflict-free Replicated Data Type) designed for managing sets
 * with support for both addition and removal operations in a distributed and concurrent environment.
 * It combines two G-Sets (grow-only sets) - one set for additions and another set (tombstone set) for removals.
 * Once an element is removed and placed in the tombstone set, it cannot be re-added, adhering to "remove-wins" semantics.
 * This implementation supports querying the presence of elements, adding elements, removing elements,
 * comparing with other 2P-Sets, and merging two 2P-Sets while preserving the remove-wins semantics.
 * (https://en.wikipedia.org/wiki/Conflict-free_replicated_data_type)
 *
 * @author itakurah (Niklas Hoefflin) (https://github.com/itakurah)
 */

public class TwoPSet<T> {
    private final Set<T> setA;
    private final Set<T> setR;

    /**
     * Constructs an empty Two-Phase Set.
     */
    public TwoPSet() {
        this.setA = new HashSet<>();
        this.setR = new HashSet<>();
    }

    /**
     * Checks if an element is in the set and has not been removed.
     *
     * @param element The element to be checked.
     * @return True if the element is in the set and has not been removed, otherwise false.
     */
    public boolean lookup(T element) {
        return setA.contains(element) && !setR.contains(element);
    }

    /**
     * Adds an element to the set.
     *
     * @param element The element to be added.
     */
    public void add(T element) {
        setA.add(element);
    }

    /**
     * Removes an element from the set. The element will be placed in the tombstone set.
     *
     * @param element The element to be removed.
     */
    public void remove(T element) {
        if (lookup(element)) {
            setR.add(element);
        }
    }

    /**
     * Compares the current 2P-Set with another 2P-Set.
     *
     * @param otherSet The other 2P-Set to compare with.
     * @return True if both SetA and SetR are subset, otherwise false.
     */
    public boolean compare(TwoPSet<T> otherSet) {
        return otherSet.setA.containsAll(setA) && otherSet.setR.containsAll(setR);
    }

    /**
     * Merges the current 2P-Set with another 2P-Set.
     *
     * @param otherSet The other 2P-Set to merge with.
     * @return A new 2P-Set containing the merged elements.
     */
    public TwoPSet<T> merge(TwoPSet<T> otherSet) {
        TwoPSet<T> mergedSet = new TwoPSet<>();
        mergedSet.setA.addAll(this.setA);
        mergedSet.setA.addAll(otherSet.setA);
        mergedSet.setR.addAll(this.setR);
        mergedSet.setR.addAll(otherSet.setR);
        return mergedSet;
    }
}
