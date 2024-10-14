package com.thealgorithms.greedyalgorithms;

import java.util.Arrays;

/**
 * Class to solve the Bandwidth Allocation Problem.
 * The goal is to maximize the value gained by allocating bandwidth to users.
 * Example:
 * Bandwidth = 10
 * Users = [3, 5, 7]
 * Values = [10, 20, 30]
 * The maximum value achievable is 40 by allocating 3 units to user 0 and 7 units to user 2.
 *
 * @author Hardvan
 */
public final class BandwidthAllocation {
    private BandwidthAllocation() {
    }

    /**
     * Allocates bandwidth to maximize value.
     * Steps:
     * 1. Calculate the ratio of value/demand for each user.
     * 2. Sort the users in descending order of the ratio.
     * 3. Allocate bandwidth to users in order of the sorted list.
     * 4. If the bandwidth is not enough to allocate the full demand of a user, allocate a fraction of the demand.
     * 5. Return the maximum value achievable.
     *
     * @param bandwidth total available bandwidth to allocate
     * @param users     array of user demands
     * @param values    array of values associated with each user's demand
     * @return the maximum value achievable
     */
    public static int maxValue(int bandwidth, int[] users, int[] values) {
        int n = users.length;
        double[][] ratio = new double[n][2]; // {index, ratio}

        for (int i = 0; i < n; i++) {
            ratio[i][0] = i;
            ratio[i][1] = (double) values[i] / users[i];
        }

        Arrays.sort(ratio, (a, b) -> Double.compare(b[1], a[1]));

        int maxValue = 0;
        for (int i = 0; i < n; i++) {
            int index = (int) ratio[i][0];
            if (bandwidth >= users[index]) {
                maxValue += values[index];
                bandwidth -= users[index];
            } else {
                maxValue += (int) (ratio[i][1] * bandwidth);
                break;
            }
        }
        return maxValue;
    }
}
