package Maths;

/**
 * <p>
 * In mathematics, a geometric progression (GP) or geometric sequence is a sequence of numbers such that the
 * ratio between the consecutive terms is constant. Ratio here means the succeeding number divided by the preceeding.
 * For instance, the sequence 2,4,8,16,32 . . . is a geometric progression with common ratio 2.
 * <p>
 * Wikipedia: https://en.wikipedia.org/wiki/Geometric_progression
 */
public class SumOfGeometricProgression {
    public static void main(String[] args) {
        /* 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024 */
        assert Double.compare(2046.0, sumOfSeries(2, 2, 10)) == 0;

        /* 1, 10, 100, 1000, 10000 */
        assert Double.compare(11111.0, sumOfSeries(1, 2, 10)) == 0;

        /* 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 */
        assert Double.compare(0.0, sumOfSeries(0, 2, 10)) == 0;

        /* 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 */
        assert Double.compare(1.0, sumOfSeries(1, 0, 10)) == 0;

        assert Double.compare(-0.0, sumOfSeries(1, 2, 0)) == 0;

        assert Double.compare(-1023.0, sumOfSeries(-1, 2, 10)) == 0;

        assert Double.compare(-341.0, sumOfSeries(1, -2, 10)) == 0;

        assert Double.compare(-0.9990234375, sumOfSeries(1, 2, -10)) == 0;
    }
    /**
     * Calculate sum of geometric series
     *
     * @param firstTerm  the initial term of a geometric series
     * @param commonRatio the common ratio of a geometric series
     * @param numOfTerms the total terms of an geometric series
     * @return sum of given geometric series
     */
    private static double sumOfSeries(double firstTerm, double commonRatio, double numOfTerms){
        if (commonRatio==1) return firstTerm * numOfTerms;
        return (firstTerm*(1-Math.pow(commonRatio, numOfTerms)))/(1-commonRatio);
    }
}
