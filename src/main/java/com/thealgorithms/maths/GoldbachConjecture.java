package com.thealgorithms.maths;

import static com.thealgorithms.maths.PrimeCheck.isPrime;
import static java.lang.String.format;

import java.util.Scanner;

/**
 * This is a representation of the unsolved problem of Goldbach's Projection, according to which every
 * even natural number greater than 2 can be written as the sum of 2 prime numbers
 * More info: https://en.wikipedia.org/wiki/Goldbach%27s_conjecture
 * @author Vasilis Sarantidis (https://github.com/BILLSARAN)
 */

public final class GoldbachConjecture {
    private GoldbachConjecture() {
    }

    public static String getPrimeSum(int number){
        String s1;
        if (number % 2 == 0 && number > 2) {
            for (int i = 0; i <= number / 2; i++) {
                if (isPrime(i) && isPrime(number - i)) {
                    s1 = format("%d + %d = %d", i, number - i, number);
                    return s1;
                }
            }
        }
        return "Wrong Input";
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a number");
        int n = scanner.nextInt();
        String s = getPrimeSum(n);
        System.out.println(s);
    }
}
