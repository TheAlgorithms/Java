package com.thealgorithms.greedyalgorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to represent a fraction as a sum of unique unit fractions.
 * Example:
 * 2/3 = 1/2 + 1/6
 * 3/10 = 1/4 + 1/20
 *
 * @author Hardvan
 */
public final class EgyptianFraction {
    private EgyptianFraction() {
    }

    /**
     * Calculates the Egyptian Fraction representation of a given fraction.
     *
     * @param numerator   the numerator of the fraction
     * @param denominator the denominator of the fraction
     * @return List of unit fractions represented as strings "1/x"
     */
    public static List<String> getEgyptianFraction(int numerator, int denominator) {
        List<String> result = new ArrayList<>();
        while (numerator != 0) {
            int x = (int) Math.ceil((double) denominator / numerator);
            result.add("1/" + x);
            numerator = numerator * x - denominator;
            denominator = denominator * x;
        }
        return result;
    }
}
