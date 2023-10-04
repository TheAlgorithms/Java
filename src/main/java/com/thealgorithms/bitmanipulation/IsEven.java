package com.thealgorithms.bitmanipulation;

/**
 * Converts any Octal Number to a Binary Number
 * @author Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
 */

public class IsEven {
    public static boolean isEven(int number) {
        return (number & 1) == 0;
    }
}
