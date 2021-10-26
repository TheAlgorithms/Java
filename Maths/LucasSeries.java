package Maths;

/** https://en.wikipedia.org/wiki/Lucas_number */
public class LucasSeries {
  public static void main(String[] args) {
    assert lucasSeries(1) == 2 && lucasSeriesIteration(1) == 2;
    assert lucasSeries(2) == 1 && lucasSeriesIteration(2) == 1;
    assert lucasSeries(3) == 3 && lucasSeriesIteration(3) == 3;
    assert lucasSeries(4) == 4 && lucasSeriesIteration(4) == 4;
    assert lucasSeries(5) == 7 && lucasSeriesIteration(5) == 7;
    assert lucasSeries(6) == 11 && lucasSeriesIteration(6) == 11;
    assert lucasSeries(11) == 123 && lucasSeriesIteration(11) == 123;
  }

  /**
   * Calculate nth number of lucas series(2, 1, 3, 4, 7, 11, 18, 29, 47, 76, 123, ....) using
   * recursion
   *
   * @param n nth
   * @return nth number of lucas series
   */
  public static int lucasSeries(int n) {
    return n == 1 ? 2 : n == 2 ? 1 : lucasSeries(n - 1) + lucasSeries(n - 2);
  }

  /**
   * Calculate nth number of lucas series(2, 1, 3, 4, 7, 11, 18, 29, 47, 76, 123, ....) using
   * iteration
   *
   * @param n nth
   * @return nth number of lucas series
   */
  public static int lucasSeriesIteration(int n) {
    int previous = 2;
    int current = 1;
    for (int i = 1; i < n; i++) {
      int next = previous + current;
      previous = current;
      current = next;
    }
    return previous;
  }
}
