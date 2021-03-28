package Maths;

/**
 * In mathematics, a geometric series is the sum of an infinite number of terms that have a constant ratio between successive terms.
 * In general, a geometric series is written as a + ar + ar**2 + ar**3 + ... , where a is the coefficient of each term and r is the common ratio between adjacent terms.
   n is the number of terms
 * 
 <p>Wikipedia: https://en.wikipedia.org/wiki/Geometric_series
 */
import java.lang.Math;

public class SumOfGeometricSeries {
  public static void main(String[] args) {

    /* a = 3 , r = 2 , n = 5 */
    assert Double.compare(93.0, sumOfGSeries(3, 2, 5)) == 0;

    /* a = 1 , r = 2 , n = 10 */
    assert Double.compare(1023.0, sumOfGSeries(1, 2, 10)) == 0;

    /* a = 5 , r = 3 , n = 5 */
    assert Double.compare(605.0, sumOfGSeries(5, 3, 5)) == 0;

    /* a = 1 , r = 0.5 , n = 10 */
    assert Double.compare(1.99805, sumOfGSeries(1, 0.5, 10)) == 0;

  }

  /**
   * Calculate sum of geometric series
   *
   * @param firstTerm the initial term of a geometric series
   * @param commonRatio the common ratio of a geometric series
   * @param numOfTerms the total terms of a geometric series
   * @return sum of given geometric series
   */
  private static double sumOfGSeries(double firstTerm, double commonRatio, int numOfTerms) {
      return firstTerm * (1 - (Math.pow(commonRatio , numOfTerms) ) ) / ( 1 - commonRatio) ;
  }
}
