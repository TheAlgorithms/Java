package Sorts;

import java.util.Random;

/**
 * BubbleSort algorithm implements using recursion
 */
public class BubbleSortRecursion implements SortAlgorithm {
    public static void main(String[] args) {
        Integer[] array = new Integer[10];

        Random random = new Random();
        /* generate 10 random numbers from -50 to 49 */
        for (int i = 0; i < array.length; ++i) {
            array[i] = random.nextInt(100) - 50;
        }

        BubbleSortRecursion bubbleSortRecursion = new BubbleSortRecursion();
        bubbleSortRecursion.sort(array);

        /* check array is sorted or not */
        for (int i = 0; i < array.length - 1; ++i) {
            assert (array[i].compareTo(array[i + 1]) <= 0);
        }
    }

    /**
     * @param unsorted - an array should be sorted
     * @return sorted array
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] unsorted) {
        bubbleSort(unsorted, unsorted.length);
        return unsorted;
    }

    /**
     * BubbleSort algorithm implements using recursion
     *
     * @param unsorted array contains elements
     * @param len      length of given array
     */
    private static <T extends Comparable<T>> void bubbleSort(T[] unsorted, int len) {
        boolean swapped = false; /* flag to check if array is sorted or not */
        for (int i = 0; i < len - 1; ++i) {
            if (SortUtils.greater(unsorted[i], unsorted[i + 1])) {
                SortUtils.swap(unsorted, i, i + 1);
                swapped = true;
            }
        }
        if (swapped) {
            bubbleSort(unsorted, len - 1);
        }
    }
}
