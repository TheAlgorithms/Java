package com.thealgorithms.bitmanipulation;

/**
 * Algorithm to find the total number of set bits in a number.
 * @author Dhriendra Pratap Singh aliad (hanisntsolo) (https://github.com/hanisntsolo)
 */
public class CountNumberOfSetBits {
    public static int countNumberOfSetBits(int n) {
        if(n == 0) {
            return 0;
        }
        return (n & 1) + countNumberOfSetBits(n >> 1);
    }
}
