package com.thealgorithms.dynamicprogramming;

import java.util.Arrays;

/**
 * An implementation of Dynamic Programming using Bitmasks.
 * * Bitmask DP represents a subset of elements as bits in an integer. 
 * If the i-th bit is 1, the i-th element is included in the subset.
 * This is highly effective for problems where n is small (≤ 20).
 * * This class demonstrates Bitmask DP by solving the Traveling 
 * Salesperson Problem (TSP) - finding the minimum cost to visit all 
 * nodes exactly once and return to the starting node.
 */
public final class BitmaskDP {
    
    private BitmaskDP() {
        // Prevent instantiation
    }

    /**
     * Solves the Traveling Salesperson Problem using Bitmask DP.
     * * @param distance A 2D array where distance[i][j] is the cost to travel from node i to node j.
     * @return The minimum distance to visit all nodes and return to the start.
     */
    public static int tsp(int[][] distance) {
        int n = distance.length;
        if (n == 0) return 0;
        
        int totalSubsets = 1 << n;
        
        // dp[mask][i] = min distance visiting the subset 'mask' and currently ending at node 'i'
        int[][] dp = new int[totalSubsets][n];
        
        // Initialize with Integer.MAX_VALUE
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE); 
        }

        // Base case: Start at node 0.
        dp[1][0] = 0;

        for (int mask = 1; mask < totalSubsets; mask++) {
            for (int u = 0; u < n; u++) {
                // Skip if node 'u' is NOT in the current subset, 
                // OR if the current state is unreachable (INT_MAX)
                if ((mask & (1 << u)) == 0 || dp[mask][u] == Integer.MAX_VALUE) continue;
                
                for (int v = 0; v < n; v++) {
                    // If node 'v' IS already in the subset, skip
                    if ((mask & (1 << v)) != 0) continue;
                    
                    int nextMask = mask | (1 << v);
                    
                    // Safe to add since we already verified dp[mask][u] is not INT_MAX
                    dp[nextMask][v] = Math.min(dp[nextMask][v], dp[mask][u] + distance[u][v]);
                }
            }
        }
        
        // Find the minimum cost to return to the starting node (0) from the last visited node
        int minCost = Integer.MAX_VALUE;
        int allVisitedMask = totalSubsets - 1; // All n bits are 1
        
        for (int i = 1; i < n; i++) {
            minCost = Math.min(minCost, dp[allVisitedMask][i] + distance[i][0]);
        }
        
        return minCost;
    }
}