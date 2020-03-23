package com.search;

/**
 * A iterative version of a ternary search algorithm
 * This is better way to implement the ternary search, because a recursive version adds some overhead to a stack.
 * But in java the compile can transform the recursive version to iterative implicitly,
 * so there are no much differences between these two algorithms
 * <p>
 * Worst-case performance	Θ(log3(N))
 * Best-case performance	O(1)
 * Average performance	Θ(log3(N))
 * Worst-case space complexity	O(1)
 */
public final class IterativeTernarySearch {

    /**
     * @param array The **Sorted** array in which we will search the element.
     * @param value The value that we want to search for.
     * @return The index of the element if found.
     * Else returns -1.
     */
    public static <T extends Comparable<T>> int find(T[] array, T value) {
        int left = 0;
        int right = array.length - 1;

        while (right > left) {
            int leftCompareTo = array[left].compareTo(value);
            int rightCompareTo = array[right].compareTo(value);

            if (leftCompareTo == 0) {
                return left;
            }

            if (rightCompareTo == 0) {
                return right;
            }

            int leftThird = left + (right - left) / 3 + 1;
            int rightThird = right - (right - left) / 3 - 1;

            if (array[leftThird].compareTo(value) <= 0) {
                left = leftThird;
            } else {
                right = rightThird;
            }
        }

        return -1;
    }

}
