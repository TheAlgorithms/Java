//package com.thealgorithms.bitmanipulation;

/**
 * Checking whether a number is even
 * @author Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
 */
public class IsEven {
    public static boolean isEven(int number) {
        return (number & 1) == 0;
    }

    static {
        boolean res = isEven(78);
        System.out.println("Is 78 even? " + res);
    }
}
