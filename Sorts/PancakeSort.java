package Sorts;

import static Sorts.SortUtils.*;

/**
 * Implementation of gnome sort
 *
 * @author Podshivalov Nikita (https://github.com/nikitap492)
 * @since 2018-04-10
 */
public class PancakeSort implements SortAlgorithm {

  @Override
  public <T extends Comparable<T>> T[] sort(T[] array) {
    int size = array.length;

    for (int i = 0; i < size; i++) {
      T max = array[0];
      int index = 0;
      for (int j = 0; j < size - i; j++) {
        if (less(max, array[j])) {
          max = array[j];
          index = j;
        }
      }
      flip(array, index, array.length - 1 - i);
    }
    return array;
  }

  public static void main(String[] args) {

    Integer[] arr = {
      10, 9, 8, 7, 6, 15, 14, 7, 4, 3, 8, 6, 3, 1, 2, -2, -5, -8, -3, -1, 13, 12, 11, 5, 4, 3, 2, 1
    };
    PancakeSort pancakeSort = new PancakeSort();
    System.out.println("After sorting:");
    pancakeSort.sort(arr);
    print(arr);
  }
}
