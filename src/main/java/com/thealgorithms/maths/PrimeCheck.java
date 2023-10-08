package com.thealgorithms.maths;

import java.util.Scanner;

public class PrimeCheck {

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
        int up = n - 2, down = 2;
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
}
