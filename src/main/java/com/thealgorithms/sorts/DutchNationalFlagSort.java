package com.thealgorithms.sorts;

/**
 * The Dutch National Flag Sort sorts a sequence of values into three permutations which are defined
 * by a value given as the indented middle. First permutation: values less than middle. Second
 * permutation: values equal middle. Third permutation: values greater than middle. If no indented
 * middle is given, this implementation will use a value from the given Array. This value is the one
 * positioned in the arrays' middle if the arrays' length is odd. If the arrays' length is even, the
 * value left to the middle will be used. More information and Pseudocode:
 * https://en.wikipedia.org/wiki/Dutch_national_flag_problem
 */
public class DutchNationalFlagSort implements SortAlgorithm {

    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        return dutchNationalFlagSort(array, array[(int) Math.ceil((array.length) / 2.0) - 1]);
    }

    public <T extends Comparable<T>> T[] sort(T[] array, T intendedMiddle) {
        return dutchNationalFlagSort(array, intendedMiddle);
    }

    private <T extends Comparable<T>> T[] dutchNationalFlagSort(final T[] array, final T intendedMiddle) {
        int i = 0;
        int j = 0;
        int k = array.length - 1;

        while (j <= k) {
            if (SortUtils.less(array[j], intendedMiddle)) {
                SortUtils.swap(array, i, j);
                j++;
                i++;
            } else if (SortUtils.greater(array[j], intendedMiddle)) {
                SortUtils.swap(array, j, k);
                k--;
            } else {
                j++;
            }
        }
        return array;
    }
}
