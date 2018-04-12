package search;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

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
    public  <T extends Comparable<T>> int find(T array[], T key) {
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

        if (comp < 0) {
            return search(array, key, left, median - 1);
        }

        if (comp > 0) {
            return search(array, key, median + 1, right);
        }

        return median;
    }

    // Driver Program
    public static void main(String[] args) {

        //just generate data
        Random r = new Random();
        int size = 100;
        int maxElement = 100000;
        Integer[] integers = Stream.generate(() -> r.nextInt(maxElement)).limit(size).sorted().toArray(Integer[]::new);


        //the element that should be found
        Integer shouldBeFound = integers[r.nextInt(size - 1)];

        BinarySearch search = new BinarySearch();
        int atIndex = search.find(integers, shouldBeFound);

        System.out.println(String.format("Should be found: %d. Found %d at index %d. An array length %d"
                , shouldBeFound, integers[atIndex], atIndex, size));


        int toCheck = Arrays.binarySearch(integers, shouldBeFound);
        System.out.println(format("Found by system method at an index: %d. Is equal: %b", toCheck, toCheck == atIndex));
    }
}
