import java.util.Arrays;

public class TravellingSalesMan {

    public static int tsp(int[][] graph, int mask, int pos, int n, int[][] dp) {
        if (mask == (1 << n) - 1) {
            return graph[pos][0]; 
        }

        if (dp[mask][pos] != -1) {
            return dp[mask][pos];
        }

        int minCost = Integer.MAX_VALUE;
        for (int city = 0; city < n; city++) {
            if ((mask & (1 << city)) == 0) {
                int newMask = mask | (1 << city);
                int cost = graph[pos][city] + tsp(graph, newMask, city, n, dp);
                minCost = Math.min(minCost, cost);
            }
        }

        dp[mask][pos] = minCost;
        return minCost;
    }

    public static int solveTSP(int[][] graph) {
        int n = graph.length;
        int[][] dp = new int[1 << n][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return tsp(graph, 1, 0, n, dp); 
    }

    public static void main(String[] args) {
        int[][] graph = {
            {0, 29, 20, 21},
            {29, 0, 15, 12},
            {20, 15, 0, 17},
            {21, 12, 17, 0}
        };

        int minCost = solveTSP(graph);
        System.out.println("Minimum Cost: " + minCost);
    }
}
