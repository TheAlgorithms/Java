package com.thealgorithms.maths;

/**
 * In mathematics, an arithmetic progression (AP) or arithmetic sequence is a
 * sequence of numbers such that the difference between the consecutive terms is
 * constant. Difference here means the second minus the first. For instance, the
 * sequence 5, 7, 9, 11, 13, 15, . . . is an arithmetic progression with common
 * difference of 2.
 *
 * <p>
 * Wikipedia: https://en.wikipedia.org/wiki/Arithmetic_progression
 */
public final class SumOfArithmeticSeries {
    private SumOfArithmeticSeries() {
    }

    /**
     * Calculate sum of arithmetic series
     *
     * @param firstTerm the initial term of an arithmetic series
     * @param commonDiff the common difference of an arithmetic series
     * @param numOfTerms the total terms of an arithmetic series
     * @return sum of given arithmetic series
     */
    public static double sumOfSeries(final double firstTerm, final double commonDiff, final int numOfTerms) {
        if (numOfTerms < 0) {
            throw new IllegalArgumentException("numOfTerms nonnegative.");
        }
        return (numOfTerms / 2.0 * (2 * firstTerm + (numOfTerms - 1) * commonDiff));
    }
}
