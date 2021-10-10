package Sorts;

import static Sorts.SortUtils.*;

/**
 * Comb Sort algorithm implementation
 *
 * <p>Best-case performance O(n * log(n)) Worst-case performance O(n ^ 2) Worst-case space
 * complexity O(1)
 *
 * <p>Comb sort improves on bubble sort.
 *
 * @author Sandeep Roy (https://github.com/sandeeproy99)
 * @author Podshivalov Nikita (https://github.com/nikitap492)
 * @see BubbleSort
 * @see SortAlgorithm
 */
class CombSort implements SortAlgorithm {

  // To find gap between elements
  private int nextGap(int gap) {
    // Shrink gap by Shrink factor
    gap = (gap * 10) / 13;
    return (gap < 1) ? 1 : gap;
  }

  /**
   * Function to sort arr[] using Comb
   *
   * @param arr - an array should be sorted
   * @return sorted array
   */
  @Override
  public <T extends Comparable<T>> T[] sort(T[] arr) {
    int size = arr.length;

    // initialize gap
    int gap = size;

    // Initialize swapped as true to make sure that loop runs
    boolean swapped = true;

    // Keep running while gap is more than 1 and last iteration caused a swap
    while (gap != 1 || swapped) {
      // Find next gap
      gap = nextGap(gap);

      // Initialize swapped as false so that we can check if swap happened or not
      swapped = false;

      // Compare all elements with current gap
      for (int i = 0; i < size - gap; i++) {
        if (less(arr[i + gap], arr[i])) {
          // Swap arr[i] and arr[i+gap]
          swapped = swap(arr, i, i + gap);
        }
      }
    }
    return arr;
  }

  // Driver method
  public static void main(String[] args) {
    CombSort ob = new CombSort();
    Integer[] arr = {8, 4, 1, 56, 3, -44, -1, 0, 36, 34, 8, 12, -66, -78, 23, -6, 28, 0};
    ob.sort(arr);

    System.out.println("sorted array");
    print(arr);
  }
}
