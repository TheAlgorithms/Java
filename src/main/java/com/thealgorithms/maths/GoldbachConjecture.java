package com.thealgorithms.maths;

import static com.thealgorithms.maths.Prime.PrimeCheck.isPrime;

/**
 * This is a representation of the unsolved problem of Goldbach's Projection, according to which every
 * even natural number greater than 2 can be written as the sum of 2 prime numbers
 * More info: https://en.wikipedia.org/wiki/Goldbach%27s_conjecture
 * @author Vasilis Sarantidis (https://github.com/BILLSARAN)
 */

public final class GoldbachConjecture {
    private GoldbachConjecture() {
    }
    public record Result(int number1, int number2) {
    }

    public static Result getPrimeSum(int number) {
        if (number <= 2 || number % 2 != 0) {
            throw new IllegalArgumentException("Number must be even and greater than 2.");
        }

        for (int i = 0; i <= number / 2; i++) {
            if (isPrime(i) && isPrime(number - i)) {
                return new Result(i, number - i);
            }
        }
        throw new IllegalStateException("No valid prime sum found."); // Should not occur
    }
}
