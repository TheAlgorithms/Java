package com.thealgorithms.dynamicprogramming;

/**
 * This file contains an implementation of finding the nth CATALAN NUMBER using
 * dynamic programming Wikipedia: https://en.wikipedia.org/wiki/Catalan_number
 *
 * Time Complexity: O(n^2) Space Complexity: O(n)
 *
 * @author AMRITESH ANAND (https://github.com/amritesh19)
 */
import java.util.Scanner;

public class CatalanNumber {

    /**
     * This method finds the nth Catalan number
     *
     * @param n input n which determines the nth Catalan number n should be less
     * than equal to 50 as 50th Catalan number is 6,533,841,209,031,609,592 for
     * n > 50, BigInteger class should be used instead long
     *
     * @return catalanArray[n] the nth Catalan number
     */
    static long findNthCatalan(int n) {

        // Array to store the results of subproblems i.e Catalan numbers from [1...n-1]
        long catalanArray[] = new long[n + 1];

        // Initialising C₀ = 1 and C₁ = 1 
        catalanArray[0] = 1;
        catalanArray[1] = 1;

        /**
         * The Catalan numbers satisfy the recurrence relation C₀=1 and Cn = Σ
         * (Ci * Cn-1-i), i = 0 to n-1 , n > 0
         */
        for (int i = 2; i <= n; i++) {
            catalanArray[i] = 0;
            for (int j = 0; j < i; j++) {
                catalanArray[i] += catalanArray[j] * catalanArray[i - j - 1];
            }
        }

        return catalanArray[n];
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number n to find nth Catalan number (n <= 50)");
        int n = sc.nextInt();
        System.out.println(n + "th Catalan number is " + findNthCatalan(n));

        sc.close();
    }
}
