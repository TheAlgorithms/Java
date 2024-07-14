package com.thealgorithms.sorts;

/**
 * This class implements the cycle sort algorithm.
 * Cycle sort is an in-place sorting algorithm, unstable, and efficient for scenarios with limited memory usage.
 * @author Podshivalov Nikita (https://github.com/nikitap492)
 */
class CycleSort implements SortAlgorithm {
    /**
     * Sorts an array of comparable elements using the cycle sort algorithm.
     *
     * @param array the array to be sorted
     * @param <T> the type of elements in the array, must be comparable
     * @return the sorted array
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        for (int cycleStart = 0; cycleStart <= array.length - 2; cycleStart++) {
            T item = array[cycleStart];

            // Find the position where we put the element
            int pos = cycleStart;
            for (int i = cycleStart + 1; i < array.length; i++) {
                if (SortUtils.less(array[i], item)) {
                    pos++;
                }
            }

            // If the item is already in the correct position
            if (pos == cycleStart) {
                continue;
            }

            // Ignore all duplicate elements
            while (item.compareTo(array[pos]) == 0) {
                pos++;
            }

            // Put the item to its correct position
            if (pos != cycleStart) {
                item = replace(array, pos, item);
            }

            // Rotate the rest of the cycle
            while (pos != cycleStart) {
                pos = cycleStart;

                // Find the position where we put the element
                for (int i = cycleStart + 1; i < array.length; i++) {
                    if (SortUtils.less(array[i], item)) {
                        pos++;
                    }
                }

                // Ignore all duplicate elements
                while (item.compareTo(array[pos]) == 0) {
                    pos++;
                }

                // Put the item to its correct position
                if (item != array[pos]) {
                    item = replace(array, pos, item);
                }
            }
        }
        return array;
    }

    /**
     * Replaces an element in the array with the given item and returns the replaced item.
     *
     * @param array the array in which the replacement will occur
     * @param pos the position at which the replacement will occur
     * @param item the item to be placed in the array
     * @param <T> the type of elements in the array, must be comparable
     * @return the replaced item
     */
    private <T extends Comparable<T>> T replace(T[] array, int pos, T item) {
        T replacedItem = array[pos];
        array[pos] = item;
        return replacedItem;
    }
}
