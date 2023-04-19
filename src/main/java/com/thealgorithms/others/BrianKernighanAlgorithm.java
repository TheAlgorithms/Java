package com.thealgorithms.others;

import java.util.Scanner;

/**
 * @author Nishita Aggarwal
 * <p>
 * Brian Kernighan’s Algorithm
 * <p>
 * algorithm to count the number of set bits in a given number
 * <p>
 * Subtraction of 1 from a number toggles all the bits (from right to left) till
 * the rightmost set bit(including the rightmost set bit). So if we subtract a
 * number by 1 and do bitwise & with itself i.e. (n & (n-1)), we unset the
 * rightmost set bit.
 * <p>
 * If we do n & (n-1) in a loop and count the no of times loop executes we get
 * the set bit count.
 * <p>
 * <p>
 * Time Complexity: O(logn)
 */
public class BrianKernighanAlgorithm {

    /**
     * @param num: number in which we count the set bits
     * @return int: Number of set bits
     */
    static int countSetBits(int num) {
        int cnt = 0;
        while (num != 0) {
            num = num & (num - 1);
            cnt++;
        }
        return cnt;
    }

    /**
     * @param args : command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int setBitCount = countSetBits(num);
        System.out.println(setBitCount);
        sc.close();
    }
}
