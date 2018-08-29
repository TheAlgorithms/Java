package src.main.java.com.sorts;

import static src.main.java.com.sorts.SortUtils.less;
import static src.main.java.com.sorts.SortUtils.swap;

public class SelectionSort {

  /**
   * This method implements the Generic Selection Sort
   *
   * @param arr The array to be sorted
   *            Sorts the array in increasing order
   **/
  public <T extends Comparable<T>> T[] sort(T[] arr) {
    int n = arr.length;
    for (int i = 0; i < n - 1; i++) {
      // Initial index of min
      int min = i;
      for (int j = i + 1; j < n; j++) {
        if (less(arr[j], arr[min])) {
          min = j;
        }
      }
      // Swapping if index of min is changed
      if (min != i) {
        swap(arr, i, min);
      }
    }

    return arr;
  }
}