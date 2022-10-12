package com.thealgorithms.others;

import java.util.Scanner;
/**
 * You can read more about Factorial
 *
 *
 * See https://en.wikipedia.org/wiki/Factorial
 */
public class Factorial {

    public static void main(String[] args) {
        // Get input from the user
        Scanner sc = new Scanner(System.in);
        System.out.println("inupt the number:");
        int num = sc.nextInt();
        long factorial = multiplyNumbers(num);
        System.out.println("Factorial of " + num + " = " + factorial);
    }

    public static long multiplyNumbers(int num) {
        return num >= 1 ? (long) num * multiplyNumbers(num - 1) : 1L;
    }
}