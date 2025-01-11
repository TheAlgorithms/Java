package com.thealgorithms.maths;

import java.util.Scanner;

import static java.lang.String.format;

/**
 * This is a representation of the unsolved problem of Goldbach's Projection, according to which every
 * even natural number greater than 2 can be written as the sum of 2 prime numbers
 * More info: https://en.wikipedia.org/wiki/Goldbach%27s_conjecture
 * @author Vasilis Sarantidis (https://github.com/BILLSARAN)
 */

public final class GoldbachConjecture {
    private GoldbachConjecture() {
    }

    /**
     * Checks whether a number is prime or not
     * @param n the input number
     * @return true if n is prime, else return false
     */
    private static boolean isPrime(int n) {
        int i;
        if(n <= 1 || (n % 2 == 0 && n != 2)) {
            return false;
        }
        else {
            for(i = 3; i < Math.sqrt(n); i += 2) {
                if(n % i == 0)
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a number");
        int n = scanner.nextInt();
        int flag = 0;

        if(n%2 == 0 && n>2) {
            for(int i = 0; i <= n/2 && flag == 0; i++)
                if(isPrime(i))
                    if(isPrime(n - i))
                    {
                        System.out.println(format("%d + %d = %d", i, n - i, n));
                        flag = 1;
                    }
        }
        else
            System.out.println("Wrong Input");
    }

}
