package com.thealgorithms.divideandconquer;

public class BinaryExponentiation {

    public static void main(String args[]) {
        System.out.println(calculatePower(2, 30));
    }

    // Function to calculate x^y
    // Time Complexity: O(logn)
    public static long calculatePower(long x, long y) {
        if (y == 0) {
            return 1;
        }
        long val = calculatePower(x, y / 2);
        val *= val;
        if (y % 2 == 1) {
            val *= x;
        }
        return val;
    }
}
