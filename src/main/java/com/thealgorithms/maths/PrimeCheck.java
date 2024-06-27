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
    
    
    // Skip even numbers (except 2) since they are not prime.
    // Skip multiples of 3 after checking 3.
    // Start checking for factors from 5 and use the 6k ± 1 optimization. 
    // This is based on the fact that any prime number greater than 3 can be expressed as 6k ± 1.
    // This function is more efficient, especially for larger numbers, as it reduces the number of iterations by skipping unnecessary checks.
    public static boolean isPrimeNumberOptimised(long number){
        if(number  <= 1) return false;
        if(number  <= 3) return true;
        for(long i = 5; (i * i) <= number; i += 6){
            if(number % i == 0 || number % (i+2) == 0){
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
}
