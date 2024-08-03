package com.thealgorithms.maths;

/**
 * Utility class for calculating the average of a list of numbers.
 */
public final class Average {

    // Private constructor to prevent instantiation
    private Average() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * Calculates the average of a double array.
     *
     * @param numbers the array of numbers
     * @return the mean of the given numbers
     * @throws IllegalArgumentException if the input array is null or empty
     */
    public static double average(double[] numbers) {
        validateInput(numbers);
        double sum = 0;
        for (double number : numbers) {
            sum += number;
        }
        return sum / numbers.length;
    }

    /**
     * Calculates the average of an int array.
     *
     * @param numbers the array of numbers, ensuring the sum does not exceed long value limits
     * @return the average value
     * @throws IllegalArgumentException if the input array is null or empty
     */
    public static int average(int[] numbers) {
        validateInput(numbers);
        long sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return (int) (sum / numbers.length);
    }

    /**
     * Validates the input array.
     *
     * @param numbers the array of numbers
     * @throws IllegalArgumentException if the input array is null or empty
     */
    private static void validateInput(Object numbers) {
        if (numbers == null || ((Object[]) numbers).length == 0) {
            throw new IllegalArgumentException("Numbers array cannot be null or empty");
        }
    }
}
