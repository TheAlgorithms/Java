package com.thealgorithms.maths;

/**
 * Calculate average of a list of numbers
 */
public class Average {

    /**
     * Calculate average of a list of numbers
     *
     * @param numbers array to store numbers
     * @return mean of given numbers
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
     * find average value of int array
     *
     * @param numbers the array contains element and the sum does not excess long
     *                value limit
     * @return average value
     */
    public static int average(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("Numbers array cannot be empty or null");
        }
        long sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return (int) (sum / numbers.length);
    }
}
