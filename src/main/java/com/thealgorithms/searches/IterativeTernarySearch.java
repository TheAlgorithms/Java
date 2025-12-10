package com.thealgorithms.searches;

import com.thealgorithms.devutils.searches.SearchAlgorithm;

/**
 * An iterative implementation of the Ternary Search algorithm.
 *
 * <p>
 * Ternary search is a divide-and-conquer algorithm that splits the array into three parts
 * instead of two, as in binary search. This implementation is iterative, reducing the overhead
 * associated with recursive function calls. However, the recursive version can also be optimized
 * by the Java compiler to resemble the iterative version, resulting in similar performance.
 *
 * <p>
 * Worst-case performance: Θ(log3(N))<br>
 * Best-case performance: O(1)<br>
 * Average performance: Θ(log3(N))<br>
 * Worst-case space complexity: O(1)
 *
 * <p>
 * This class implements the {@link SearchAlgorithm} interface, providing a generic search method
 * for any comparable type.
 *
 * @see SearchAlgorithm
 * @see TernarySearch
 * @since 2018-04-13
 */
public class IterativeTernarySearch implements SearchAlgorithm {

    @Override
    public <T extends Comparable<T>> int find(T[] array, T key) {
        if (array == null || array.length == 0 || key == null) {
            return -1;
        }
        if (array.length == 1) {
            return array[0].compareTo(key) == 0 ? 0 : -1;
        }

        int left = 0;
        int right = array.length - 1;

        while (right > left) {
            int leftCmp = array[left].compareTo(key);
            int rightCmp = array[right].compareTo(key);
            if (leftCmp == 0) {
                return left;
            }
            if (rightCmp == 0) {
                return right;
            }

            int leftThird = left + (right - left) / 3 + 1;
            int rightThird = right - (right - left) / 3 - 1;

            if (array[leftThird].compareTo(key) <= 0) {
                left = leftThird;
            } else {
                right = rightThird;
            }
        }

        return -1;
    }
}
