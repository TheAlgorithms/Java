package com.sorts;

import com.types.Sort;
import static com.sorts.SortUtils.less;

/**
 * This method implements the Generic Merge Sort
 * Visit Wikipedia (https://en.wikipedia.org/wiki/Timsort) for more information on the topic
 *
 * @author Andreas Manos (https://github.com/Antwhite420)
 */
public class TimSort<T extends Comparable<T>> implements Sort<T> {

    private final int RUN = 32;

    /**
     * sort method sorts the given array
     *
     * @param array - an array should be sorted
     * @return the sorted array
     */
    @Override
    public T[] sort(T[] array) {
        for (int i = 0; i < array.length; i += RUN) {
            insertionSort(array, i, Math.min((i + 31), (array.length - 1)));
        }

        for (int size = RUN; size < array.length; size *= 2) {

            for (int left = 0; left < array.length; left += 2 * size) {

                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), (array.length - 1));
                merge(array, left, mid, right);
            }
        }
        return array;
    }

    /**
     * insertionSort method sorts the array from left index to right index
     *
     * @param arr   the original array
     * @param left  the first element of the array
     * @param right the middle element of the array
     */
    public <T extends Comparable<T>> void insertionSort(T[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            T temp = arr[i];
            int j = i - 1;
            while (j >= 0 && less(temp, arr[j])) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
        }
    }

    //

    /**
     * merge method merges the sorted RUNS in ascending order
     *
     * @param arr the original array
     * @param l   the first element of the array
     * @param m   the middle element of the array
     * @param r   the last element of the array
     */
    public <T extends Comparable<T>> void merge(T[] arr, int l,
                                                int m, int r) {

        // The array is splitted into two arrays left and right
        int len1 = m - l + 1, len2 = r - m;
        T[] left = (T[]) new Comparable[len1];
        T[] right = (T[]) new Comparable[len2];

        // Passing the elements from the original array to the sub arrays
        if (len1 >= 0) System.arraycopy(arr, l, left, 0, len1);
        if (len2 >= 0) System.arraycopy(arr, m + 1, right, 0, len2);

        int i = 0;
        int j = 0;
        int k = l;

        // Passing the elements to the original array after comparing them
        while (i < len1 && j < len2) {
            if (less(left[i], right[j]) || left[i].compareTo(right[j]) == 0) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }

        // Passing the remaining elements of left array, if any
        while (i < len1) {
            arr[k] = left[i];
            k++;
            i++;
        }

        // Passing the remaining element of right array, if any
        while (j < len2) {
            arr[k] = right[j];
            k++;
            j++;
        }
    }
}

