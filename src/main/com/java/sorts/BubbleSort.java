package src.main.com.java.sorts;

import static src.main.com.java.sorts.SortUtils.less;
import static src.main.com.java.sorts.SortUtils.swap;

public class BubbleSort {
  /**
   * This method implements the Generic Bubble Sort
   *
   * @param array The array to be sorted
   *              Sorts the array in increasing order
   **/
  public <T extends Comparable<T>> T[] sort(T[] array) {
    int last = array.length;
    //Sorting
    boolean swap;
    do {
      swap = false;
      for (int count = 0; count < last - 1; count++) {
        if (less(array[count + 1], array[count])) {
          swap = swap(array, count, count + 1);
        }
      }
      last--;
    } while (swap);
    return array;
  }
}