package com.thealgorithms.sorts;

/**
 * Dual Pivot Quick Sort Algorithm
 * 
 * @author Debasish Biswas (https://github.com/debasishbsws) *
 * @see SortAlgorithm
 */
public class DualPivotQuickSort implements SortAlgorithm {

    /**
     * This method implements the Dual pivot Quick Sort
     *
     * @param array The array to be sorted Sorts the array in increasing order
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        dualPivotQuicksort(array, 0, array.length - 1);
        return array;
    }

    /**
     * The sorting process
     *
     * @param left  The first index of an array
     * @param right The last index of an array
     * @param array The array to be sorted
     */
    private static <T extends Comparable<T>> void dualPivotQuicksort(T[] array, int left, int right) {
        if (left < right) {
            int[] pivots = partition(array, left, right);

            dualPivotQuicksort(array, left, pivots[0] - 1);
            dualPivotQuicksort(array, pivots[0] + 1, pivots[1] - 1);
            dualPivotQuicksort(array, pivots[1] + 1, right);
        }
    }

    /**
     * This method finds the partition indices for an array
     *
     * @param array The array to be sorted
     * @param left  The first index of an array
     * @param right The last index of an array Finds the partition index of an array
     */
    private static <T extends Comparable<T>> int[] partition(T[] array, int left, int right) {
        if (array[left].compareTo(array[right]) > 0)
            swap(array, left, right);

        T pivot1 = array[left];
        T pivot2 = array[right];

        int j = left + 1;
        int less = left + 1;
        int great = right - 1;

        while (less <= great) {
            // If element is less than pivot1
            if (array[less].compareTo(pivot1) < 0) {
                swap(array, less, left++);
            }

            // If element is greater or equal to pivot2
            else if (array[less].compareTo(pivot2) >= 0) {
                while (less < great && array[great].compareTo(pivot2) > 0)
                    great--;

                swap(array, less, great--);

                if (array[less].compareTo(pivot1) < 0)
                    swap(array, less, left++);

            }

            less++;
        }
        j--;
        great++;
        // Bring the pivots to their appropriate positions
        swap(array, left, j);
        swap(array, right, great);

        // return the pivots' indices
        return new int[] { less, great };
    }

    private static <T extends Comparable<T>> void swap(T[] array, int left, int right) {
        T temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    /**
     * Main method
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Integer array[] = { 24, 8, -42, 75, -29, -77, 38, 57 };
        DualPivotQuickSort dualPivotQuickSort = new DualPivotQuickSort();
        dualPivotQuickSort.sort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

    /*
     * References: https://www.geeksforgeeks.org/dual-pivot-quicksort/
     */

}