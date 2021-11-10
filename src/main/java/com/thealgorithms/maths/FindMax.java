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
     * find max of array
     *
     * @param array the array contains element
     * @return max value of given array
     */
    public static int findMax(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; ++i) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }
}
