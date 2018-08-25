package src.main.java.com.sorts;

import static src.main.java.com.sorts.SortUtils.less;
import static src.main.java.com.sorts.SortUtils.swap;

public class ShellSort {

  /**
   * This method implements Generic Shell Sort.
   *
   * @param array The array to be sorted
   */
  public <T extends Comparable<T>> T[] sort(T[] array) {
    int length = array.length;
    int n = 1;

    while (n < length / 3) {
      n = 3 * n + 1;
    }

    while (n >= 1) {
      for (int i = n; i < length; i++) {
        for (int j = i; j >= n && less(array[j], array[j - n]); j -= n) {
          swap(array, j, j - n);
        }
      }
      n /= 3;
    }

    return array;
  }
}