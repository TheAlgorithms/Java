package com.thealgorithms.searches;

import com.thealgorithms.devutils.searches.SearchAlgorithm;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * A Random Search algorithm that randomly selects an index and checks if the
 * value at that index matches the target. It repeats the process until it
 * finds the target or checks all elements.
 *
 * <p>
 * Time Complexity: O(n) in the worst case.
 * </p>
 *
 * @author Hardvan
 */
public class RandomSearch implements SearchAlgorithm {

    private final Random random = new Random();

    /**
     * Finds the index of a given element using random search.
     *
     * @param array Array to search through
     * @param key Element to search for
     * @return Index of the element if found, -1 otherwise
     */
    @Override
    public <T extends Comparable<T>> int find(T[] array, T key) {
        Set<Integer> visitedIndices = new HashSet<>();
        int size = array.length;

        while (visitedIndices.size() < size) {
            int randomIndex = random.nextInt(size);
            if (array[randomIndex].compareTo(key) == 0) {
                return randomIndex;
            }
            visitedIndices.add(randomIndex);
        }

        return -1;
    }
}
