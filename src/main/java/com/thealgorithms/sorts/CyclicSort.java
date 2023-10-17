//cyclic sort works and is used only if the array consists every elements in range from m to n
package com.thealgorithms.sorts;

import static com.thealgorithms.sorts.SortUtils.swap;
import static com.thealgorithms.sorts.SortUtils.print;

/**
 * visit:  https://youtu.be/JfinxytTYFQ?si=eDj2bHo_r1YF4wdJ
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
