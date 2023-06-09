package com.thealgorithms.searches;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Interpolation search algorithm implementation
 *
 * <p>
 * Worst-case performance O(n) Best-case performance O(1) Average performance
 * O(log(log(n))) if the elements are uniformly distributed if not O(n)
 * Worst-case space complexity O(1)
 *
 * @author Podshivalov Nikita (https://github.com/nikitap492)
 */
class InterpolationSearch {

    /**
     * @param array is a sorted array
     * @param key is a value what shoulb be found in the array
     * @return an index if the array contains the key unless -1
     */
    public int find(int[] array, int key) {
        // Find indexes of two corners
        int start = 0, end = (array.length - 1);

        // Since array is sorted, an element present
        // in array must be in range defined by corner
        while (start <= end && key >= array[start] && key <= array[end]) {
            // Probing the position with keeping
            // uniform distribution in mind.
            int pos = start + (((end - start) / (array[end] - array[start])) * (key - array[start]));

            // Condition of target found
            if (array[pos] == key) {
                return pos;
            }

            // If key is larger, key is in upper part
            if (array[pos] < key) {
                start = pos + 1;
            } // If key is smaller, x is in lower part
            else {
                end = pos - 1;
            }
        }
        return -1;
    }

    // Driver method
    public static void main(String[] args) {
        Random r = new Random();
        int size = 100;
        int maxElement = 100000;
        int[] integers = IntStream.generate(() -> r.nextInt(maxElement)).limit(size).sorted().toArray();

        // the element that should be found
        int shouldBeFound = integers[r.nextInt(size - 1)];

        InterpolationSearch search = new InterpolationSearch();
        int atIndex = search.find(integers, shouldBeFound);

        System.out.printf("Should be found: %d. Found %d at index %d. An array length %d%n", shouldBeFound, integers[atIndex], atIndex, size);

        int toCheck = Arrays.binarySearch(integers, shouldBeFound);
        System.out.printf("Found by system method at an index: %d. Is equal: %b%n", toCheck, toCheck == atIndex);
    }
}
