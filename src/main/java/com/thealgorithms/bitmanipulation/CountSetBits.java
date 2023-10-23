package com.thealgorithms.bitmanipulation;

public class CountSetBits {

  /**
   * Counts the number of set bits in the binary representation of the given number.
   * Author: Siddhaarth Raj Gangaraj (https://github.com/SiddhaarthG)
   */

  public static int countSetBits(long n) {
    if (n < 0) {
      throw new IllegalArgumentException("Input must be non-negative.");
    }

    int count = 0;
    while (n != 0) {
      n = n & (n - 1);
      count++;
    }
    return count;
  }
}
