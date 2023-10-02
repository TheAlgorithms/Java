package com.thealgorithms.bitmanipulation;

/**
 * Find the total number of set bit in a number.
 * @author Dhriendra Pratap Singh aliad (hanisntsolo) (https://github.com/hanisntsolo)
 */
public class CountNumberOfSetBits {
  public static int countNumberOfSetBits(int n) {
    if(n == 0) {
      return 0;
    }
    return (n & 1) + countNumberOfSetBits(n >> 1);
  }
}
