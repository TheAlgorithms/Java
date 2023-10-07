package com.thealgorithms.bitmanipulation;

/**
 * Swap Two numbers without the help of extra variable.
 * @author Abhishek Shukla (https://github.com/Shuklaaa)
 */

public class SwapTwoNumbers {
    public static void swapNumbers(int a, int b) {
        // Swap two numbers using XOR
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
    }
}
