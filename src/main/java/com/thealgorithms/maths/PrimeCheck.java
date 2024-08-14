package com.thealgorithms.maths;

import java.util.Scanner;

public final class PrimeCheck {
    private PrimeCheck() {
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int n = scanner.nextInt();
        if (isPrime(n)) {
            System.out.println("algo1 verify that " + n + " is a prime number");
        } else {
            System.out.println("algo1 verify that " + n + " is not a prime number");
        }

        if (fermatPrimeChecking(n, 20)) {
            System.out.println("algo2 verify that " + n + " is a prime number");
        } else {
            System.out.println("algo2 verify that " + n + " is not a prime number");
        }

        if (isPrimeNumberOptimised(Long.MAX_VALUE)) {
            System.out.println("algo4 verify that " + Long.MAX_VALUE + " is a prime number");
        } else {
            System.out.println("algo4 verify that " + Long.MAX_VALUE + " is not a prime number");
        }
        scanner.close();
    }

    /**
     * *
     * Checks if a number is prime or not
     *
     * @param n the number
     * @return {@code true} if {@code n} is prime
     */
    public static boolean isPrime(int n) {
        if (n == 2) {
            return true;
        }
        if (n < 2 || n % 2 == 0) {
            return false;
        }
        for (int i = 3, limit = (int) Math.sqrt(n); i <= limit; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * *
     * Checks if a number is prime or not
     *
     * @param n the number
     * @return {@code true} if {@code n} is prime
     */
    public static boolean fermatPrimeChecking(int n, int iteration) {
        long a;
        int up = n - 2;
        int down = 2;
        for (int i = 0; i < iteration; i++) {
            a = (long) Math.floor(Math.random() * (up - down + 1) + down);
            if (modPow(a, n - 1, n) != 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * *
     * @param a basis
     * @param b exponent
     * @param c modulo
     * @return (a^b) mod c
     */
    private static long modPow(long a, long b, long c) {
        long res = 1;
        for (int i = 0; i < b; i++) {
            res *= a;
            res %= c;
        }
        return res % c;
    }

    /**
     * Checks if a given number is prime using an optimized approach.
     * @param number the number to check
     * @return true if the number is prime, false otherwise
     */
    public static boolean isPrimeNumberOptimised(long number) {
        // Numbers less than or equal to 1 are not prime
        if (number <= 1) return false;
        // Numbers 2 and 3 are prime
        if (number <= 3) return true;
        // Eliminate even numbers and multiples of 3
        if (number % 2 == 0 || number % 3 == 0) return false;
        // Check for factors from 5 onwards using the 6k ± 1 optimization
        for (long i = 5; (i * i) <= number; i += 6) {
            // Check if the number is divisible by i or (i + 2)
            if (number % i == 0 || number % (i + 2) == 0) {
                return false;
            }
        }
        // If no factors are found, the number is prime
        return true;
    }
}
