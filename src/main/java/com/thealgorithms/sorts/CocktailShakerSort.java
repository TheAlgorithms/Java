package com.thealgorithms.sorts;

/**
 * CocktailShakerSort class implements the Cocktail Shaker Sort algorithm,
 * which is a bidirectional bubble sort. It sorts the array by passing
 * through it back and forth, progressively moving the largest elements
 * to the end and the smallest elements to the beginning.
 *
 * @author Mateus Bizzo (https://github.com/MattBizzo)
 * @author Podshivalov Nikita (https://github.com/nikitap492)
 */
class CocktailShakerSort implements SortAlgorithm {

    /**
     * Sorts the given array using the Cocktail Shaker Sort algorithm.
     *
     * @param <T> The type of elements in the array, which must be comparable
     * @param array The array to be sorted
     * @return The sorted array
     */
    @Override
    public <T extends Comparable<T>> T[] sort(final T[] array) {
        if (array.length == 0) {
            return array;
        }

        int left = 0;
        int right = array.length - 1;

        while (left < right) {
            right = performForwardPass(array, left, right);
            left = performBackwardPass(array, left, right);
        }

        return array;
    }

    /**
     * Performs a forward pass through the array, moving larger elements to the end.
     *
     * @param <T>   The type of elements in the array, which must be comparable
     * @param array The array being sorted
     * @param left  The current left boundary of the sorting area
     * @param right The current right boundary of the sorting area
     * @return The index of the last swapped element during this pass
     */
    private <T extends Comparable<T>> int performForwardPass(final T[] array, final int left, final int right) {
        int lastSwappedIndex = left;

        for (int i = left; i < right; i++) {
            if (SortUtils.less(array[i + 1], array[i])) {
                SortUtils.swap(array, i, i + 1);
                lastSwappedIndex = i;
            }
        }

        return lastSwappedIndex;
    }

    /**
     * Performs a backward pass through the array, moving smaller elements to the beginning.
     *
     * @param <T>   The type of elements in the array, which must be comparable
     * @param array The array being sorted
     * @param left  The current left boundary of the sorting area
     * @param right The current right boundary of the sorting area
     * @return The index of the last swapped element during this pass
     */
    private <T extends Comparable<T>> int performBackwardPass(final T[] array, final int left, final int right) {
        int lastSwappedIndex = right;

        for (int i = right; i > left; i--) {
            if (SortUtils.less(array[i], array[i - 1])) {
                SortUtils.swap(array, i - 1, i);
                lastSwappedIndex = i;
            }
        }

        return lastSwappedIndex;
    }
}
