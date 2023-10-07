// Created by Pronay Debnath
// Date:- 1/10/2003
// Updated FibonnaciNumber.java using golden ratio formula
// Explanation:- https://www.quickanddirtytips.com/articles/what-is-the-golden-ratio-and-how-is-it-related-to-the-fibonacci-sequence/

import java.util.*;

public final class FibonacciNumber<T extends Number> {
    private FibonacciNumber() {
    }

    // Function to find the nth Fibonacci number using the golden ratio formula
    public static <T extends Number> int nthFibonacci(T n) {
        // Calculate the square root of 5
        double sqrt5 = Math.sqrt(5);

        // Calculate the golden ratio (phi) and its conjugate (psi)
        double phi = (1 + sqrt5) / 2;
        double psi = (1 - sqrt5) / 2;

        // Convert the input value to a double
        double result = n.doubleValue();

        // Calculate the nth Fibonacci number using the golden ratio formula
        double fibonacci = (1 / sqrt5) * (Math.pow(phi, result) - Math.pow(psi, result));

        // Cast the result to an int and return it
        return (int) fibonacci;
    }

}
