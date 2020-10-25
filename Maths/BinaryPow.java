package Maths;

public class BinaryPow {
  /**
   * Calculate a^p using binary exponentiation
   * [Binary-Exponentiation](https://cp-algorithms.com/algebra/binary-exp.html)
   *
   * @param a the base for exponentiation
   * @param p the exponent - must be greater than 0
   * @return a^p
   */
  public static int BinPow(int a, int p) {
    int res = 1;
    while (p > 0) {
      if ((p & 1) == 1) {
        res = res * a;
      }
      a = a * a;
      p >>>= 1;
    }
    return res;
  }

  /** Test Functions */
  public static void main(String[] args) {
    int a = 2;
    int p = 15;
    int res = BinPow(a, p);
    System.out.println(a + "^" + p + ": " + res);
  }
}
