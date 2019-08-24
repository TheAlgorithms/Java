package com.sorts;

import com.types.Sort;

public class BubbleSort<T> implements Sort<T> {
  /**
   * This method implements the Generic Bubble Sort
   *
   * @param array The array to be sorted
   *              Sorts the array in increasing order
   **/

  @Override
  public <T extends Comparable<T>> T[] sort(T[] array) {
    int last = array.length;
    //Sorting
    boolean swap;
    do {
      swap = false;
      for (int count = 0; count < last - 1; count++) {
        if (SortUtils.less(array[count + 1], array[count])) {
          swap = SortUtils.swap(array, count, count + 1);
        }
      }
      last--;
    } while (swap);
    return array;
  }
}