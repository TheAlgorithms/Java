package com.thealgorithms.maths;

/**
 * A positive integer is considered uniform if all
 * of its digits are equal. For example, 222 is uniform,
 * while 223 is not.
 * Given two positive integers a and b, determine the
 * number of uniform integers between a and b.
 */
public final class UniformNumbers {
    // Private constructor to prevent instantiation of the utility class
    private UniformNumbers() {
        // Prevent instantiation
    }
    /**
     * This function will find the number of uniform numbers
     * from 1 to num
     * @param num upper limit to find the uniform numbers
     * @return the count of uniform numbers between 1 and num
     */
    public static int uniformNumbers(int num) {
        String numStr = Integer.toString(num);
        int uniformCount = (numStr.length() - 1) * 9;
        int finalUniform = Integer.parseInt(String.valueOf(numStr.charAt(0)).repeat(numStr.length()));

        if (finalUniform <= num) {
            uniformCount += Integer.parseInt(String.valueOf(numStr.charAt(0)));
        } else {
            uniformCount += Integer.parseInt(String.valueOf(numStr.charAt(0))) - 1;
        }

        return uniformCount;
    }
    /**
     * This function will calculate the number of uniform numbers
     * between a and b
     * @param a lower bound of range
     * @param b upper bound of range
     * @return  the count of uniform numbers between a and b
     */
    public static int countUniformIntegers(int a, int b) {
        if (b > a && b > 0 && a > 0) {
            return uniformNumbers(b) - uniformNumbers(a - 1);
        } else if (b == a) {
            return 1;
        } else {
            return 0;
        }
    }
}
