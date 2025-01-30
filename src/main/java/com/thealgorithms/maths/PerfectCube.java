package com.thealgorithms.maths;

/**
 * https://en.wikipedia.org/wiki/Cube_(algebra)
 */
public final class PerfectCube {
    private PerfectCube() {
    }

    /**
     * Get cuberoot of a number
     *
     * @param number number to get cube root of
     */
    public static int getCubeRoot(int number) {
        number = Math.abs(number); // converting negative number to positive number
        return (int) Math.pow(number, 1.0 / 3);
    }

    /**
     * Check if a number is perfect cube or not
     *
     * @param number number to check
     * @return {@code true} if {@code number} is perfect cube, otherwise
     * {@code false}
     */
    public static boolean isPerfectCube(int number) {
        int a = getCubeRoot(number);
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
