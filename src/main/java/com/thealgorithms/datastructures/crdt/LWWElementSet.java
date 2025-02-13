package com.thealgorithms.datastructures.crdt;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * Last-Write-Wins Element Set (LWWElementSet) is a state-based CRDT (Conflict-free Replicated Data
 * Type) designed for managing sets in a distributed and concurrent environment. It supports the
 * addition and removal of elements, using timestamps to determine the order of operations. The set
 * is split into two subsets: the add set for elements to be added and the remove set for elements
 * to be removed. The LWWElementSet ensures that the most recent operation (based on the timestamp)
 * wins in the case of concurrent operations.
 *
 * @param <T> The type of the elements in the LWWElementSet.
 * @author <a href="https://github.com/itakurah">itakurah (GitHub)</a>, <a
 * href="https://www.linkedin.com/in/niklashoefflin/">Niklas Hoefflin (LinkedIn)</a>
 * @see <a href="https://en.wikipedia.org/wiki/Conflict-free_replicated_data_type">Conflict free
 * replicated data type (Wikipedia)</a>
 * @see <a href="https://inria.hal.science/inria-00555588v1/document">A comprehensive study of
 * Convergent and Commutative Replicated Data Types</a>
 */
class LWWElementSet<T> {
    final Map<T, Element<T>> addSet;
    final Map<T, Element<T>> removeSet;

    /**
     * Constructs an empty LWWElementSet. This constructor initializes the addSet and removeSet as
     * empty HashMaps. The addSet stores elements that are added, and the removeSet stores elements
     * that are removed.
     */
    LWWElementSet() {
        this.addSet = new HashMap<>();
        this.removeSet = new HashMap<>();
    }

    /**
     * Adds an element to the addSet with the current timestamp. This method stores the element in the
     * addSet, ensuring that the element is added to the set with an associated timestamp that
     * represents the time of the addition.
     *
     * @param key The key of the element to be added.
     */
    public void add(T key) {
        addSet.put(key, new Element<>(key, Instant.now()));
    }

    /**
     * Removes an element by adding it to the removeSet with the current timestamp. This method adds
     * the element to the removeSet, marking it as removed with the current timestamp.
     *
     * @param key The key of the element to be removed.
     */
    public void remove(T key) {
        removeSet.put(key, new Element<>(key, Instant.now()));
    }

    /**
     * Checks if an element is in the LWWElementSet. An element is considered present if it exists in
     * the addSet and either does not exist in the removeSet, or its add timestamp is later than any
     * corresponding remove timestamp.
     *
     * @param key The key of the element to be checked.
     * @return {@code true} if the element is present in the set (i.e., its add timestamp is later
     * than its remove timestamp, or it is not in the remove set), {@code false} otherwise (i.e.,
     * the element has been removed or its remove timestamp is later than its add timestamp).
     */
    public boolean lookup(T key) {
        Element<T> inAddSet = addSet.get(key);
        Element<T> inRemoveSet = removeSet.get(key);

        return inAddSet != null && (inRemoveSet == null || inAddSet.timestamp.isAfter(inRemoveSet.timestamp));
    }

    /**
     * Merges another LWWElementSet into this set. This method takes the union of both the add-sets
     * and remove-sets from the two sets, resolving conflicts by keeping the element with the latest
     * timestamp. If an element appears in both the add-set and remove-set of both sets, the one with
     * the later timestamp will be retained.
     *
     * @param other The LWWElementSet to merge with the current set.
     */
    public void merge(LWWElementSet<T> other) {
        for (Map.Entry<T, Element<T>> entry : other.addSet.entrySet()) {
            addSet.merge(entry.getKey(), entry.getValue(), this::resolveConflict);
        }
        for (Map.Entry<T, Element<T>> entry : other.removeSet.entrySet()) {
            removeSet.merge(entry.getKey(), entry.getValue(), this::resolveConflict);
        }
    }

    /**
     * Resolves conflicts between two elements by selecting the one with the later timestamp. This
     * method is used when merging two LWWElementSets to ensure that the most recent operation (based
     * on timestamps) is kept.
     *
     * @param e1 The first element.
     * @param e2 The second element.
     * @return The element with the later timestamp.
     */
    private Element<T> resolveConflict(Element<T> e1, Element<T> e2) {
        return e1.timestamp.isAfter(e2.timestamp) ? e1 : e2;
    }
}

/**
 * Represents an element in the LWWElementSet, consisting of a key and a timestamp. This class is
 * used to store the elements in both the add and remove sets with their respective timestamps.
 *
 * @param <T> The type of the key associated with the element.
 */
class Element<T> {
    T key;
    Instant timestamp;

    /**
     * Constructs a new Element with the specified key and timestamp.
     *
     * @param key       The key of the element.
     * @param timestamp The timestamp associated with the element.
     */
    Element(T key, Instant timestamp) {
        this.key = key;
        this.timestamp = timestamp;
    }
}
