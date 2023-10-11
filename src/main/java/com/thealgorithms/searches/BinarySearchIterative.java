package com.thealgorithms.searches;

import com.thealgorithms.devutils.searches.SearchAlgorithm;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * Binary search is one of the most popular algorithms The algorithm finds the
 * position of a target value within a sorted array
 *
 * <p>
 * Worst-case performance O(log n) Best-case performance O(1) Average
 * performance O(log n) Worst-case space complexity O(1)
 *
 * @author Varun Upadhyay (https://github.com/varunu28)
 * @author Podshivalov Nikita (https://github.com/nikitap492)
 * @see SearchAlgorithm
 * @see IterativeBinarySearch
 */
class BinarySearchIterative implements SearchAlgorithm {

    /**
     * @param array is an array where the element should be found
     * @param key is an element which should be found
     * @param <T> is any comparable type
     * @return index of the element
     */
    @Override
    public <T extends Comparable<T>> int find(T[] array, T key) {
        return search(array, key, 0, array.length - 1);
    }

    /**
     * This method implements the Generic Binary Search iteratively.
     *
     * @param array The array to make the binary search
     * @param key   The number you are looking for
     * @return the location of the key, or -1 if not found
     */
    private static <T extends Comparable<T>> int search(T[] array, T key, int left, int right) {
        while (left <= right) {
            int median = (left + right) >>> 1;
            int comp = key.compareTo(array[median]);

            if (comp == 0) {
                return median; // Key found
            }

            if (comp < 0) {
                right = median - 1; // Adjust the right bound
            } else {
                left = median + 1; // Adjust the left bound
            }
        }
        
        return -1; // Key not found
    }

    // Driver Program
    public static void main(String[] args) {
        // Just generate data
        Random r = ThreadLocalRandom.current();

        int size = 100;
        int maxElement = 100000;

        Integer[] integers = IntStream.generate(() -> r.nextInt(maxElement)).limit(size).sorted().boxed().toArray(Integer[] ::new);

        // The element that should be found
        int shouldBeFound = integers[r.nextInt(size - 1)];

        BinarySearch search = new BinarySearch();
        int atIndex = search.find(integers, shouldBeFound);

        System.out.printf("Should be found: %d. Found %d at index %d. An array length %d%n", shouldBeFound, integers[atIndex], atIndex, size);

        int toCheck = Arrays.binarySearch(integers, shouldBeFound);
        System.out.printf("Found by system method at an index: %d. Is equal: %b%n", toCheck, toCheck == atIndex);
    }
}
