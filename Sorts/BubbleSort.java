package Sorts;

import static Sorts.SortUtils.*;

/**
 * @author Varun Upadhyay (https://github.com/varunu28)
 * @author Podshivalov Nikita (https://github.com/nikitap492)
 * @see SortAlgorithm
 */
class BubbleSort implements SortAlgorithm {

  /**
   * Implements generic bubble sort algorithm.
   *
   * @param array the array to be sorted.
   * @param <T> the type of elements in the array.
   * @return the sorted array.
   */
  @Override
  public <T extends Comparable<T>> T[] sort(T[] array) {
    for (int i = 0, size = array.length; i < size - 1; ++i) {
      boolean swapped = false;
      for (int j = 0; j < size - 1 - i; ++j) {
        if (greater(array[j], array[j + 1])) {
          swap(array, j, j + 1);
          swapped = true;
        }
      }
      if (!swapped) {
        break;
      }
    }
    return array;
  }

  /** Driver Code */
  public static void main(String[] args) {

    Integer[] integers = {4, 23, 6, 78, 1, 54, 231, 9, 12};
    BubbleSort bubbleSort = new BubbleSort();
    bubbleSort.sort(integers);

    for (int i = 0; i < integers.length - 1; ++i) {
      assert integers[i] <= integers[i + 1];
    }
    print(integers); /* output: [1, 4, 6, 9, 12, 23, 54, 78, 231] */

    String[] strings = {"c", "a", "e", "b", "d"};
    bubbleSort.sort(strings);
    for (int i = 0; i < strings.length - 1; i++) {
      assert strings[i].compareTo(strings[i + 1]) <= 0;
    }
    print(bubbleSort.sort(strings)); /* output: [a, b, c, d, e] */
  }
}
