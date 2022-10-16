package com.thealgorithms.sorts;

/**
 * The Dutch National Flag Sort sorts a sequence of values into three permutations which are defined by a value given
 * as the indented middle.
 * First permutation: values less than middle.
 * Second permutation: values equal middle.
 * Third permutation: values greater than middle.
 * If no indented middle is given, this implementation will use a value from the given Array.
 * This value is the one positioned in the arrays' middle if the arrays' length is odd.
 * If the arrays' length is even, the value left to the middle will be used.
 * More information and Pseudocode: https://en.wikipedia.org/wiki/Dutch_national_flag_problem
 */
public class DutchNationalFlagSort implements SortAlgorithm {

    @Override
    public <T extends Comparable<T>> T[] sort(T[] unsorted) {
        return dutch_national_flag_sort(
            unsorted,
            unsorted[(int) Math.ceil((unsorted.length) / 2.0) - 1]
        );
    }

    public <T extends Comparable<T>> T[] sort(T[] unsorted, T intendedMiddle) {
        return dutch_national_flag_sort(unsorted, intendedMiddle);
    }

    private <T extends Comparable<T>> T[] dutch_national_flag_sort(
        T[] arr,
        T intendedMiddle
    ) {
        int i = 0;
        int j = 0;
        int k = arr.length - 1;

        while (j <= k) {
            if (0 > arr[j].compareTo(intendedMiddle)) {
                SortUtils.swap(arr, i, j);
                j++;
                i++;
            } else if (0 < arr[j].compareTo(intendedMiddle)) {
                SortUtils.swap(arr, j, k);
                k--;
            } else {
                j++;
            }
        }
        return arr;
    }
}
