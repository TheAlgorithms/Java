package com.thealgorithms.sorts;

public class StalinSort implements SortAlgorithm {
    @SuppressWarnings("unchecked")
    public <T extends Comparable<T>> T[] sort(T[] array) {
        if (array.length == 0) {
            return array;
        }
        int currentIndex = 0;
        for (int i = 1; i < array.length; i++) {
            if (SortUtils.greaterOrEqual(array[i], array[currentIndex])) {
                currentIndex++;
                array[currentIndex] = array[i];
            }
        }
        // Create a result array with sorted elements
        T[] result = (T[]) java.lang.reflect.Array.newInstance(array.getClass().getComponentType(), currentIndex + 1);
        System.arraycopy(array, 0, result, 0, currentIndex + 1);
        return result;
    }
}
