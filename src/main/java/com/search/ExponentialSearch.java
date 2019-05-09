package src.main.java.com.search;

import java.util.Arrays;

/**
 * Exponential search (also called doubling search or galloping search or Struzik search) is an algorithm which finds
 * the position of a target value within an array. It works by determining a range that the search key resides in and
 * performing a binary search within that range
 * <p>
 * Worst-case performance	O(n)
 * Best-case performance	O(1)
 * Average performance	O(Log n)
 * Worst-case space complexity	O(Log n)
 */
public class ExponentialSearch {
    /**
     * @param array is an array where the element should be found
     * @param key   is an element which should be found
     * @param <T>   is any comparable type
     * @return The index position of the key in the array, returns -1 for empty array
     */
    public <T extends Comparable<T>> int findIndex(T[] array, T key) {
        int size = array.length;
        if(size == 0)
            return -1;
        // If the element is present at first position
        if (array[0] == key)
            return 0;

        // Find the range for binary search by repeated doubling
        int i = 1;
        while (i < size && array[i].compareTo(key) <= 0) {
            i = i * 2;
        }

        // Call binary search for the range found
        return Arrays.binarySearch(array, i / 2, Math.min(i, size), key);
    }
}
