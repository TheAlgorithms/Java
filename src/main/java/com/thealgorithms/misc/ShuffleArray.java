package com.thealgorithms.misc;

import java.util.Random;

/**
 * The Fisher-Yates (Knuth) Shuffle algorithm randomly permutes an array's
 * elements, ensuring each permutation is equally likely.
 *
 * <p>
 * Worst-case performance O(n)
 * Best-case performance O(n)
 * Average performance O(n)
 * Worst-case space complexity O(1)
 *
 * This class provides a static method to shuffle an array in place.
 *
 * @author Rashi Dashore (https://github.com/rashi07dashore)
 */
public final class ShuffleArray {

    private ShuffleArray() {
    }

    /**
     * Shuffles the provided array in-place using the Fisher–Yates algorithm.
     *
     * @param arr the array to shuffle; must not be {@code null}
     * @throws IllegalArgumentException if the input array is {@code null}
     */
    public static void shuffle(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array must not be null");
        }

        Random random = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            swap(arr, i, j);
        }
    }

    /**
     * Swaps two elements in an array.
     *
     * @param arr the array
     * @param i   index of first element
     * @param j   index of second element
     */
    private static void swap(int[] arr, int i, int j) {
        if (i != j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
