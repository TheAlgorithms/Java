/* This program calculates and returns the nth Tribonacci number in a tribonacci sequence,
a tribonacci sequence can be obtained by summing the last three element of the sequence .
With 0, 0, 1 as its zeroeth, first, second elements respectively. */

package com.thealgorithms.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Tribonacci {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(tribonacci(n)); // Prints the nth tribonacci number
    }

    static int tribonacci(int n) {
        return tribonacci(n, new HashMap<>()); // calls the overloaded tribonacci function with a new HashMap reference
    }

    // OverLoading the tribonacci function to include memoization technique

    static int tribonacci(int n, HashMap<Integer, Integer> memo) {    
        if (n == 0 || n == 1) return 0; // base conditions for the
        if (n == 2) return 1; // recursive call      
        if (memo.containsKey(n)) { // Checks whether the ans for the current value of n is present or not in the HashMap
            return memo.get(n);
        }
        int result = tribonacci(n - 1, memo) + tribonacci(n - 2, memo) + tribonacci(n - 3, memo);
        memo.put(n, result); // makes sure the result gets stored in the HashMap
        return result;        
    }    
}
