package com.thealgorithms.sorts;

public class StalinSort implements SortAlgorithm {
    @SuppressWarnings("unchecked")
    public <T extends Comparable<T>> T[] sort(T[] array) {
        if (array.length == 0) {
            return array;
        }

        int currentIndex = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i].compareTo(array[currentIndex]) >= 0) {
                currentIndex++;
                array[currentIndex] = array[i];
            }
        }

        // Create a result array with sorted elements
        T[] result = (T[]) java.lang.reflect.Array.newInstance(array.getClass().getComponentType(), currentIndex + 1);
        System.arraycopy(array, 0, result, 0, currentIndex + 1);
        return result;
    }

    // Driver Program
    public static void main(String[] args) {
        // Integer Input
        Integer[] integers = {4, 23, 6, 78, 1, 54, 231, 9, 12};
        StalinSort stalinSort = new StalinSort();
        // print a sorted array
        SortUtils.print(stalinSort.sort(integers));
        // String Input
        String[] strings = {"c", "a", "e", "b", "d"};
        SortUtils.print(stalinSort.sort(strings));
    }
}
