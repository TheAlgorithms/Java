package com.thealgorithms.others;
/*
This program uses recursion to calculate the nth fibonacci number
and also memoisation for effective use of memory
*/
import java.util.Scanner;
public class MyClass {
    static long[] memo; // Array to store already calculated values
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the term");
        int n = sc.nextInt();

                    memo
            = new long[n];
        for (int i = 0; i < n; i++) {
            memo[i] = -1; // initislise all values to -1 as it is not a fibonacci number
        }
        System.out.println(fib(n));
    }

    public static long fib(int n) {
        if (n < 2) return 1;
        if (memo[n - 1] != -1)
            return memo[n - 1]; // if value is already calculated return it
        else {
            memo[n - 1] = fib(n - 1) + fib(n - 2);
            return memo[n - 1];
        }
    }
}
