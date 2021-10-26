package Maths;

import java.util.Arrays;
import java.util.Random;

public class FindMinRecursion {

  /** Driver Code */
  public static void main(String[] args) {
    Random rand = new Random();

    /* rand size */
    int size = rand.nextInt(100) + 1;
    int[] array = new int[size];

    /* init array with rand numbers */
    for (int i = 0; i < size; i++) {
      array[i] = rand.nextInt() % 100;
    }

    assert min(array, 0, array.length - 1) == Arrays.stream(array).min().getAsInt();
    assert min(array, array.length) == Arrays.stream(array).min().getAsInt();
  }

  /**
   * Get min of array using divide and conquer algorithm
   *
   * @param array contains elements
   * @param low the index of the first element
   * @param high the index of the last element
   * @return min of {@code array}
   */
  public static int min(int[] array, int low, int high) {
    if (low == high) {
      return array[low]; // or array[high]
    }

    int mid = (low + high) >>> 1;

    int leftMin = min(array, low, mid); // get min in [low, mid]
    int rightMin = min(array, mid + 1, high); // get min in [mid+1, high]

    return Math.min(leftMin, rightMin);
  }

  /**
   * Get min of array using recursion algorithm
   *
   * @param array contains elements
   * @param len length of given array
   * @return min value of {@code array}
   */
  public static int min(int[] array, int len) {
    return len == 1 ? array[0] : Math.min(min(array, len - 1), array[len - 1]);
  }
}
