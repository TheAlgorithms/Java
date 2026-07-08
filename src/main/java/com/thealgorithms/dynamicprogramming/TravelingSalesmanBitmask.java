package com.thealgorithms.dynamicprogramming;

import java.util.Arrays;

/**
 * Solves the Traveling Salesman Problem using bitmask dynamic programming.
 *
 * <p>The DP state is {@code dp[mask][city]}, where {@code mask} stores the set of
 * visited cities and {@code city} is the current endpoint of the partial tour.
 * This is the classic Held-Karp formulation and is practical for small inputs
 * where {@code n <= 20}.</p>
 *
 * <p>This bitmask version complements {@code graph/TravelingSalesman.java} by
 * showing the subset-DP formulation explicitly, which is useful for small
 * instances and for problems that require visiting subsets in a specific order.</p>
 */
public final class TravelingSalesmanBitmask {

    private static final int INF = Integer.MAX_VALUE / 4;

    private TravelingSalesmanBitmask() {
    }

    /**
     * Computes the minimum Hamiltonian cycle cost starting and ending at city 0.
     *
        * <p>The input must be a square matrix. Use {@link Integer#MAX_VALUE} for
        * missing edges. The method returns {@code 0} when no Hamiltonian cycle exists.
        *
        * @param distanceMatrix square matrix of edge weights; use {@link Integer#MAX_VALUE}
        *     for missing edges
        * @return the minimum tour cost, or 0 when no tour exists
     * @throws IllegalArgumentException if the matrix is not square
     */
    public static int solve(int[][] distanceMatrix) {
        if (distanceMatrix == null) {
            throw new IllegalArgumentException("Distance matrix cannot be null");
        }
        if (distanceMatrix.length == 0) {
            return 0;
        }

        int n = distanceMatrix.length;
        for (int[] row : distanceMatrix) {
            if (row == null || row.length != n) {
                throw new IllegalArgumentException("Matrix must be square");
            }
        }

        int[][] dp = new int[1 << n][n];
        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }

        dp[1][0] = 0;

        for (int mask = 1; mask < (1 << n); mask++) {
            for (int currentCity = 0; currentCity < n; currentCity++) {
                if (!isBitSet(mask, currentCity) || dp[mask][currentCity] == INF) {
                    continue;
                }

                for (int nextCity = 0; nextCity < n; nextCity++) {
                    if (isBitSet(mask, nextCity) || distanceMatrix[currentCity][nextCity] == Integer.MAX_VALUE) {
                        continue;
                    }

                    int nextMask = setBit(mask, nextCity);
                    int newCost = safeAdd(dp[mask][currentCity], distanceMatrix[currentCity][nextCity]);
                    if (newCost == INF) {
                        continue;
                    }
                    if (newCost < dp[nextMask][nextCity]) {
                        dp[nextMask][nextCity] = newCost;
                    }
                }
            }
        }

        int fullMask = (1 << n) - 1;
        int bestTour = INF;
        for (int lastCity = 1; lastCity < n; lastCity++) {
            if (dp[fullMask][lastCity] == INF || distanceMatrix[lastCity][0] == Integer.MAX_VALUE) {
                continue;
            }
            bestTour = Math.min(bestTour, safeAdd(dp[fullMask][lastCity], distanceMatrix[lastCity][0]));
        }

        return bestTour == INF ? 0 : bestTour;
    }

    private static boolean isBitSet(int mask, int bit) {
        return (mask & (1 << bit)) != 0;
    }

    private static int setBit(int mask, int bit) {
        return mask | (1 << bit);
    }

    private static int safeAdd(int left, int right) {
        if (left == INF || right == Integer.MAX_VALUE) {
            return INF;
        }
        long sum = (long) left + right;
        return sum >= INF ? INF : (int) sum;
    }
}