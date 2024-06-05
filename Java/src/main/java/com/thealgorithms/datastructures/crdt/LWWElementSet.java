package com.thealgorithms.datastructures.crdt;

import java.util.HashMap;
import java.util.Map;

/**
 * Last-Write-Wins Element Set (LWWElementSet) is a state-based CRDT (Conflict-free Replicated Data Type)
 * designed for managing sets in a distributed and concurrent environment. It supports the addition and removal
 * of elements, using timestamps to determine the order of operations. The set is split into two subsets:
 * the add set for elements to be added and the remove set for elements to be removed.
 *
 * @author itakurah (Niklas Hoefflin) (https://github.com/itakurah)
 * @see <a href="https://en.wikipedia.org/wiki/Conflict-free_replicated_data_type">Conflict-free_replicated_data_type</a>
 * @see <a href="https://github.com/itakurah">itakurah (Niklas Hoefflin)</a>
 */

class Element {
    String key;
    int timestamp;
    Bias bias;

    /**
     * Constructs a new Element with the specified key, timestamp and bias.
     *
     * @param key       The key of the element.
     * @param timestamp The timestamp associated with the element.
     * @param bias      The bias of the element (ADDS or REMOVALS).
     */
    Element(String key, int timestamp, Bias bias) {
        this.key = key;
        this.timestamp = timestamp;
        this.bias = bias;
    }
}

enum Bias {
    /**
     * ADDS bias for the add set.
     * REMOVALS bias for the remove set.
     */
    ADDS,
    REMOVALS
}

class LWWElementSet {
    private final Map<String, Element> addSet;
    private final Map<String, Element> removeSet;

    /**
     * Constructs an empty LWWElementSet.
     */
    LWWElementSet() {
        this.addSet = new HashMap<>();
        this.removeSet = new HashMap<>();
    }

    /**
     * Adds an element to the addSet.
     *
     * @param e The element to be added.
     */
    public void add(Element e) {
        addSet.put(e.key, e);
    }

    /**
     * Removes an element from the removeSet.
     *
     * @param e The element to be removed.
     */
    public void remove(Element e) {
        if (lookup(e)) {
            removeSet.put(e.key, e);
        }
    }

    /**
     * Checks if an element is in the LWWElementSet by comparing timestamps in the addSet and removeSet.
     *
     * @param e The element to be checked.
     * @return True if the element is present, false otherwise.
     */
    public boolean lookup(Element e) {
        Element inAddSet = addSet.get(e.key);
        Element inRemoveSet = removeSet.get(e.key);

        return (inAddSet != null && (inRemoveSet == null || inAddSet.timestamp > inRemoveSet.timestamp));
    }

    /**
     * Compares the LWWElementSet with another LWWElementSet to check if addSet and removeSet are a subset.
     *
     * @param other The LWWElementSet to compare.
     * @return True if the set is subset, false otherwise.
     */
    public boolean compare(LWWElementSet other) {
        return other.addSet.keySet().containsAll(addSet.keySet()) && other.removeSet.keySet().containsAll(removeSet.keySet());
    }

    /**
     * Merges another LWWElementSet into this set by resolving conflicts based on timestamps.
     *
     * @param other The LWWElementSet to merge.
     */
    public void merge(LWWElementSet other) {
        for (Element e : other.addSet.values()) {
            if (!addSet.containsKey(e.key) || compareTimestamps(addSet.get(e.key), e)) {
                addSet.put(e.key, e);
            }
        }

        for (Element e : other.removeSet.values()) {
            if (!removeSet.containsKey(e.key) || compareTimestamps(removeSet.get(e.key), e)) {
                removeSet.put(e.key, e);
            }
        }
    }

    /**
     * Compares timestamps of two elements based on their bias (ADDS or REMOVALS).
     *
     * @param e     The first element.
     * @param other The second element.
     * @return True if the first element's timestamp is greater or the bias is ADDS and timestamps are equal.
     */
    public boolean compareTimestamps(Element e, Element other) {
        if (e.bias != other.bias) {
            throw new IllegalArgumentException("Invalid bias value");
        }
        Bias bias = e.bias;
        int timestampComparison = Integer.compare(e.timestamp, other.timestamp);

        if (timestampComparison == 0) {
            return bias != Bias.ADDS;
        }
        return timestampComparison < 0;
    }
}
