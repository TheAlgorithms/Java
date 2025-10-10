package com.thealgorithms.sorts;

/**
 * Implementation of Pancake Sort Algorithm.
 * 
 * Pancake sort is a variation of the selection sort algorithm. 
 * Given an unsorted array, we can perform only flip operation on the array. 
 * Flip operation: reverse array from index 0 to i where i can be any valid index.
 * 
 * The idea is to do something similar to Selection Sort. We one by one place maximum element 
 * at the end and reduce the size of current array by one.
 * 
 * Time Complexity: O(n²)
 * Space Complexity: O(1)
 * 
 * @author Podshivalov Nikita (https://github.com/nikitap492)
 * @since 2018-04-10
 * @see <a href="https://en.wikipedia.org/wiki/Pancake_sorting">Pancake Sort</a>
 */
public class PancakeSort implements SortAlgorithm {

    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        if (array.length < 2) {
            return array;
        }

        // Start from the complete array and one by one reduce current size by one
        for (int currentSize = array.length; currentSize > 1; currentSize--) {
            // Find index of the maximum element in array[0...currentSize-1]
            int maxIndex = findMaxIndex(array, currentSize);
            
            // If maximum element is not at the end, then we need to perform flips
            if (maxIndex != currentSize - 1) {
                // First flip brings the maximum element to the front (if not already there)
                if (maxIndex != 0) {
                    SortUtils.flip(array, 0, maxIndex);
                }
                // Second flip brings the maximum element to its correct position at the end
                SortUtils.flip(array, 0, currentSize - 1);
            }
        }

        return array;
    }

    /**
     * Finds the index of the maximum element in the array up to the given size.
     *
     * @param array      the array to be searched
     * @param currentSize the current size of the unsorted portion of the array
     * @param <T>        the type of elements in the array
     * @return the index of the maximum element
     */
    private <T extends Comparable<T>> int findMaxIndex(T[] array, int currentSize) {
        T max = array[0];
        int maxIndex = 0;
        for (int i = 1; i < currentSize; i++) {
            if (SortUtils.less(max, array[i])) {
                max = array[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
