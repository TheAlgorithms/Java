package com.thealgorithms.datastructures.crdt;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * ORSet (Observed-Removed Set) is a state-based CRDT (Conflict-free Replicated Data Type)
 * that supports both addition and removal of elements. This particular implementation follows
 * the Add-Wins strategy, meaning that in case of conflicting add and remove operations,
 * the add operation takes precedence. The merge operation of two OR-Sets ensures that
 * elements added at any replica are eventually observed at all replicas. Removed elements,
 * once observed, are never reintroduced.
 * This OR-Set implementation provides methods for adding elements, removing elements,
 * checking for element existence, retrieving the set of elements, comparing with other OR-Sets,
 * and merging with another OR-Set to create a new OR-Set containing all unique elements
 * from both sets.
 *
 * @author itakurah (Niklas Hoefflin) (https://github.com/itakurah)
 * @see <a href="https://en.wikipedia.org/wiki/Conflict-free_replicated_data_type">Conflict-free_replicated_data_type</a>
 * @see <a href="https://github.com/itakurah">itakurah (Niklas Hoefflin)</a>
 */

public class ORSet<T> {

    private final Set<Pair<T>> elements;
    private final Set<Pair<T>> tombstones;

    /**
     * Constructs an empty OR-Set.
     */
    public ORSet() {
        this.elements = new HashSet<>();
        this.tombstones = new HashSet<>();
    }

    /**
     * Checks if the set contains the specified element.
     *
     * @param element the element to check for
     * @return true if the set contains the element, false otherwise
     */
    public boolean contains(T element) {
        return elements.stream().anyMatch(pair -> pair.getElement().equals(element));
    }

    /**
     * Retrieves the elements in the set.
     *
     * @return a set containing the elements
     */
    public Set<T> elements() {
        Set<T> result = new HashSet<>();
        elements.forEach(pair -> result.add(pair.getElement()));
        return result;
    }

    /**
     * Adds the specified element to the set.
     *
     * @param element the element to add
     */
    public void add(T element) {
        String n = prepare();
        effect(element, n);
    }

    /**
     * Removes the specified element from the set.
     *
     * @param element the element to remove
     */
    public void remove(T element) {
        Set<Pair<T>> pairsToRemove = prepare(element);
        effect(pairsToRemove);
    }

    /**
     * Collect all pairs with the specified element.
     *
     * @param element the element to collect pairs for
     * @return a set of pairs with the specified element to be removed
     */
    private Set<Pair<T>> prepare(T element) {
        Set<Pair<T>> pairsToRemove = new HashSet<>();
        for (Pair<T> pair : elements) {
            if (pair.getElement().equals(element)) {
                pairsToRemove.add(pair);
            }
        }
        return pairsToRemove;
    }

    /**
     * Generates a unique tag for the element.
     *
     * @return the unique tag
     */
    private String prepare() {
        return generateUniqueTag();
    }

    /**
     * Adds the element with the specified unique tag to the set.
     *
     * @param element the element to add
     * @param n       the unique tag associated with the element
     */
    private void effect(T element, String n) {
        Pair<T> pair = new Pair<>(element, n);
        elements.add(pair);
        elements.removeAll(tombstones);
    }

    /**
     * Removes the specified pairs from the set.
     *
     * @param pairsToRemove the pairs to remove
     */
    private void effect(Set<Pair<T>> pairsToRemove) {
        elements.removeAll(pairsToRemove);
        tombstones.addAll(pairsToRemove);
    }

    /**
     * Generates a unique tag.
     *
     * @return the unique tag
     */
    private String generateUniqueTag() {
        return UUID.randomUUID().toString();
    }

    /**
     * Compares this Add-Wins OR-Set with another OR-Set to check if elements and tombstones are a subset.
     *
     * @param other the other OR-Set to compare
     * @return true if the sets are subset, false otherwise
     */
    public boolean compare(ORSet<T> other) {
        Set<Pair<T>> union = new HashSet<>(elements);
        union.addAll(tombstones);

        Set<Pair<T>> otherUnion = new HashSet<>(other.elements);
        otherUnion.addAll(other.tombstones);

        return otherUnion.containsAll(union) && other.tombstones.containsAll(tombstones);
    }

    /**
     * Merges this Add-Wins OR-Set with another OR-Set.
     *
     * @param other the other OR-Set to merge
     */
    public void merge(ORSet<T> other) {
        elements.removeAll(other.tombstones);
        other.elements.removeAll(tombstones);
        elements.addAll(other.elements);
        tombstones.addAll(other.tombstones);
    }

    /**
     * Represents a pair containing an element and a unique tag.
     *
     * @param <T> the type of the element in the pair
     */
    public static class Pair<T> {
        private final T element;
        private final String uniqueTag;

        /**
         * Constructs a pair with the specified element and unique tag.
         *
         * @param element   the element in the pair
         * @param uniqueTag the unique tag associated with the element
         */
        public Pair(T element, String uniqueTag) {
            this.element = element;
            this.uniqueTag = uniqueTag;
        }

        /**
         * Gets the element from the pair.
         *
         * @return the element
         */
        public T getElement() {
            return element;
        }
    }
}
