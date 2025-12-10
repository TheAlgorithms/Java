package com.thealgorithms.maths;

public final class FindMinRecursion {

    private FindMinRecursion() {
    }

    /**
     * Get min of an array using divide and conquer algorithm
     *
     * @param array contains elements
     * @param low the index of the first element
     * @param high the index of the last element
     * @return min of {@code array}
     */

    public static int min(final int[] array, final int low, final int high) {
        if (array.length == 0) {
            throw new IllegalArgumentException("array must be non-empty.");
        }
        if (low == high) {
            return array[low]; // or array[high]
        }

        int mid = (low + high) >>> 1;

        int leftMin = min(array, low, mid); // get min in [low, mid]
        int rightMin = min(array, mid + 1, high); // get min in [mid+1, high]

        return Math.min(leftMin, rightMin);
    }

    /**
     * Get min of an array using recursion algorithm
     *
     * @param array contains elements
     * @return min value of {@code array}
     */
    public static int min(final int[] array) {
        return min(array, 0, array.length - 1);
    }
}
