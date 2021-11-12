package com.thealgorithms.searches;

import static java.lang.String.format;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import com.thealgorithms.devutils.searches.SearchAlgorithm;

/**
 * The LowerBound method is used to return an index pointing to the first
 * element in the range [first, last) which has a value not less than val, i.e.
 * the index of the next smallest number just greater than or equal to that
 * number. If there are multiple values that are equal to val it returns the
 * index of the first such value.
 *
 * <p>
 * This is an extension of BinarySearch.
 *
 * <p>
 * Worst-case performance O(log n) Best-case performance O(1) Average
 * performance O(log n) Worst-case space complexity O(1)
 *
 * @author Pratik Padalia (https://github.com/15pratik)
 * @see SearchAlgorithm
 * @see BinarySearch
 */
class LowerBound implements SearchAlgorithm {

    // Driver Program
    public static void main(String[] args) {
        // Just generate data
        Random r = ThreadLocalRandom.current();

        int size = 100;
        int maxElement = 100000;

        Integer[] integers
                = IntStream.generate(() -> r.nextInt(maxElement))
                        .limit(size)
                        .sorted()
                        .boxed()
                        .toArray(Integer[]::new);

        // The element for which the lower bound is to be found
        int val = integers[r.nextInt(size - 1)] + 1;

        LowerBound search = new LowerBound();
        int atIndex = search.find(integers, val);

        System.out.println(
                format(
                        "Val: %d. Lower Bound Found %d at index %d. An array length %d",
                        val, integers[atIndex], atIndex, size));

        boolean toCheck = integers[atIndex] >= val || integers[size - 1] < val;
        System.out.println(
                format(
                        "Lower Bound found at an index: %d. Is greater or max element: %b", atIndex, toCheck));
    }

    /**
     * @param array is an array where the LowerBound value is to be found
     * @param key is an element for which the LowerBound is to be found
     * @param <T> is any comparable type
     * @return index of the LowerBound element
     */
    @Override
    public <T extends Comparable<T>> int find(T[] array, T key) {
        return search(array, key, 0, array.length - 1);
    }

    /**
     * This method implements the Generic Binary Search
     *
     * @param array The array to make the binary search
     * @param key The number you are looking for
     * @param left The lower bound
     * @param right The upper bound
     * @return the location of the key
     */
    private <T extends Comparable<T>> int search(T[] array, T key, int left, int right) {
        if (right <= left) {
            return left;
        }

        // find median
        int median = (left + right) >>> 1;
        int comp = key.compareTo(array[median]);

        if (comp == 0) {
            return median;
        } else if (comp < 0) {
            // median position can be a possible solution
            return search(array, key, left, median);
        } else {
            // key we are looking is greater, so we must look on the right of median position
            return search(array, key, median + 1, right);
        }
    }
}
