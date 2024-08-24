package com.thealgorithms.others;

public final class RootPrecision {
    private RootPrecision() {
    }

    /**
     * Calculates the square root of a number with the specified precision.
     *
     * @param number    The number to calculate the square root of.
     * @param precision The number of decimal places of precision.
     * @return The square root of the number with the specified precision.
     */
    public static double calculateSquareRoot(int number, int precision) {
        double rawRoot = Math.sqrt(number); // Calculate the square root using Math.sqrt
        double scalingFactor = Math.pow(10, precision); // Calculate the scaling factor for precision

        // Scale the square root, truncate the extra decimals, and rescale back
        return Math.round(rawRoot * scalingFactor) / scalingFactor;
    }
}
