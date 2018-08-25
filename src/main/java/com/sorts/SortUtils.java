package src.main.java.com.sorts;

final class SortUtils {


  /**
   * Helper method for swapping places in array
   *
   * @param array The array which elements we want to swap
   * @param idx   index of the first element
   * @param idy   index of the second element
   */
  static <T> boolean swap(T[] array, int idx, int idy) {
    T swap = array[idx];
    array[idx] = array[idy];
    array[idy] = swap;
    return true;
  }


  /**
   * This method checks if first element is less then the other element
   *
   * @param v first element
   * @param w second element
   * @return true if the first element is less then the second element
   */
  static <T extends Comparable<T>> boolean less(T v, T w) {
    return v.compareTo(w) < 0;
  }


  /**
   * Swaps all position from {@param left} to @{@param right} for {@param array}
   *
   * @param array is an array
   * @param left  is a left flip border of the array
   * @param right is a right flip border of the array
   */
  static <T extends Comparable<T>> void flip(T[] array, int left, int right) {
    while (left <= right) {
      swap(array, left++, right--);
    }
  }
}