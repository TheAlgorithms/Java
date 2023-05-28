package com.thealgorithms.maths;

import java.util.Arrays;
import java.util.Random;

public class FindMax {

    /**
     * Driver Code
     */
    public static void main(String[] args) {
        Random random = new Random();

        /* random size */
        int size = random.nextInt(100) + 1;
        int[] array = new int[size];

        /* init array with random numbers */
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt() % 100;
        }

        assert Arrays.stream(array).max().getAsInt() == findMax(array);
    }

    /**
     * @brief finds the maximum value stored in the input array
     *
     * @param array the input array
     * @exception IllegalArgumentException input array is empty
     * @return the maximum value stored in the input array
     */
    public static int findMax(int[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("array must be non-empty.");
        }
        int max = Integer.MIN_VALUE;
        for (final var value : array) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }
}
