package Maths;

/** https://en.wikipedia.org/wiki/Pythagorean_triple */
public class PythagoreanTriple {
  public static void main(String[] args) {
    assert isPythagTriple(3, 4, 5);
    assert isPythagTriple(5, 12, 13);
    assert isPythagTriple(6, 8, 10);
    assert !isPythagTriple(10, 20, 30);
    assert !isPythagTriple(6, 8, 100);
    assert !isPythagTriple(-1, -1, 1);
  }

  /**
   * Check if a,b,c are a Pythagorean Triple
   *
   * @param a x/y component length of a right triangle
   * @param b y/x component length of a right triangle
   * @param c hypotenuse length of a right triangle
   * @return boolean <tt>true</tt> if a, b, c satisfy the Pythagorean theorem, otherwise
   *     <tt>false</tt>
   */
  public static boolean isPythagTriple(int a, int b, int c) {
    if (a <= 0 || b <= 0 || c <= 0) {
      return false;
    } else {
      return (a * a) + (b * b) == (c * c);
    }
  }
}
