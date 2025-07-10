package com.thealgorithms.maths;

/**
 * Utility class to compute the ceiling of a given number.
 */
public final class Ceil {

    private Ceil() {
    }

    /**
     * Returns the smallest double value that is greater than or equal to the input.
     * Equivalent to mathematical ⌈x⌉ (ceiling function).
     *
     * @param number the number to ceil
     * @return the smallest double greater than or equal to {@code number}
     */
    public static double ceil(double number) {
        long intPart = (long) number;

        if (number > 0 && number != intPart) {
            return intPart + 1.0;
        } else {
            return (double) intPart;
        }
    }
}
