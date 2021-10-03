package Maths;

import java.util.Arrays;
import java.lang.Math;

/**
 * description:
 *
 * <p>absMin([0, 5, 1, 11]) = 0, absMin([3 , -10, -2]) = -2
 */
public class AbsoluteMin {
  public static void main(String[] args) {
    int[] testnums = {4, 0, 16};
    assert absMin(testnums) == 0;

    int[] numbers = {3, -10, -2};
    System.out.println("absMin(" + Arrays.toString(numbers) + ") = " + absMin(numbers));
  }

  /**
   * get the value, returns the absolute min value min
   *
   * @param numbers contains elements
   * @return the absolute min value
   */
  public static int absMin(int[] numbers) {
    int absMinValue = numbers[0];
    for (int i = 1, length = numbers.length; i < length; ++i) {
      absMinValue = Math.min(absMinValue , numbers[i]);
    }
    return absMinValue;
  }
}
