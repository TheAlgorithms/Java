package com.thealgorithms.bitmanipulation;

/**
 * Checks whether a number is even
 * @author Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
 */

public class IsEven {
    public static boolean isEven(int number) {
        return (number & 1) == 0;
    }
}
