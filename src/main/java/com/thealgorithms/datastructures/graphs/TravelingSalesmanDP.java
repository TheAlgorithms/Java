package com.thealgorithms.datastructures.graphs;

/**
* Problem Statement:
* The Traveling Salesman Problem (TSP) asks for the shortest possible route 
* that visits a given set of cities and returns to the origin city. 
* Each city is connected to every other city, and the goal is to find the shortest route 
* that visits each city exactly once and returns to the starting point.
*
* More information on TSP can be found here:
* https://en.wikipedia.org/wiki/Travelling_salesman_problem
*
* Approach:
* This implementation uses dynamic programming (DP) with bitmasking 
* to represent visited cities. The DP state is dp[mask][i], 
* where 'mask' represents the set of visited cities, and 'i' is the current city.
* 
* Time Complexity: O(n^2 * 2^n), where 'n' is the number of cities.
* Space Complexity: O(n * 2^n), due to the DP table and bitmask representation.
*/

import java.util.Arrays;

public class TravelingSalesmanDP {
   private static final int INF = Integer.MAX_VALUE / 2; // Infinity value for unvisited paths
   
   /**
    * Solves the TSP using dynamic programming.
    *
    * @param dist Matrix where dist[i][j] represents the distance between city i and city j.
    * @return Minimum cost of traveling through all cities and returning to the start.
    */
   public static int tsp(int[][] dist) {
       int n = dist.length;
       int[][] dp = new int[n][(1 << n)]; // DP table
       
       // Initialize DP table with infinity
       for (int[] row : dp) {
           Arrays.fill(row, INF);
       }
       
       // Start at city 0 with only the first city visited
       dp[0][1] = 0;
       
       // Iterate over all subsets of visited cities
       for (int mask = 1; mask < (1 << n); mask++) {
           for (int u = 0; u < n; u++) {
               if ((mask & (1 << u)) == 0) {
                   continue; // If city u is not visited in this subset
               }
               
               for (int v = 0; v < n; v++) {
                   if ((mask & (1 << v)) != 0 || dist[u][v] == INF) {
                       continue; // If city v is already visited
                   }
                   int newMask = mask | (1 << v); // Visit city v
                   dp[v][newMask] = Math.min(dp[v][newMask], dp[u][mask] + dist[u][v]);
               }
           }
       }
       
       // Return the minimum cost of visiting all cities and returning to city 0
       int result = INF;
       for (int i = 1; i < n; i++) {
           result = Math.min(result, dp[i][(1 << n) - 1] + dist[i][0]);
       }
       
       return result;
   }
}
