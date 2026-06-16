package com.thealgorithms.sorts;

/**
 * Library Sort (also known as Gapped Insertion Sort) is traditionally implemented
 * using periodic gaps between elements for faster insertion. This implementation
 * uses binary search to find the insertion position combined with array shifting,
 * which is a simplified variant without gap-based optimization.
 * Time Complexity: O(n^2) worst case due to element shifting
 * Space Complexity: O(n)
 *
 * @see <a href="https://en.wikipedia.org/wiki/Library_sort">
 *     Wikipedia: Library Sort</a>
 * @author Vraj Prajapati (@Rosander0)
 */
public final class LibrarySort {

    private LibrarySort() {
        // Utility class
    }

    /**
     * Sorts an array using the Library Sort algorithm.
     *
     * @param array the array to sort (must not be null)
     * @return the sorted array
     * @throws IllegalArgumentException if {@code array} is {@code null}
     */
    public static int[] sort(final int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Input array must not be null.");
        }
        if (array.length <= 1) {
            return array;
        }

        int n = array.length;
        Integer[] spaced = new Integer[2 * n];

        spaced[0] = array[0];
        int inserted = 1;

        for (int i = 1; i < n; i++) {
            int pos = binarySearch(spaced, inserted, array[i]);
            for (int j = inserted; j > pos; j--) {
                spaced[j] = spaced[j - 1];
            }
            spaced[pos] = array[i];
            inserted++;
        }

        int idx = 0;
        for (int i = 0; i < 2 * n; i++) {
            if (spaced[i] != null) {
                array[idx++] = spaced[i];
            }
        }
        return array;
    }

    /**
     * Binary search to find insertion position among inserted elements.
     *
     * @param spaced the spaced array
     * @param inserted number of elements inserted so far
     * @param target the value to find position for
     * @return the correct insertion index
     */
    private static int binarySearch(final Integer[] spaced, final int inserted, final int target) {
        int lo = 0;
        int hi = inserted;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (spaced[mid] <= target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }
}
