package com.thealgorithms.greedyalgorithms;

import java.util.Arrays;
import java.util.Comparator;

/**
 * The FractionalKnapsack class provides a method to solve the fractional knapsack problem
 * using a greedy algorithm approach. It allows for selecting fractions of items to maximize
 * the total value in a knapsack with a given weight capacity.
 *
 * The problem consists of a set of items, each with a weight and a value, and a knapsack
 * that can carry a maximum weight. The goal is to maximize the value of items in the knapsack,
 * allowing for the inclusion of fractions of items.
 *
 * Problem Link: https://en.wikipedia.org/wiki/Continuous_knapsack_problem
 */
public final class FractionalKnapsack {
    private FractionalKnapsack() {
    }

    /**
     * Computes the maximum value that can be accommodated in a knapsack of a given capacity.
     *
     * @param weight an array of integers representing the weights of the items
     * @param value an array of integers representing the values of the items
     * @param capacity an integer representing the maximum weight capacity of the knapsack
     * @return the maximum value that can be obtained by including the items in the knapsack
     */
    public static int fractionalKnapsack(int[] weight, int[] value, int capacity) {
        double[][] ratio = new double[weight.length][2];

        for (int i = 0; i < weight.length; i++) {
            ratio[i][0] = i;
            ratio[i][1] = value[i] / (double) weight[i];
        }

        Arrays.sort(ratio, Comparator.comparingDouble(o -> o[1]));

        int finalValue = 0;
        double current = capacity;

        for (int i = ratio.length - 1; i >= 0; i--) {
            int index = (int) ratio[i][0];
            if (current >= weight[index]) {
                finalValue += value[index];
                current -= weight[index];
            } else {
                finalValue += (int) (ratio[i][1] * current);
                break;
            }
        }
        return finalValue;
    }
}
