package com.thealgorithms.sorts;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Tag Sort (also known as Index Sort or Dictionary Sort).
 *
 * <p>Tag Sort is a sorting technique that does not modify the original array.
 * Instead, it creates an array of indices (tags) and sorts those indices based
 * on the corresponding values in the original array. This is useful when you
 * need to know the original positions of elements after sorting.
 *
 * <p>Time Complexity: O(n log n)
 * Space Complexity: O(n)
 *
 * @see <a href="https://www.geeksforgeeks.org/tag-sort/">Tag Sort - GeeksForGeeks</a>
 */
public class TagSort implements SortAlgorithm {

    /**
     * Sorts the given array using Tag Sort.
     * Returns the sorted array (copy), leaving the original array unchanged.
     *
     * @param array the array to be sorted
     * @param <T>   the type of elements, must be Comparable
     * @return a new sorted array
     */
    @Override
    public <T extends Comparable<T>> T[] sort(final T[] array) {
        if (array.length == 0) {
            return array;
        }

        Integer[] tags = createTags(array);
        sortTags(tags, array);

        @SuppressWarnings("unchecked")
        T[] sortedArray = (T[]) new Comparable[array.length];
        for (int i = 0; i < array.length; i++) {
            sortedArray[i] = array[tags[i]];
        }

        System.arraycopy(sortedArray, 0, array, 0, array.length);
        return array;
    }

    /**
     * Returns the sorted indices (tags) of the given array without modifying it.
     *
     * @param array the input array
     * @param <T>   the type of elements, must be Comparable
     * @return an array of indices representing the sorted order
     */
    public <T extends Comparable<T>> Integer[] getSortedTags(final T[] array) {
        Integer[] tags = createTags(array);
        sortTags(tags, array);
        return tags;
    }

    private <T extends Comparable<T>> Integer[] createTags(final T[] array) {
        Integer[] tags = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            tags[i] = i;
        }
        return tags;
    }

    private <T extends Comparable<T>> void sortTags(final Integer[] tags, final T[] array) {
        Arrays.sort(tags, Comparator.comparing(i -> array[i]));
    }
}
