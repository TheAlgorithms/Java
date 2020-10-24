package Sorts;

import static Sorts.SortUtils.print;

/**
 * This method implements the Generic Merge Sort
 *
 * @author Varun Upadhyay (https://github.com/varunu28)
 * @author Podshivalov Nikita (https://github.com/nikitap492)
 * @see SortAlgorithm
 */
class MergeSort implements SortAlgorithm {

  /**
   * This method implements the Generic Merge Sort
   *
   * @param unsorted the array which should be sorted
   * @param <T> Comparable class
   * @return sorted array
   */
  @Override
  public <T extends Comparable<T>> T[] sort(T[] unsorted) {
    doSort(unsorted, 0, unsorted.length - 1);
    return unsorted;
  }

  /**
   * @param arr The array to be sorted
   * @param left The first index of the array
   * @param right The last index of the array Recursively sorts the array in increasing order
   */
  private static <T extends Comparable<T>> void doSort(T[] arr, int left, int right) {
    if (left < right) {
      int mid = left + (right - left) / 2;
      doSort(arr, left, mid);
      doSort(arr, mid + 1, right);
      merge(arr, left, mid, right);
    }
  }

  /**
   * This method implements the merge step of the merge sort
   *
   * @param arr The array to be sorted
   * @param left The first index of the array
   * @param mid The middle index of the array
   * @param right The last index of the array merges two parts of an array in increasing order
   */
  private static <T extends Comparable<T>> void merge(T[] arr, int left, int mid, int right) {
    int length = right - left + 1;
    T[] temp = (T[]) new Comparable[length];
    int i = left;
    int j = mid + 1;
    int k = 0;

    while (i <= mid && j <= right) {
      if (arr[i].compareTo(arr[j]) <= 0) {
        temp[k++] = arr[i++];
      } else {
        temp[k++] = arr[j++];
      }
    }

    while (i <= mid) {
      temp[k++] = arr[i++];
    }

    while (j <= right) {
      temp[k++] = arr[j++];
    }

    System.arraycopy(temp, 0, arr, left, length);
  }

  // Driver program
  public static void main(String[] args) {

    // Integer Input
    Integer[] arr = {4, 23, 6, 78, 1, 54, 231, 9, 12};
    MergeSort mergeSort = new MergeSort();
    mergeSort.sort(arr);

    // Output => 1	   4  	 6	9	12	23	54	78	231
    print(arr);

    // String Inpu
    String[] stringArray = {"c", "a", "e", "b", "d"};
    mergeSort.sort(stringArray);
    // Output => a	b	c	d	e
    print(stringArray);
  }
}
