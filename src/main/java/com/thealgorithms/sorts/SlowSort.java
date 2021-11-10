package com.thealgorithms.sorts;

/**
 * @author Amir Hassan (https://github.com/ahsNT)
 * @see SortAlgorithm
 */
public class SlowSort implements SortAlgorithm {

    @Override
    public <T extends Comparable<T>> T[] sort(T[] unsortedArray) {
        sort(unsortedArray, 0, unsortedArray.length - 1);
        return unsortedArray;
    }

    private <T extends Comparable<T>> void sort(T[] array, int i, int j) {
        if (SortUtils.greaterOrEqual(i, j)) {
            return;
        }
        int m = (i + j) / 2;
        sort(array, i, m);
        sort(array, m + 1, j);
        if (SortUtils.less(array[j], array[m])) {
            T temp = array[j];
            array[j] = array[m];
            array[m] = temp;
        }
        sort(array, i, j - 1);
    }

    public static void main(String[] args) {
        SlowSort slowSort = new SlowSort();

        Integer[] integerArray = {8, 84, 53, 953, 64, 2, 202, 98};
        // Print integerArray unsorted
        SortUtils.print(integerArray);

        slowSort.sort(integerArray);
        // Print integerArray sorted
        SortUtils.print(integerArray);

        String[] stringArray = {"g", "d", "a", "b", "f", "c", "e"};
        // Print stringArray unsorted
        SortUtils.print(stringArray);

        slowSort.sort(stringArray);
        // Print stringArray sorted
        SortUtils.print(stringArray);
    }
}
