package Java.src.main.java.com.thealgorithms.datastructures.graphs;
import java.util.Arrays;

public class TravelingSalesmanDP {

    private static final int INF = Integer.MAX_VALUE / 2;  // Avoid overflow

    // Function to find the minimum cost to visit all cities
    public static int tsp(int[][] graph) {
        int n = graph.length;
        int[][] dp = new int[n][1 << n];  // DP table to store results
        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }

        // Base case: Start from the first city (index 0)
        dp[0][1] = 0;

        // Iterate over all subsets of nodes
        for (int mask = 1; mask < (1 << n); mask += 2) {
            for (int u = 0; u < n; u++) {
                if ((mask & (1 << u)) == 0) continue;

                // Try to find the best path to each vertex
                for (int v = 0; v < n; v++) {
                    if ((mask & (1 << v)) == 0) continue;
                    dp[u][mask] = Math.min(dp[u][mask], dp[v][mask ^ (1 << u)] + graph[v][u]);
                }
            }
        }

        // Calculate minimum cost to return to the starting point
        int minCost = INF;
        for (int u = 1; u < n; u++) {
            minCost = Math.min(minCost, dp[u][(1 << n) - 1] + graph[u][0]);
        }

        return minCost;
    }

    public static void main(String[] args) {
        // Example graph (distance matrix)
        int[][] graph = {
            { 0, 10, 15, 20 },
            { 10, 0, 35, 25 },
            { 15, 35, 0, 30 },
            { 20, 25, 30, 0 }
        };

        System.out.println("Minimum tour distance: " + tsp(graph));
    }
}

//This method has a time complexity of O(n^2. 2^n);
