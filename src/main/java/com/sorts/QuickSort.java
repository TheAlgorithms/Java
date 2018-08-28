package src.main.java.com.sorts;

import static src.main.java.com.sorts.SortUtils.less;
import static src.main.java.com.sorts.SortUtils.swap;

public class QuickSort {


  /**
   * This method implements the Generic Quick Sort
   *
   * @param array The array to be sorted
   *              Sorts the array in increasing order
   **/
  public <T extends Comparable<T>> T[] sort(T[] array) {
    doSort(array, 0, array.length - 1);
    return array;
  }


  /**
   * The sorting process
   *
   * @param left  The first index of an array
   * @param right The last index of an array
   * @param array The array to be sorted
   **/

  private static <T extends Comparable<T>> void doSort(T[] array, int left, int right) {
    if (left < right) {
      int pivot = partition(array, left, right);
      doSort(array, left, pivot - 1);
      doSort(array, pivot, right);
    }
  }

  /**
   * This method finds the partition index for an array
   *
   * @param array The array to be sorted
   * @param left  The first index of an array
   * @param right The last index of an array
   *              Finds the partition index of an array
   **/

  private static <T extends Comparable<T>> int partition(T[] array, int left, int right) {
    int mid = (left + right) / 2;
    T pivot = array[mid];

    while (left <= right) {
      while (less(array[left], pivot)) {
        ++left;
      }
      while (less(pivot, array[right])) {
        --right;
      }
      if (left <= right) {
        swap(array, left, right);
        ++left;
        --right;
      }
    }
    return left;
  }
}