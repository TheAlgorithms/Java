package ProjectEuler;

/**
 * A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
 *
 * <p>a^2 + b^2 = c^2 For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2.
 *
 * <p>There exists exactly one Pythagorean triplet for which a + b + c = 1000. Find the product abc.
 *
 * <p>link: https://projecteuler.net/problem=9
 */
public class Problem09 {
  public static void main(String[] args) {
    assert solution1() == 31875000;
  }

  private static int solution1() {
    for (int i = 0; i <= 300; ++i) {
      for (int j = 0; j <= 400; ++j) {
        int k = 1000 - i - j;
        if (i * i + j * j == k * k) {
          return i * j * k;
        }
      }
    }
    return -1; /* should not happen */
  }
}
