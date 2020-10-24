package ProjectEuler;

/**
 * A palindromic number reads the same both ways. The largest palindrome made from the product of
 * two 2-digit numbers is 9009 = 91 × 99.
 *
 * <p>Find the largest palindrome made from the product of two 3-digit numbers.
 *
 * <p>link: https://projecteuler.net/problem=4
 */
public class Problem04 {
  public static void main(String[] args) {

    assert solution1(10000) == -1;
    assert solution1(20000) == 19591; /* 19591 == 143*137 */
    assert solution1(30000) == 29992; /* 29992 == 184*163 */
    assert solution1(40000) == 39893; /* 39893 == 287*139 */
    assert solution1(50000) == 49894; /* 49894 == 494*101 */
    assert solution1(60000) == 59995; /* 59995 == 355*169 */
    assert solution1(70000) == 69996; /* 69996 == 614*114 */
    assert solution1(80000) == 79897; /* 79897 == 733*109 */
    assert solution1(90000) == 89798; /* 89798 == 761*118 */
    assert solution1(100000) == 99999; /* 100000 == 813*123 */
  }

  private static int solution1(int n) {
    for (int i = n - 1; i >= 10000; --i) {
      String strNumber = String.valueOf(i);

      /* Test if strNumber is palindrome */
      if (new StringBuilder(strNumber).reverse().toString().equals(strNumber)) {
        for (int divisor = 999; divisor >= 100; --divisor) {
          if (i % divisor == 0 && String.valueOf(i / divisor).length() == 3) {
            return i;
          }
        }
      }
    }
    return -1; /* not found */
  }
}
