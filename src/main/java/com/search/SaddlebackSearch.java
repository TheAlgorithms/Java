package com.search;

/**
 * Saddleback search is an algorithm which finds the position of a target value within a 2D sorted array
 */
public class SaddlebackSearch {

    /**
     * This method performs Saddleback Search
     *
     * @param array The **Sorted** array in which we will search the element.
     * @param value the element that we want to search for.
     * @return The index (row and column) of the element if found.
     * Else returns -1 -1.
     */
    public static <T extends Comparable<T>> int[] find(T[][] array, T value) {
        return search(array, value, array.length - 1, 0);
    }

    /**
     * This method performs Saddleback Search
     *
     * @param array The **Sorted** array in which we will search the element.
     * @param value the element that we want to search for.
     * @param row the current row.
     * @param col the current column.
     * @return The index (row and column) of the element if found.
     * Else returns -1 -1.
     */
    private static <T extends Comparable<T>> int[] search(T[][] array, T value, int row, int col) {
        if (row < 0 || col >= array[row].length) {
            return new int[] {-1, -1};
        }

        int compareTo = array[row][col].compareTo(value);

        if (compareTo == 0) {
            return new int[] {row, col};
        } else if (compareTo > 0) {
            return search(array, value, row - 1, col);
        }

        return search(array, value, row, col + 1);
    }

}
