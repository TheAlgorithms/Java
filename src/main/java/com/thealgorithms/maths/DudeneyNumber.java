/**
 * A number is said to be Dudeney if the sum of the digits, is the cube root of the entered number.
 * Example- Let the number be 512, its sum of digits is 5+1+2=8. The cube root of 512 is also 8.
 *          Since, the sum of the digits is equal to the cube root of the entered number;
 *          it is a Dudeney Number.
 */
package com.thealgorithms.maths;

import static com.thealgorithms.maths.PerfectCube.getCubeRoot;
import static com.thealgorithms.maths.utils.MathsUtil.checkInputIsPositive;

public final class DudeneyNumber {
    private DudeneyNumber() {
    }

    // returns True if the number is a Dudeney number and False if it is not a Dudeney number.
    public static boolean isDudeney(final int n) {
        checkInputIsPositive(n, "Input number should be positive.");
        // Calculating Cube Root
        final int cubeRoot = getCubeRoot(n);
        // If the number is not a perfect cube the method returns false.
        if (cubeRoot * cubeRoot * cubeRoot != n) {
            return false;
        }

        // If the cube root of the number is not equal to the sum of its digits, we return false.
        return cubeRoot == SumOfDigits.sumOfDigits(n);
    }
}
