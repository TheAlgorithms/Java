package com.thealgorithms.bitmanipulation;

/**
 * Find Non Repeating Number
 * @author Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
 */

public class NonRepeatingNumberFinder {

    public static int findNonRepeatingNumber(int[] arr) {
        int result = 0;
        for (int num : arr) {
            result ^= num;
        }
        return result;
    }
}
