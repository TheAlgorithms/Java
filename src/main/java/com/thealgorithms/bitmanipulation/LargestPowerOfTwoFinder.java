package com.thealgorithms.bitmanipulation;

/**
 * Find the largest power of 2 less than or equal to a given number.
 */

public class LargestPowerOfTwoFinder {

    public static int largestPowOfTwoLeNum(int number) {
        if (number <= 0) {
            return 0;
        }
        int res = 1;
        while ((res << 1) <= number) {
            res <<= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        // Testing the function with some sample inputs
        System.out.println(largestPowOfTwoLeNum(0)); // 0
        System.out.println(largestPowOfTwoLeNum(1)); // 1
        System.out.println(largestPowOfTwoLeNum(-1)); // 0
        System.out.println(largestPowOfTwoLeNum(3)); // 2
        System.out.println(largestPowOfTwoLeNum(15)); // 8
        System.out.println(largestPowOfTwoLeNum(99)); // 64
        System.out.println(largestPowOfTwoLeNum(178)); // 128
        System.out.println(largestPowOfTwoLeNum(999999)); // 524288
    }
}
