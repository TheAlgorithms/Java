package com.thealgorithms.greedyalgorithms;

import java.util.Arrays;

/**
 * The MinimumWaitingTime class provides a method to calculate the minimum
 * waiting time for a list of queries using a greedy algorithm.
 *
 * @author Hardvan
 */
public final class MinimumWaitingTime {
    private MinimumWaitingTime() {
    }

    /**
     * Calculates the minimum waiting time for a list of queries.
     * The function sorts the queries in non-decreasing order and then calculates
     * the waiting time for each query based on its position in the sorted list.
     *
     * @param queries an array of integers representing the query times in picoseconds
     * @return the minimum waiting time in picoseconds
     */
    public static int minimumWaitingTime(int[] queries) {
        int n = queries.length;
        if (n <= 1) {
            return 0;
        }

        Arrays.sort(queries);

        int totalWaitingTime = 0;
        for (int i = 0; i < n; i++) {
            totalWaitingTime += queries[i] * (n - i - 1);
        }
        return totalWaitingTime;
    }
}
