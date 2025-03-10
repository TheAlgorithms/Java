package com.thealgorithms.maths;

import java.util.LinkedHashSet;
import java.util.Set;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Amicable numbers are two different natural numbers that the sum of the
 * proper divisors of each is equal to the other number.
 * (A proper divisor of a number is a positive factor of that number other than the number itself.
 * For example, the proper divisors of 6 are 1, 2, and 3.)
 * A pair of amicable numbers constitutes an aliquot sequence of period 2.
 * It is unknown if there are infinitely many pairs of amicable numbers.
 *
 * <p>
 * link: https://en.wikipedia.org/wiki/Amicable_numbers
 * <p>
 * Simple Example: (220, 284)
 * 220 is divisible by {1,2,4,5,10,11,20,22,44,55,110} <-SUM = 284
 * 284 is divisible by {1,2,4,71,142} <-SUM = 220.
 */
public final class AmicableNumber {
    private AmicableNumber() {
    }
    /**
     * Finds all the amicable numbers in a given range.
     *
     * @param from range start value
     * @param to   range end value (inclusive)
     * @return list with amicable numbers found in given range.
     */
    public static Set<Pair<Integer, Integer>> findAllInRange(int from, int to) {
        if (from <= 0 || to <= 0 || to < from) {
            throw new IllegalArgumentException("Given range of values is invalid!");
        }

        Set<Pair<Integer, Integer>> result = new LinkedHashSet<>();

        for (int i = from; i < to; i++) {
            for (int j = i + 1; j <= to; j++) {
                if (isAmicableNumber(i, j)) {
                    result.add(Pair.of(i, j));
                }
            }
        }
        return result;
    }

    /**
     * Checks whether 2 numbers are AmicableNumbers or not.
     */
    public static boolean isAmicableNumber(int a, int b) {
        if (a <= 0 || b <= 0) {
            throw new IllegalArgumentException("Input numbers must be natural!");
        }
        return sumOfDividers(a, a) == b && sumOfDividers(b, b) == a;
    }

    /**
     * Recursively calculates the sum of all dividers for a given number excluding the divider itself.
     */
    private static int sumOfDividers(int number, int divisor) {
        if (divisor == 1) {
            return 0;
        } else if (number % --divisor == 0) {
            return sumOfDividers(number, divisor) + divisor;
        } else {
            return sumOfDividers(number, divisor);
        }
    }
}
