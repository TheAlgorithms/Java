package com.thealgorithms.bitmanipulation;

public class PowerOfFour {

    private PowerOfFour() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isPowerOfFour(int n) {
        if (n <= 0) {
            return false;
        } else {
            return (n & (n - 1)) == 0 && (n & 0x55555555) != 0;
        }
    }
}
