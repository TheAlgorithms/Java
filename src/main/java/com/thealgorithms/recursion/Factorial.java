package com.thealgorithms.recursion;

/*
    The Factorial of a non-negative integer n is the product of all positive integers less than or
   equal to n. It is defined as: n! = n × (n - 1) × (n - 2) × ... × 1 with the base case: 0! = 1

    Example:
        5! = 5 × 4 × 3 × 2 × 1 = 120
*/

public final class Factorial {
  private Factorial() {
    throw new UnsupportedOperationException("Utility class");
  }

  /**
   * Computes the factorial of a non-negative integer using recursion.
   *
   * @param n the number for which factorial is to be calculated
   * @return factorial of n
   * @throws IllegalArgumentException if n is negative
   */

  public static long factorial(int n) {
    if (n < 0) {
      throw new IllegalArgumentException("Factorial is not defined for negative numbers.");
    }
    if (n == 0 || n == 1) {
      return 1;
    }
    return n * factorial(n - 1);
  }
}
