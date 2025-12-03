package com.thealgorithms.maths;

/**
 * Utility class to compute the floor of a given number.
 */
public final class Floor {
    private Floor() {
    }

    /**
     * Returns the largest double value that is less than or equal to the input.
     * Equivalent to mathematical ⌊x⌋ (floor function).
     *
     * @param number the number to floor
     * @return the largest double less than or equal to {@code number}
     */
    public static double floor(double number) {
        if (Double.isNaN(number) || Double.isInfinite(number) || number == 0.0 || number < Integer.MIN_VALUE || number > Integer.MAX_VALUE) {
            return number;
        }
        
        if (number > 0.0 && number < 1.0) {
            return 0.0;
        }
        
        long intPart = (long) number;
        if (number < 0 && number != intPart) {
            return intPart - 1.0;
        } else {
            return intPart;
        }
    }
}
