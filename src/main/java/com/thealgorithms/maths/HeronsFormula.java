package com.thealgorithms.maths;

/**
 * Heron's Formula implementation for calculating the area of a triangle given
 * its three side lengths.
 * <p>
 * Heron's Formula states that the area of a triangle whose sides have lengths
 * a, b, and c is:
 * Area = √(s(s - a)(s - b)(s - c))
 * where s is the semi-perimeter of the triangle: s = (a + b + c) / 2
 * </p>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Heron%27s_formula">Heron's
 *      Formula - Wikipedia</a>
 * @author satyabarghav
 */
public final class HeronsFormula {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private HeronsFormula() {
    }

    /**
     * Checks if all three side lengths are positive.
     *
     * @param a the length of the first side
     * @param b the length of the second side
     * @param c the length of the third side
     * @return true if all sides are positive, false otherwise
     */
    private static boolean areAllSidesPositive(final double a, final double b, final double c) {
        return a > 0 && b > 0 && c > 0;
    }

    /**
     * Checks if the given side lengths satisfy the triangle inequality theorem.
     * <p>
     * The triangle inequality theorem states that the sum of any two sides
     * of a triangle must be greater than the third side.
     * </p>
     *
     * @param a the length of the first side
     * @param b the length of the second side
     * @param c the length of the third side
     * @return true if the sides can form a valid triangle, false otherwise
     */
    private static boolean canFormTriangle(final double a, final double b, final double c) {
        return a + b > c && b + c > a && c + a > b;
    }

    /**
     * Calculates the area of a triangle using Heron's Formula.
     * <p>
     * Given three side lengths a, b, and c, the area is computed as:
     * Area = √(s(s - a)(s - b)(s - c))
     * where s is the semi-perimeter: s = (a + b + c) / 2
     * </p>
     *
     * @param a the length of the first side (must be positive)
     * @param b the length of the second side (must be positive)
     * @param c the length of the third side (must be positive)
     * @return the area of the triangle
     * @throws IllegalArgumentException if any side length is non-positive or if the
     *                                  sides cannot form a valid triangle
     */
    public static double herons(final double a, final double b, final double c) {
        if (!areAllSidesPositive(a, b, c)) {
            throw new IllegalArgumentException("All side lengths must be positive");
        }
        if (!canFormTriangle(a, b, c)) {
            throw new IllegalArgumentException("Triangle cannot be formed with the given side lengths (violates triangle inequality)");
        }
        final double s = (a + b + c) / 2.0;
        return Math.sqrt((s) * (s - a) * (s - b) * (s - c));
    }
}
