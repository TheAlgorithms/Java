package com.thealgorithms.maths;

/**
 * A utility class for computing the average of numeric arrays.
 *
 * <p>This class provides static methods to calculate the arithmetic mean
 * of arrays of both {@code double} and {@code int} values. It also offers
 * a Stream-based alternative for modern, declarative usage.
 *
 * <p>All methods guard against {@code null} or empty inputs.
 */
public final class Average {

    // Prevent instantiation of this utility class
    private Average() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }

    /**
     * Computes the arithmetic mean of a {@code double} array.
     *
     * <p>The average is calculated as the sum of all elements divided
     * by the number of elements: {@code avg = Σ(numbers[i]) / n}.
     *
     * @param numbers a non-null, non-empty array of {@code double} values
     * @return the arithmetic mean of the given numbers
     * @throws IllegalArgumentException if {@code numbers} is {@code null} or empty
     */
    public static double average(double[] numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("Numbers array cannot be empty or null");
        }
        double sum = 0;
        for (double number : numbers) {
            sum += number;
        }
        return sum / numbers.length;
    }

    /**
     * Computes the arithmetic mean of an {@code int} array.
     *
     * <p>The sum is accumulated in a {@code long} to prevent integer overflow
     * when processing large arrays or large values.
     *
     * @param numbers a non-null, non-empty array of {@code int} values
     * @return the arithmetic mean as a {@code long} (truncated toward zero)
     * @throws IllegalArgumentException if {@code numbers} is {@code null} or empty
     */
    public static long average(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("Numbers array cannot be empty or null");
        }
        long sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum / numbers.length;
    }
}
