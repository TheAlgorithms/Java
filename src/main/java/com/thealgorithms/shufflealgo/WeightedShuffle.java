package com.thealgorithms.shufflealgo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public
final class WeightedShuffle {

    private
    WeightedShuffle() {
        // Prevent instantiation
    }

    /**
     * Shuffles elements based on their weights. Higher weight elements are more
     * likely to appear earlier.
     *
     * @param array   the input array to shuffle
     * @param weights the weights for each corresponding element in the array
     */
    public
    static void weightedShuffle(int[] array, int[] weights) {
        // Edge case: Check if weights match the array size
        if (array == null || weights == null || array.length != weights.length) {
            return;
        }

        Integer[] indices = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            indices[i] = i;
        }

        Random random = new Random();

        // Sort indices by weights in descending order, prioritizing higher weights
        Arrays.sort(indices, Comparator.comparingInt((Integer i)->- weights[i])
                .thenComparingInt(i->random.nextInt()));

        int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[indices[i]];
        }

        System.arraycopy(result, 0, array, 0, array.length);
    }

    public
    static void main(String[] args) {
        int[] array = {10, 20, 30};
        int[] weights = {1, 3, 2};
        weightedShuffle(array, weights);

        System.out.println("Weighted Shuffled Array:");
        for (int num : array) {
            System.out.print(num + " ");
        }
    }
}

