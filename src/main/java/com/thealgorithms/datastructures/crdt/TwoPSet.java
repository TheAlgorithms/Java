import java.util.Collections;
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
 * Changes made by @555vedant:
 * - Made the constructor private and added a static factory method 'empty'.
 * - Used 'Collections.unmodifiableSet' to create unmodifiable sets for 'setA' and 'setR' in the 'merge' method.
 * - Updated the 'merge' method accordingly.
 *
 * @author itakurah (Niklas Hoefflin) (https://github.com/itakurah)
 * @author 555vedant (Your Name) (Your GitHub Profile)
 */
public class TwoPSet<T> {
    private final Set<T> setA;
    private final Set<T> setR;

    /**
     * Constructs an empty Two-Phase Set.
     * Note: This constructor is made private. Use the static factory method 'empty()' to create an empty set.
     */
    private TwoPSet(Set<T> setA, Set<T> setR) {
        this.setA = setA;
        this.setR = setR;
    }

    /**
     * Creates an empty Two-Phase Set.
     *
     * @param <T> the type of elements in the set.
     * @return an empty Two-Phase Set.
     */
    public static <T> TwoPSet<T> empty() {
        return new TwoPSet<>(new HashSet<>(), new HashSet<>());
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
        Set<T> mergedSetA = new HashSet<>(setA);
        mergedSetA.addAll(otherSet.setA);

        Set<T> mergedSetR = new HashSet<>(setR);
        mergedSetR.addAll(otherSet.setR);

        return new TwoPSet<>(Collections.unmodifiableSet(mergedSetA), Collections.unmodifiableSet(mergedSetR));
    }
}



