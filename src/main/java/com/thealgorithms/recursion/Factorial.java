package com.thealgorithms.recursion;

import java.util.Scanner;

/**
 * This class provides a recursive implementation of the factorial function.
 * The factorial of a number n is defined as n! = n × (n-1) × (n-2) × ... × 1
 * with the base case 0! = 1.
 */
public class Factorial {

    /**
     * Recursive method to calculate factorial.
     *
     * @param n the number to find factorial of
     * @return factorial of n
     * @throws IllegalArgumentException if n is negative
     */
    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Number must be non-negative");
        }
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    // Main method with user input
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number to find its factorial: ");

        int num = scanner.nextInt();
        scanner.close();

        try {
            long result = factorial(num);
            System.out.println("Factorial of " + num + " is: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
