package com.thealgorithms.maths;

import java.util.Random;

public class Rand {

    /**
     * Generates a random integer between two given integers (inclusive).
     *
     * @param min The lower bound of the range (inclusive).
     * @param max The upper bound of the range (inclusive).
     * @return A random integer between min and max.
     * @throws IllegalArgumentException If min is greater than max.
     */
    public static int rand(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Min cannot be greater than max");
        }
        Random random = new Random();
        // nextInt(bound) generates a random number between 0 (inclusive) and the specified bound (exclusive).
        // Adding min to the result ensures the result is within the desired range.
        return random.nextInt(max - min + 1) + min;
    }
}
