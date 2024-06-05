package com.thealgorithms.greedyalgorithms;

import java.util.Arrays;
import java.util.Comparator;

// Problem Link: https://en.wikipedia.org/wiki/Continuous_knapsack_problem

public final class FractionalKnapsack {
    private FractionalKnapsack() {
    }
    // Function to perform fractional knapsack
    public static int fractionalKnapsack(int[] weight, int[] value, int capacity) {
        // Create a 2D array to store item indices and their value-to-weight ratios.
        double[][] ratio = new double[weight.length][2];

        // Populate the ratio array with item indices and their value-to-weight ratios.
        for (int i = 0; i < weight.length; i++) {
            ratio[i][0] = i; // Assign item index.
            ratio[i][1] = value[i] / (double) weight[i]; // Calculate and assign value-to-weight ratio.
        }

        // Sort items by their value-to-weight ratios in descending order.
        Arrays.sort(ratio, Comparator.comparingDouble(o -> o[1]));

        int finalValue = 0; // Variable to store the final knapsack value.
        double current = capacity; // Variable to track the remaining capacity of the knapsack.

        // Iterate through the sorted items to select items for the knapsack.
        for (int i = ratio.length - 1; i >= 0; i--) {
            int index = (int) ratio[i][0]; // Get the item index.
            if (current >= weight[index]) {
                // If the entire item can fit in the knapsack, add its value.
                finalValue += value[index];
                current -= weight[index];
            } else {
                // If only a fraction of the item can fit, add a proportionate value.
                finalValue += (int) (ratio[i][1] * current);
                break; // Stop adding items to the knapsack since it's full.
            }
        }
        return finalValue;
    }
}
