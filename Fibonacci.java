package com.thealgorithms.dynamicprogramming;

import java.util.*;
public class Fibonacci {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the value of n");
        int n = sc.nextInt();
        //prints the nth fibonacci number
        System.out.println(fib(n));
        sc.close();
    }
    public static int fib(int n) 
    { 
        int n1 = 0, n2 = 1, n3; 
        if (n == 0) 
           return n1; 
        for (int k = 2; k <= n; k++) 
        { 
            n3 = n1 + n2; 
            n1 = n2; 
            n2 = n3; 
        } 
        return n2; 
    } 
}
//this code is submitted by Trisha Moitra
