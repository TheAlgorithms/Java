package com.thealgorithms.maths;

/**
 * https://en.wikipedia.org/wiki/Cube_(algebra)
 */
public class PerfectCube {

    public static void main(String[] args) {
        assert isPerfectCube(-1);
        assert isPerfectCube(0);
        assert isPerfectCube(1);
        assert !isPerfectCube(4);
        assert isPerfectCube(8);
        assert isPerfectCube(27);
        
        assert isPerfectCubeMathCbrt(-1);
        assert isPerfectCubeMathCbrt(0);
        assert isPerfectCubeMathCbrt(1);
        assert !isPerfectCubeMathCbrt(4);
        assert isPerfectCubeMathCbrt(8);
        assert isPerfectCubeMathCbrt(27);
    }

    /**
     * Check if a number is perfect cube or not
     *
     * @param number number to check
     * @return {@code true} if {@code number} is perfect cube, otherwise
     * {@code false}
     */
    public static boolean isPerfectCube(int number) {
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
