package Searches;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static java.lang.String.format;

/**
 *
 *
 *
 * Binary search is one of the most popular algorithms
 * The algorithm finds the position of a target value within a sorted array
 *
 * Worst-case performance	O(log n)
 * Best-case performance	O(1)
 * Average performance	O(log n)
 * Worst-case space complexity	O(1)
 *
 *
 * @author Varun Upadhyay (https://github.com/varunu28)
 * @author Podshivalov Nikita (https://github.com/nikitap492)
 *
 * @see SearchAlgorithm
 * @see IterativeBinarySearch
 *
 */

class BinarySearch implements SearchAlgorithm {

    /**
     *
     * @param array is an array where the element should be found
     * @param key is an element which should be found
     * @param <T> is any comparable type
     * @return index of the element
     */
    @Override
    public  <T extends Comparable<T>> int find(T[] array, T key) {
        return search(array, key, 0, array.length);
    }

    /**
     * This method implements the Generic Binary Search
     *
     * @param array The array to make the binary search
     * @param key The number you are looking for
     * @param left The lower bound
     * @param right The  upper bound
     * @return the location of the key
     **/
    private <T extends Comparable<T>> int search(T array[], T key, int left, int right){
        if (right < left) return -1; // this means that the key not found

        // find median
        int median = (left + right) >>> 1;
        int comp = key.compareTo(array[median]);

        if (comp == 0) {
            return median;
        } else if (comp < 0) {
            return search(array, key, left, median - 1);
        } else {
            return search(array, key, median + 1, right);
        }
    }

    /**
     * this method implements the Binary Search without Recursion
     *
     * @param array the array to make the binary search
     * @param key   the number you are looking for
     * @param <T>   The type param
     * @return the index of the key
     */
    private static <T extends Comparable<T>> int search(T[] array, T key) {
        int low, high, mid;
        low = 0;
        high = array.length - 1;
        while (low <= high) {
            mid = (low + high) >> 1;
            if (key.compareTo(array[mid]) < 0) {
                high = mid;
            } else if (key.compareTo(array[mid]) > 0) {
                low = mid;
            } else {
                return mid;
            }
        }
        return -1;
    }


    // Driver Program
    public static void main(String[] args) {
        // Just generate data
        Random r = ThreadLocalRandom.current();

        int size = 100;
        int maxElement = 100000;

        Integer[] integers = IntStream.generate(() -> r.nextInt(maxElement)).limit(size).sorted().boxed().toArray(Integer[]::new);


        // The element that should be found
        int shouldBeFound = integers[r.nextInt(size - 1)];

        BinarySearch search = new BinarySearch();
        int atIndex = search.find(integers, shouldBeFound);

        System.out.println(format(
            "Should be found: %d. Found %d at index %d. An array length %d",
            shouldBeFound, integers[atIndex], atIndex, size
        ));

        int toCheck = Arrays.binarySearch(integers, shouldBeFound);
        System.out.println(format("Found by system method at an index: %d. Is equal: %b", toCheck, toCheck == atIndex));

        System.out.println("-------------test binarySearch without Recursion!!!---------------");

        int index = search(integers,shouldBeFound);
        System.out.println(format(
                "Should be found: %d. Found %d at index %d. An array length %d",
                shouldBeFound, integers[index], index, size
        ));
        int toCheck2 = Arrays.binarySearch(integers, shouldBeFound);
        System.out.println(format("Found by system method at an index: %d. Is equal: %b", toCheck2, toCheck2 == index));
    }
}
