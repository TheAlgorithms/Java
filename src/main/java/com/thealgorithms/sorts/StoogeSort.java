package com.thealgorithms.sorts;

/**
 * @author Amir Hassan (https://github.com/ahsNT)
 * @see SortAlgorithm
 */
public class StoogeSort implements SortAlgorithm {

    @Override
    public <T extends Comparable<T>> T[] sort(T[] unsortedArray) {
        sort(unsortedArray, 0, unsortedArray.length);
        return unsortedArray;
    }

    public <T extends Comparable<T>> T[] sort(T[] unsortedArray, int start, int end) {
        if (SortUtils.less(unsortedArray[end - 1], unsortedArray[start])) {
            T temp = unsortedArray[start];
            unsortedArray[start] = unsortedArray[end - 1];
            unsortedArray[end - 1] = temp;
        }

        int len = end - start;
        if (len > 2) {
            int third = len / 3;
            sort(unsortedArray, start, end - third);
            sort(unsortedArray, start + third, end);
            sort(unsortedArray, start, end - third);
        }
        return unsortedArray;
    }

    public static void main(String[] args) {
        StoogeSort stoogeSort = new StoogeSort();

        Integer[] integerArray = {8, 84, 53, 953, 64, 2, 202};
        // Print integerArray unsorted
        SortUtils.print(integerArray);

        stoogeSort.sort(integerArray);
        // Print integerArray sorted
        SortUtils.print(integerArray);

        String[] stringArray = {"g", "d", "a", "b", "f", "c", "e"};
        // Print stringArray unsorted
        SortUtils.print(stringArray);

        stoogeSort.sort(stringArray);
        // Print stringArray sorted
        SortUtils.print(stringArray);
    }
}
