package com.thealgorithms.maths;

/**
 * Wikipedia for HeronsFormula => https://en.wikipedia.org/wiki/Heron%27s_formula
 * Find the area of a triangle using only side lengths
 */

public final class HeronsFormula {

    /*
     * A function to get the Area of a Triangle using Heron's Formula
     * @param s1,s2,s3 => the three sides of the Triangle
     * @return area using the formula (√(s(s – s1)(s – s2)(s – s3)))
     * here s is called semi-perimeter and it is the half of the perimeter (i.e; s = (s1+s2+s3)/2)
     * @author satyabarghav
     */
    private HeronsFormula() {
    }

    private static boolean areAllSidesPositive(final double a, final double b, final double c) {
        return a > 0 && b > 0 && c > 0;
    }

    private static boolean canFormTriangle(final double a, final double b, final double c) {
        return a + b > c && b + c > a && c + a > b;
    }

    public static double herons(final double a, final double b, final double c) {
        if (!areAllSidesPositive(a, b, c) || !canFormTriangle(a, b, c)) {
            throw new IllegalArgumentException("Triangle can't be formed with the given side lengths");
        }
        final double s = (a + b + c) / 2.0;
        return Math.sqrt((s) * (s - a) * (s - b) * (s - c));
    }
}
