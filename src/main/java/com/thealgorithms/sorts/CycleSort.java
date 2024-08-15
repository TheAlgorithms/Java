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
     * @param <T>   The type of elements in the array, which must be comparable
     * @param array The array to be sorted
     * @return The sorted array
     */
    @Override
    public <T extends Comparable<T>> T[] sort(final T[] array) {
        final int length = array.length;

        for (int cycleStart = 0; cycleStart <= length - 2; cycleStart++) {
            T item = array[cycleStart];
            int pos = findPosition(array, cycleStart, item);

            if (pos == cycleStart) {
                continue; // Item is already in the correct position
            }

            item = placeItem(array, item, pos);

            // Rotate the rest of the cycle
            while (pos != cycleStart) {
                pos = findPosition(array, cycleStart, item);
                item = placeItem(array, item, pos);
            }
        }
        return array;
    }

    /**
     * Finds the correct position for the given item starting from cycleStart.
     *
     * @param <T>        The type of elements in the array, which must be comparable
     * @param array      The array to be sorted
     * @param cycleStart The starting index of the cycle
     * @param item       The item whose position is to be found
     * @return The correct position of the item
     */
    private <T extends Comparable<T>> int findPosition(final T[] array, final int cycleStart, final T item) {
        int pos = cycleStart;
        for (int i = cycleStart + 1; i < array.length; i++) {
            if (SortUtils.less(array[i], item)) {
                pos++;
            }
        }
        return pos;
    }

    /**
     * Places the item in its correct position, handling duplicates, and returns the displaced item.
     *
     * @param <T>   The type of elements in the array, which must be comparable
     * @param array The array being sorted
     * @param item  The item to be placed
     * @param pos   The position where the item is to be placed
     * @return The displaced item
     */
    private <T extends Comparable<T>> T placeItem(final T[] array, final T item, int pos) {
        while (item.compareTo(array[pos]) == 0) {
            pos++;
        }
        return replace(array, pos, item);
    }

    /**
     * Replaces an element in the array with the given item and returns the replaced item.
     *
     * @param <T>   The type of elements in the array, which must be comparable
     * @param array The array in which the replacement will occur
     * @param pos   The position at which the replacement will occur
     * @param item  The item to be placed in the array
     * @return The replaced item
     */
    private <T extends Comparable<T>> T replace(final T[] array, final int pos, final T item) {
        final T replacedItem = array[pos];
        array[pos] = item;
        return replacedItem;
    }
}
