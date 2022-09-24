package com.thealgorithms.maths;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://en.wikipedia.org/wiki/Collatz_conjecture">...</a>
 */
public class CollatzConjecture {

    /**
     * Calculate the next number of the sequence.
     *
     * @param n current number of the sequence
     * @return next number of the sequence
     */
    public int nextNumber(final int n) {
        if (n % 2 == 0) {
            return n / 2;
        }
        return 3 * n + 1;
    }

    /**
     * Calculate the Collatz sequence of any natural number.
     *
     * @param firstNumber starting number of the sequence
     * @return sequence of the Collatz Conjecture
     */
    public List<Integer> collatzConjecture(int firstNumber) {
        if (firstNumber < 1) {
            throw new IllegalArgumentException("Must be a natural number");
        }
        ArrayList<Integer> result = new ArrayList<>();
        result.add(firstNumber);
        while (firstNumber != 1) {
            result.add(nextNumber(firstNumber));
            firstNumber = nextNumber(firstNumber);
        }
        return result;
    }
}
