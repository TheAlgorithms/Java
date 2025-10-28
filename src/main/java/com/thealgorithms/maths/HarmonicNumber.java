package com.thealgorithms.maths;

/**
 * A class for calculating harmonic numbers.
 * The harmonic series is the divergent infinite series: 1 + 1/2 + 1/3 + 1/4 + ...
 */
public final class HarmonicNumber {
    
    private HarmonicNumber() {}
    
    /**
     * Calculates the nth harmonic number.
     * The harmonic series is the sum: 1 + 1/2 + 1/3 + ... + 1/n
     * This series appears in various mathematical and practical applications.
     *
     * For example:
     * H(1) = 1
     * H(2) = 1 + 1/2 = 1.5
     * H(3) = 1 + 1/2 + 1/3 ≈ 1.833
     * H(4) = 1 + 1/2 + 1/3 + 1/4 ≈ 2.083
     *
     * @param numTerms the number of terms to sum (n)
     * @return the nth harmonic number
     * @throws IllegalArgumentException if numTerms is less than 1
     */
    public static double of(int numTerms) {
        if (numTerms < 1) {
            throw new IllegalArgumentException("Number of terms must be positive");
        }
        double harmonicSum = 0.0;
        for (int termIndex = 1; termIndex <= numTerms; termIndex++) {
            harmonicSum += 1.0 / termIndex;
        }
        return harmonicSum;
    }
}
