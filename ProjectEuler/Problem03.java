// Problem Statement
// The prime factors of 13195 are 5, 7, 13 and 29.
// What is the largest prime factor of the number 600851475143 ?

/* 
 * Every integer that is greater than 1 has a unique factorization as a product of prime numbers.
 * In other words, the theorem says that n = p_0 * p_1 * ... * p_{m-1}, where each p_i > 1 is prime but not necessarily unique.
 * Now if we take the number n and repeatedly divide out its smallest factor (which must also be prime), then the last factor that we divide out must be the largest prime factor of n. For reference, 600851475143 = 71 * 839 * 1471 * 6857.
 */
package ProjectEuler;

import java.util.*;

public class Problem03 {

  public static void main(String[] args) {
    System.out.println(run());
  }

  static String run() {
    long n = 600851475143L;
    while (true) {
      long p = smallestFactor(n);
      if (p < n)
        n /= p;
      else
        return Long.toString(n);
    }
  }

  static long smallestFactor(long n) {
    if (n <= 1)
      throw new IllegalArgumentException();
    for (long i = 2, end = (int) Math.sqrt(n); i <= end; i++) {
      if (n % i == 0)
        return i;
    }
    return n; // n itself is prime
  }

}