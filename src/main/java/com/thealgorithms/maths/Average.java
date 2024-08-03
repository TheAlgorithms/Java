package com.thealgorithms.maths;

/**
 * Utility class for calculating the average of numeric arrays.
 */
public final class Average {

    // Private constructor to prevent instantiation
    private Average() {
    }

    /**
     * Calculates the average of a double array.
     *
     * @param numbers an array of doubles
     * @return the mean of the given numbers
     * @throws IllegalArgumentException if the array is null or empty
     */
    public static double calculateAverage(double[] numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty.");
        }
        double sum = 0;
        for (double number : numbers) {
            sum += number;
        }
        return sum / numbers.length;
    }

    /**
     * Calculates the average of an int array.
     *
     * @param numbers an array of integers
     * @return the mean of the given numbers
     * @throws IllegalArgumentException if the array is null or empty
     */
    public static int calculateAverage(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty.");
        }
        long sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return (int) (sum / numbers.length);
    }
}
