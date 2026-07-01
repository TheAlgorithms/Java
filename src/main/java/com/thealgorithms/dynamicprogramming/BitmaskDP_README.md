This document provides a detailed, line-by-line breakdown of the Traveling Salesperson Problem (TSP) implementation using Bitmask Dynamic Programming.

Class: BitmaskDP

A utility class containing the algorithm to solve the TSP. It is declared as final with a private constructor to prevent instantiation, adhering to standard Java utility class conventions.

Method: tsp(int[][] distance)

Calculates the minimum cost to visit every node exactly once and return to the starting node.

Parameters

distance (int[][]): An $N \times N$ adjacency matrix representing the graph. distance[u][v] holds the cost/distance to travel from node u to node v.

Returns

int: The minimum total distance required to complete the TSP tour.

Variable Breakdown

n: The total number of nodes (cities) in the graph.

totalSubsets: Calculated as 1 << n (which equals $2^n$). This represents the total number of possible combinations of visited nodes.

dp: A 2D array of size [totalSubsets][n].

mask (row): The current subset of visited nodes represented as an integer bitmask.

i (column): The node the path currently ends on.

dp[mask][i] stores the minimum cost to reach this state.

Step-by-Step Logic

1. Initialization

for (int[] row : dp) {
    Arrays.fill(row, Integer.MAX_VALUE); 
}


All states in the DP table are initialized to Integer.MAX_VALUE (infinity). This indicates that, initially, all paths are considered unreachable.

2. The Base Case

dp[1][0] = 0;


The algorithm assumes node 0 is the starting point.

The binary representation of 1 is ...0001, meaning only the 0-th bit is set (only node 0 has been visited).

The cost to be at node 0 having only visited node 0 is 0.

3. State Transitions (The Core Loop)

The algorithm iterates through every possible mask (from 1 to totalSubsets - 1).

for (int u = 0; u < n; u++) {
    if ((mask & (1 << u)) == 0 || dp[mask][u] == Integer.MAX_VALUE) continue;


For each mask, it checks every node u to see if it acts as a valid "current end node". It skips the iteration if:

Node u is not in the current mask.

The state dp[mask][u] is unreachable (Integer.MAX_VALUE).

for (int v = 0; v < n; v++) {
    if ((mask & (1 << v)) != 0) continue;


It then looks for an unvisited adjacent node v. If v is already included in the mask (meaning the bit is 1), it skips v because the TSP requires visiting nodes exactly once.

int nextMask = mask | (1 << v);
dp[nextMask][v] = Math.min(dp[nextMask][v], dp[mask][u] + distance[u][v]);


If v is unvisited, the algorithm calculates the nextMask by setting the $v$-th bit to 1. It then updates the DP table for nextMask ending at v with the minimum of:

Its current known cost.

The cost of the current state (dp[mask][u]) plus the travel cost from u to v.

4. Calculating the Final Tour Cost

int minCost = Integer.MAX_VALUE;
int allVisitedMask = totalSubsets - 1; 

for (int i = 1; i < n; i++) {
    minCost = Math.min(minCost, dp[allVisitedMask][i] + distance[i][0]);
}


After the DP table is fully populated, the algorithm looks at the state where all nodes have been visited (allVisitedMask, where all $N$ bits are 1).

It iterates through every possible ending node i, taking the cost of reaching that node and adding the distance to travel back to the starting node 0 (distance[i][0]). The smallest resulting sum is returned as the final minCost.