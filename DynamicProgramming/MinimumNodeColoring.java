package DynamicProgramming;
import java.util.Arrays;

/**
 *  Task : Find minimal number of colors to color nodes of the given graph,
 *         so there will be no connected nodes that are colored in the same color
 *
 *  Solution : Dynamic programming on bitmask. Bitmask - binary representation of the set.
 *                                                       (1-includes, 0-excludes) in binary
 *
 *  Asymptotic of solution : O(3^N) with O(2^N * N^2) preprocessing
 *  Limitations : Problem is NP-hard, though will work in small N
 **/

interface Graph {
    boolean areConnected(int u, int v);   // If the node u is connected with node v
    int getGraphSize();                   //Should return n
}

class Solver {
    Graph g;
    boolean [] group;
    int [] dp;
    int [] path;                        //path,
    final int maxN = 32;
    Solver(Graph g) {
        this.g = g;
    }
    /**
     * Preprocessing : Groups set of nodes so there is no pair of nodes that are connected.
     *                 group[mask] is true if mask fulfills the condition
     **/
    void preprocess() {
        int n = g.getGraphSize();

        group = new boolean[1 << n];       // Total 2^n combination of nodes
        group[0] = true;                   // group with 0 nodes

        for (int mask = 0; mask < (1 << n); mask++) {
            if (!group[mask])
                continue;
            for (int u = 0; u < n; u ++) {
                if ((mask & (1 << u)) != 0)
                    continue;
                boolean flag = true;
                for (int v = 0; v < n; v++) {
                    if ((mask & (1 << v)) == 0)
                        continue;
                    if (g.areConnected(u, v)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    group[mask ^ (1 << u)] = true;
                }
            }
        }
    }

    /**
     *  Solution : Going through all subsets of mask, if coulb be divided into
     *             smaller subsets, so the needed number of colors will be smaller,
     *             it divides.
     **/
    int solve() {
        preprocess();
        int n = g.getGraphSize();

        dp = new int[1 << n];
        path = new int[1 << n];

        Arrays.fill(dp, maxN);
        dp[0] = 0;
        for (int mask = 1; mask < (1 << n); mask++) {
            for (int subset = mask; subset > 0; subset = mask & (subset - 1)) {
                if (group[subset]) {
                    if (dp[mask] > dp[mask ^ subset] + 1) {
                        dp[mask] = dp[mask ^ subset] + 1;
                        path[mask] = subset;
                        path[subset] = subset;
                    }
                }
            }
        }
        return dp[(1 << n) - 1];
    }

    public void printColorGroups() {
        int n = g.getGraphSize();
        int mask = (1 << n) - 1;
        while ( mask != 0) {
            int sup = path[mask];
            printMask(sup);
            mask ^= sup;
        }
    }

    private void printMask(int mask) {

        for (int i = g.getGraphSize() - 1; i >= 0; i--) {
            if ((mask & (1 << i)) != 0 ) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }


}

/**
 *  Simple graph for the test.
 **/
class MyGraph implements Graph {
    private int n = 5;
    int [][] adjacencyMatrix;
    public  MyGraph() {
        adjacencyMatrix = new int[][] {
                {0, 0, 1, 0, 1},
                {0, 0, 1, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0}
        };
    }
    @Override
    public boolean areConnected(int u, int v) {
        return adjacencyMatrix[u][v] == 1;
    }
    @Override
    public int getGraphSize() {
        return n;
    }
}



public class MinimumNodeColoring {
    public static void main(String args[]) {
        MyGraph sampleGraph = new MyGraph();
        Solver solver = new Solver(sampleGraph);
        System.out.println(solver.solve());
        solver.printColorGroups();
    }
}