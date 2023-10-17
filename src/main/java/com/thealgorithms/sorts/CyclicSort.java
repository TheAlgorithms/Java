//cyclic sort works and is used only if the arrar elements range from 0 to n-1
//where n is length of array
package com.thealgorithms.sorts;

import static com.thealgorithms.sorts.SortUtils.swap;
import static com.thealgorithms.sorts.SortUtils.print;

/**
 * @author Podshivalov Nikita (https://github.com/M0hitReddy)
 */

class CyclicSort implements SortAlgorithm {
    @Override
    public <T extends Comparable<T>> T[] sort(T[] arr) {
        int n = arr.length;
        int i = 0;
        // traverse array elements
        while (i < n) {
            T item = arr[i];
            // if element at the index not equal to index then swap arr[i] with arr[arr[i]]
            if (arr[i].compareTo(arr[(int) item]) != 0) {
                swap(arr, i, (int) item);
            } else {
                i++;
            }
        }
        return arr;
    }

    /**
     * Driver Code
     */
    public static void main(String[] args) {
        Integer[] arr = { 8, 1, 4, 0, 9, 3, 6, 7, 2, 5 };
        CyclicSort cycleSort = new CyclicSort();
        cycleSort.sort(arr);
        System.out.println("After sort : ");
        print(arr);
    }
}