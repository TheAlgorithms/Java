package com.thealgorithms.others;

import java.util.Scanner;

/**
 * Fibonacci sequence, and characterized by the fact that every number after the
 * first two is the sum of the two preceding ones.
 *
 * <p>
 * Fibonacci sequence: 0, 1, 1, 2, 3, 5, 8, 13, 21,...
 *
 * <p>
 * Source for the explanation: https://en.wikipedia.org/wiki/Fibonacci_number
 *
 * Problem Statement: print all Fibonacci numbers that are smaller than your
 * given input N
 */
public class FibbonaciSeries {

    public static void main(String[] args) {
        // Get input from the user
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int first = 0, second = 1;
        scan.close();
        while (first <= n) {
            // print first fibo 0 then add second fibo into it while updating second as well
            System.out.println(first);
            int next = first + second;
            first = second;
            second = next;
        }
    }
}
