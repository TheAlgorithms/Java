package com.thealgorithms.maths;

import java.util.Arrays;
import java.util.Random;

public class FindMin {

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

        assert Arrays.stream(array).min().getAsInt() == findMin(array);
    }

    /**
     * @brief finds the minimum value stored in the input array
     *
     * @param array the input array
     * @exception IllegalArgumentException input array is empty
     * @return the mimum value stored in the input array
     */
    public static int findMin(int[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("array must be non-empty.");
        }
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = value;
            }
        }
        return min;
    }
}
