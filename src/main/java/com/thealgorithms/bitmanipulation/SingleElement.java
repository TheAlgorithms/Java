package com.thealgorithms.bitmanipulation;

/**
 * Utility class to find the single non-duplicate element from an array
 * where all other elements appear twice.
 * <p>
 * The algorithm runs in O(n) time complexity and O(1) space complexity
 * using bitwise XOR.
 * </p>
 *
 * @author <a href="http://github.com/tuhinm2002">Tuhin M</a>
 */
public final class SingleElement {

    /**
     * Private constructor to prevent instantiation of this utility class.
     * Throws an UnsupportedOperationException if attempted.
     */
    private SingleElement() {
        throw new UnsupportedOperationException("Utility Class");
    }

    /**
     * Finds the single non-duplicate element in an array where every other
     * element appears exactly twice. Uses bitwise XOR to achieve O(n) time
     * complexity and O(1) space complexity.
     *
     * @param arr the input array containing integers where every element
     *            except one appears exactly twice
     * @return the single non-duplicate element
     */
    public static int findSingleElement(int[] arr) {
        int ele = 0;
        for (int i = 0; i < arr.length; i++) {
            ele ^= arr[i];
        }
        return ele;
    }
}
