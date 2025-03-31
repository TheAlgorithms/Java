package com.thealgorithms.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * This class provides solutions to the Traveling Salesman Problem (TSP) using both brute-force and dynamic programming approaches.
 * For more information, see <a href="https://en.wikipedia.org/wiki/Travelling_salesman_problem">Wikipedia</a>.
 * @author  <a href="https://github.com/DenizAltunkapan">Deniz Altunkapan</a>
 */

public final class TravelingSalesman {

    // Private constructor to prevent instantiation
    private TravelingSalesman() {
    }

    /**
     * Solves the Traveling Salesman Problem (TSP) using brute-force approach.
     * This method generates all possible permutations of cities, calculates the total distance for each route, and returns the shortest distance found.
     *
     * @param distanceMatrix A square matrix where element [i][j] represents the distance from city i to city j.
     * @return The shortest possible route distance visiting all cities exactly once and returning to the starting city.
     */
    public static int bruteForce(int[][] distanceMatrix) {
        if (distanceMatrix.length <= 1) {
            return 0;
        }

        List<Integer> cities = new ArrayList<>();
        for (int i = 1; i < distanceMatrix.length; i++) {
            cities.add(i);
        }

        List<List<Integer>> permutations = generatePermutations(cities);
        int minDistance = Integer.MAX_VALUE;

        for (List<Integer> permutation : permutations) {
            List<Integer> route = new ArrayList<>();
            route.add(0);
            route.addAll(permutation);
            int currentDistance = calculateDistance(distanceMatrix, route);
            if (currentDistance < minDistance) {
                minDistance = currentDistance;
            }
        }

        return minDistance;
    }

    /**
     * Computes the total distance of a given route.
     *
     * @param distanceMatrix A square matrix where element [i][j] represents the
     *                       distance from city i to city j.
     * @param route          A list representing the order in which the cities are visited.
     * @return The total distance of the route, or Integer.MAX_VALUE if the route is invalid.
     */
    public static int calculateDistance(int[][] distanceMatrix, List<Integer> route) {
        int distance = 0;
        for (int i = 0; i < route.size() - 1; i++) {
            int d = distanceMatrix[route.get(i)][route.get(i + 1)];
            if (d == Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            distance += d;
        }
        int returnDist = distanceMatrix[route.get(route.size() - 1)][route.get(0)];
        return (returnDist == Integer.MAX_VALUE) ? Integer.MAX_VALUE : distance + returnDist;
    }

    /**
     * Generates all permutations of a given list of cities.
     *
     * @param cities A list of cities to permute.
     * @return A list of all possible permutations.
     */
    private static List<List<Integer>> generatePermutations(List<Integer> cities) {
        List<List<Integer>> permutations = new ArrayList<>();
        permute(cities, 0, permutations);
        return permutations;
    }

    /**
     * Recursively generates permutations using backtracking.
     *
     * @param arr     The list of cities.
     * @param k       The current index in the permutation process.
     * @param output  The list to store generated permutations.
     */
    private static void permute(List<Integer> arr, int k, List<List<Integer>> output) {
        if (k == arr.size()) {
            output.add(new ArrayList<>(arr));
            return;
        }
        for (int i = k; i < arr.size(); i++) {
            Collections.swap(arr, i, k);
            permute(arr, k + 1, output);
            Collections.swap(arr, i, k);
        }
    }

    /**
     * Solves the Traveling Salesman Problem (TSP) using dynamic programming with the Held-Karp algorithm.
     *
     * @param distanceMatrix A square matrix where element [i][j] represents the distance from city i to city j.
     * @return The shortest possible route distance visiting all cities exactly once and returning to the starting city.
     * @throws IllegalArgumentException if the input matrix is not square.
     */
    public static int dynamicProgramming(int[][] distanceMatrix) {
        if (distanceMatrix.length == 0) {
            return 0;
        }
        int n = distanceMatrix.length;

        for (int[] row : distanceMatrix) {
            if (row.length != n) {
                throw new IllegalArgumentException("Matrix must be square");
            }
        }

        int[][] dp = new int[n][1 << n];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dp[0][1] = 0;

        for (int mask = 1; mask < (1 << n); mask++) {
            for (int u = 0; u < n; u++) {
                if ((mask & (1 << u)) == 0 || dp[u][mask] == Integer.MAX_VALUE) {
                    continue;
                }
                for (int v = 0; v < n; v++) {
                    if ((mask & (1 << v)) != 0 || distanceMatrix[u][v] == Integer.MAX_VALUE) {
                        continue;
                    }
                    int newMask = mask | (1 << v);
                    dp[v][newMask] = Math.min(dp[v][newMask], dp[u][mask] + distanceMatrix[u][v]);
                }
            }
        }

        int minDistance = Integer.MAX_VALUE;
        int fullMask = (1 << n) - 1;
        for (int i = 1; i < n; i++) {
            if (dp[i][fullMask] != Integer.MAX_VALUE && distanceMatrix[i][0] != Integer.MAX_VALUE) {
                minDistance = Math.min(minDistance, dp[i][fullMask] + distanceMatrix[i][0]);
            }
        }

        return minDistance == Integer.MAX_VALUE ? 0 : minDistance;
    }
}
