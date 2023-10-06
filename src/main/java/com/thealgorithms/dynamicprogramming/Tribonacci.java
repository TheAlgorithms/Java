package com.thealgorithms.dynamicprogramming;

public class Tribonacci {

    public static int calculateTribonacci(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1 || n == 2) {
            return 1;
        } else {
            int a = 0, b = 0, c = 1;
            for (int i = 3; i <= n; i++) {
                int temp = a + b + c;
                a = b;
                b = c;
                c = temp;
            }
            return c;
        }
    }

    public static void main(String[] args) {
        int n = 5; // Replace this with the desired value of N
        int result = calculateTribonacci(n);
        System.out.println("The " + n + "th Tribonacci number is " + result);
    }
}
