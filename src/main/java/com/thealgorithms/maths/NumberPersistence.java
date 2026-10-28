package com.thealgorithms.maths;

/**
 * A utility class for calculating the persistence of a number.
 *
 * <p>This class provides methods to calculate:
 * <ul>
 *   <li>Multiplicative persistence: The number of steps required to reduce a number to a single digit by multiplying its digits.</li>
 *   <li>Additive persistence: The number of steps required to reduce a number to a single digit by summing its digits.</li>
 * </ul>
 *
 * <p>This class is final and cannot be instantiated.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Persistence_of_a_number">Wikipedia: Persistence of a number</a>
 */
public final class NumberPersistence {

    // Private constructor to prevent instantiation
    private NumberPersistence() {
    }

    /**
     * Calculates the multiplicative persistence of a given number.
     *
     * <p>Multiplicative persistence is the number of steps required to reduce a number to a single digit
     * by multiplying its digits repeatedly.
     *
     * @param num the number to calculate persistence for; must be non-negative
     * @return the multiplicative persistence of the number
     * @throws IllegalArgumentException if the input number is negative
     */
    public static int multiplicativePersistence(int num) {
        if (num < 0) {
            throw new IllegalArgumentException("multiplicativePersistence() does not accept negative values");
        }

        int steps = 0;
        while (num >= 10) {
            int product = 1;
            int temp = num;
            while (temp > 0) {
                product *= temp % 10;
                temp /= 10;
            }
            num = product;
            steps++;
        }
        return steps;
    }

    /**
     * Calculates the additive persistence of a given number.
     *
     * <p>Additive persistence is the number of steps required to reduce a number to a single digit
     * by summing its digits repeatedly.
     *
     * @param num the number to calculate persistence for; must be non-negative
     * @return the additive persistence of the number
     * @throws IllegalArgumentException if the input number is negative
     */
    public static int additivePersistence(int num) {
        if (num < 0) {
            throw new IllegalArgumentException("additivePersistence() does not accept negative values");
        }

        int steps = 0;
        while (num >= 10) {
            int sum = 0;
            int temp = num;
            while (temp > 0) {
                sum += temp % 10;
                temp /= 10;
            }
            num = sum;
            steps++;
        }
        return steps;
    }
}
