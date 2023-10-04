package com.thealgorithms.maths;

/**
 * https://en.wikipedia.org/wiki/Cube_(algebra)
 */
public class PerfectCube {

    /**
     * Check if a number is perfect cube or not
     *
     * @param number number to check
     * @return {@code true} if {@code number} is perfect cube, otherwise
     * {@code false}
     */
    public static boolean isPerfectCube(int number) {
        number = Math.abs(number); // converting negative number to positive number
        int a = (int) Math.pow(number, 1.0 / 3);
        return a * a * a == number;
    }

    /**
     * Check if a number is perfect cube or not by using Math.cbrt function
     *
     * @param number number to check
     * @return {@code true} if {@code number} is perfect cube, otherwise
     * {@code false}
     */
    public static boolean isPerfectCubeMathCbrt(int number) {
        double cubeRoot = Math.cbrt(number);
        return cubeRoot == (int) cubeRoot;
    }
}
