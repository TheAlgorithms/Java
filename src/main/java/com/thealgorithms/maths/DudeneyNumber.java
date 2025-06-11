package com.thealgorithms.maths;

/**
 * A number is said to be Dudeney if the sum of the digits, is the cube root of the entered number.
 * Example- Let the number be 512, its sum of digits is 5+1+2=8. The cube root of 512 is also 8.
 *          Since, the sum of the digits is equal to the cube root of the entered number;
 *          it is a Dudeney Number.
 */
public final class DudeneyNumber {
    private DudeneyNumber() {
    }

    // returns True if the number is a Dudeney number and False if it is not a Dudeney number.
    public static boolean isDudeney(final int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Input must me positive.");
        }
        // Calculating Cube Root
        final int cubeRoot = (int) Math.round(Math.pow(n, 1.0 / 3.0));
        // If the number is not a perfect cube the method returns false.
        if (cubeRoot * cubeRoot * cubeRoot != n) {
            return false;
        }

        // If the cube root of the number is not equal to the sum of its digits, we return false.
        return cubeRoot == SumOfDigits.sumOfDigits(n);
    }
}
