package com.thealgorithms.bitmanipulation;

/**
 * Checks whether a number is even
 * @author Bama Charan Chhandogi (<a href="https://github.com/BamaCharanChhandogi">...</a>)
 */

public final class IsEven {
    private IsEven() {
    }
    public static boolean isEven(int number) {
        return (number & 1) == 0;
    }
}
