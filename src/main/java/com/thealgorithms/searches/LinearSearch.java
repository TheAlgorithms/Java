package com.thealgorithms.searches;

import com.thealgorithms.devutils.searches.SearchAlgorithm;

/**
 * Linear search is the easiest search algorithm It works with sorted and
 * unsorted arrays (an binary search works only with sorted array) This
 * algorithm just compares all elements of an array to find a value
 *
 * <p>
 * Worst-case performance O(n) Best-case performance O(1) Average performance
 * O(n) Worst-case space complexity
 *
 * @author Varun Upadhyay (https://github.com/varunu28)
 * @author Podshivalov Nikita (https://github.com/nikitap492)
 * @see BinarySearch
 * @see SearchAlgorithm
 */
public class LinearSearch implements SearchAlgorithm {

 /**
     * Generic Linear search method.
     *
     * <p>
     * This method takes an array of elements and a key to search for.
     * It traverses the array and compares each element with the key using
     * the {@code compareTo()} method. If a match is found, it returns
     * the index of the element; otherwise, it returns {@code -1}.
     *
     * <p>
     * The linear search algorithm can work on both sorted and unsorted data.
     * However, it has a time complexity of O(n) in the worst and average cases,
     * as it may need to check every element.
     *
     * <p>
     * <b>Note on {@link Comparable}:</b> <br>
     * The type parameter {@code <T extends Comparable<T>>} ensures that the elements of
     * the array implement the {@link Comparable} interface. This means each element knows
     * how to compare itself with another element of the same type using the
     * {@code compareTo()} method.
     *
     * <p>
     * Example usage:
     * <pre>{@code
     * if (array[i].compareTo(value) == 0) {
     *     return i;
     * }
     * }</pre>
     * The {@code compareTo()} method returns {@code 0} if both elements are equal.
     * Using {@code Comparable} allows this algorithm to work with any object type
     * (such as Integer, String, or custom classes) that defines its own comparison logic.
     *
     * @param array Array to be searched
     * @param value Key being searched for
     * @param <T>   The type of elements in the array, which must implement Comparable
     * @return Index of the key if found, otherwise -1
     */
    @Override
    public <T extends Comparable<T>> int find(T[] array, T value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].compareTo(value) == 0) {
                return i;
            }
        }
        return -1;
    }
}
