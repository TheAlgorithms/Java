package com.thealgorithms.maths;

/**
 * Utility class to check if three integers form a Pythagorean triple.
 * A Pythagorean triple consists of three positive integers a, b, and c,
 * such that a² + b² = c².
 *
 * Common examples:
 * - (3, 4, 5)
 * - (5, 12, 13)
 *
 * Reference: https://en.wikipedia.org/wiki/Pythagorean_triple
 */
public final class PythagoreanTriple {

    private PythagoreanTriple() {
    }

    /**
     * Checks whether three integers form a Pythagorean triple.
     * The order of parameters does not matter.
     *
     * @param a one side length
     * @param b another side length
     * @param c another side length
     * @return {@code true} if (a, b, c) can form a Pythagorean triple, otherwise {@code false}
     */
    public static boolean isPythagTriple(int a, int b, int c) {
        if (a <= 0 || b <= 0 || c <= 0) {
            return false;
        }

        // Sort the sides so the largest is treated as hypotenuse
        int[] sides = {a, b, c};
        java.util.Arrays.sort(sides);

        int x = sides[0];
        int y = sides[1];
        int hypotenuse = sides[2];

        return x * x + y * y == hypotenuse * hypotenuse;
    }
}
