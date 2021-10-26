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
  public static int binPow(int a, int p) {
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

  /**
   * Function for testing binary exponentiation
   *
   * @param a the base
   * @param p the exponent
   */
  public static void test(int a, int p) {
    int res = binPow(a, p);
    assert res == (int) Math.pow(a, p) : "Incorrect Implementation";
    System.out.println(a + "^" + p + ": " + res);
  }

  /**
   * Main Function to call tests
   *
   * @param args System Line Arguments
   */
  public static void main(String[] args) {
    // prints 2^15: 32768
    test(2, 15);

    // prints 3^9: 19683
    test(3, 9);
  }
}
