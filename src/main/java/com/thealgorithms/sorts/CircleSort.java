package com.thealgorithms.sorts;

import static com.thealgorithms.sorts.SortUtils.*;

public class CircleSort implements SortAlgorithm {

    /* This method implements the circle sort
    * @param array The array to be sorted 
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        int n = array.length;
        while (doSort(array, 0, n - 1));
        return array;
    }

    /* This method implements the cyclic sort recursive version
    * @param array The array to be sorted
    * @param the left boundary of the part currently being sorted
    * @param the right boundary of the part currently being sorted
     */
    private <T extends Comparable<T>> Boolean doSort(T[] array, int left, int right) {
        Boolean swapped = false;

        if (left == right) {
            return false;
        }

        int low = left;
        int high = right;

        while (low < high) {
            if (array[low].compareTo(array[high]) > 0) {
                swap(array, low, high);
                swapped = true;
            }
            low++;
            high--;
        }

        if (low == high && array[low].compareTo(array[high + 1]) > 0) {
            swap(array, low, high + 1);
            swapped = true;
        }

        int mid = left + (right - left) / 2;
        Boolean leftHalf = doSort(array, left, mid);
        Boolean rightHalf = doSort(array, mid + 1, right);

        return swapped || leftHalf || rightHalf;
    }

    /* Driver code*/
    public static void main(String[] args) {
        CircleSort CSort = new CircleSort();

        Integer[] arr = {4, 23, 6, 78, 1, 54, 231, 9, 12};
        CSort.sort(arr);
        for (int i = 0; i < arr.length - 1; ++i) {
            assert arr[i] <= arr[i + 1];
        }

        String[] stringArray = {"c", "a", "e", "b", "d"};
        CSort.sort(stringArray);
        for (int i = 0; i < stringArray.length - 1; ++i) {
            assert arr[i].compareTo(arr[i + 1]) <= 0;
        }
    }
}
