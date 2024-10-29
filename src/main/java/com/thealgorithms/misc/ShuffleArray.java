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
    // Prevent instantiation
    private ShuffleArray() {
    }

    /**
     * This method shuffles an array using the Fisher-Yates algorithm.
     *
     * @param arr is the input array to be shuffled
     */
    public static void shuffle(int[] arr) {
        Random random = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
