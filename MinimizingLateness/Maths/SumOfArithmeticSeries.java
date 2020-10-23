package Maths;

/**
 * <p>
 * In mathematics, an arithmetic progression (AP) or arithmetic sequence is a sequence of numbers such that the
 * difference between the consecutive terms is constant. Difference here means the second minus the first.
 * For instance, the sequence 5, 7, 9, 11, 13, 15, . . . is an arithmetic progression with common difference of 2.
 * <p>
 * Wikipedia: https://en.wikipedia.org/wiki/Arithmetic_progression
 */
public class SumOfArithmeticSeries {
    public static void main(String[] args) {

        /* 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 + 10 */
        assert Double.compare(55.0, sumOfSeries(1, 1, 10)) == 0;

        /* 1 + 3 + 5 + 7 + 9 + 11 + 13 + 15 + 17 + 19 */
        assert Double.compare(100.0, sumOfSeries(1, 2, 10)) == 0;

        /* 1 + 11 + 21 + 31 + 41 + 51 + 61 + 71 + 81 + 91 */
        assert Double.compare(460.0, sumOfSeries(1, 10, 10)) == 0;

        /* 0.1 + 0.2 + 0.3 + 0.4 + 0.5 + 0.6 + 0.7 + 0.8 + 0.9 + 1.0 */
        assert Double.compare(5.5, sumOfSeries(0.1, 0.1, 10)) == 0;

        assert Double.compare(49600.0, sumOfSeries(1, 10, 100)) == 0;
    }

    /**
     * Calculate sum of arithmetic series
     *
     * @param firstTerm  the initial term of an arithmetic series
     * @param commonDiff the common difference of an arithmetic series
     * @param numOfTerms the total terms of an arithmetic series
     * @return sum of given arithmetic series
     */
    private static double sumOfSeries(double firstTerm, double commonDiff, int numOfTerms) {
        return numOfTerms / 2.0 * (2 * firstTerm + (numOfTerms - 1) * commonDiff);
    }
}