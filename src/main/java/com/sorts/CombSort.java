package com.sorts;

/**
 * CombSort improves BubbleSort.
 */
public class CombSort {

  /**
   * This method implements the generic CombSort.
   *
   * @param array The array to be sorted.
   * @return The input array in ascending sorted order.
   */
  public <T extends Comparable<T>> T[] sort(T[] array) {
    // Initialize gap with array size
    int gap = array.length;
    // Standard shrik factor is 1.3
    double shrink = 1.3;
    boolean sorted = false;

    while (!sorted) {
      // Calculate gap for next sorting phase
      gap = (int) Math.floor(gap / shrink);
      if (gap <= 1) {
        // In case gap gets < 1
        gap = 1;
        // Array is sorted if no further swaps occur
        sorted = true;
      }

      // Perform gaped-bubblesort
      for (int i = 0; i + gap < array.length; i++) {
        if (SortUtils.less(array[i + gap], array[i])) {
          SortUtils.swap(array, i, i + gap);
          // If gap = 1 and no swaps occur, array is sorted
          sorted = false;
        }
      }
    }

    return array;
  }

}
