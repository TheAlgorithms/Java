package com.thealgorithms.bitmanipulation;

public class IsEven {
    /**
     * Check if a number is odd or even
     *
     * @param input a number
     * @return {@code true} if given number is even, otherwise {@code false}
     */
    public static boolean isEven(int value) {
        return ((value & 1) != 1);
    }
}
