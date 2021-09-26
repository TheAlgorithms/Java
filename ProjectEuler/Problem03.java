package ProjectEuler;

/**
 * The prime factors of 13195 are 5, 7, 13 and 29.
 *
 * <p>What is the largest prime factor of the number 600851475143 ?
 *
 * <p>Link: https://projecteuler.net/problem=3
 */
public class Problem03 {

  /**
   * Checks if a number is prime or not
   *
   * @param n the number
   * @return {@code true} if {@code n} is prime
   */
  public static boolean isPrime(int n) {
    if (n == 2) {
      return true;
    }
    if (n < 2 || n % 2 == 0) {
      return false;
    }
    for (int i = 3, limit = (int) Math.sqrt(n); i <= limit; i += 2) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }

  /**
   * Calculate all the prime factors of a number and return the largest
   *
   * @param n integer number
   * @return the maximum prime factor of the given number
   */
  static long maxPrimeFactor(long n) {
    for (int i = 2; i < n / 2; i++) {
      if (isPrime(i))
        while (n % i == 0) {
          n /= i;
        }
    }
    return n;
  }

  public static void main(String[] args) {
    int[][] testNumbers = {
      {87, 29},
      {10, 5},
      {21, 7},
      {657, 73},
      {777, 37}
    };
    for (int[] num : testNumbers) {
      assert Problem03.maxPrimeFactor(num[0]) == num[1];
    }
    assert Problem03.maxPrimeFactor(600851475143L) == 6857;
  }
}
