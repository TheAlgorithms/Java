package com.thealgorithms.maths;

public class GoldenRatioFibonacciRecursion {
    public static void main(String[] args) {
        int n = 10; // Change n to the desired Fibonacci number you want to find

        double goldenRatio = (1 + Math.sqrt(5)) / 2;
        double nthFibonacci = fibonacci(n, goldenRatio);

        System.out.println("The " + n + "th Fibonacci number is: " + (int) nthFibonacci);
    }

    public static double fibonacci(int n, double goldenRatio) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return (Math.pow(goldenRatio, n) - Math.pow(-goldenRatio, -n)) / Math.sqrt(5);
        }
    }
}
