package com.thealgorithms.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.random.RandomGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GomoryHuTreeTest {

    @Test
    @DisplayName("Single node graph")
    void singleNode() {
        int[][] cap = {{0}};
        int[][] res = GomoryHuTree.buildTree(cap);
        int[] parent = res[0];
        int[] weight = res[1];
        assertEquals(-1, parent[0]);
        assertEquals(0, weight[0]);
    }

    @Test
    @DisplayName("Triangle undirected graph with known min-cuts")
    void triangleGraph() {
        // 0-1:3, 1-2:2, 0-2:4
        int[][] cap = new int[3][3];
        cap[0][1] = 3;
        cap[1][0] = 3;
        cap[1][2] = 2;
        cap[2][1] = 2;
        cap[0][2] = 4;
        cap[2][0] = 4;

        int[][] tree = GomoryHuTree.buildTree(cap);
        // validate all pairs via path-min-edge equals maxflow
        validateAllPairs(cap, tree);
    }

    @Test
    @DisplayName("Random small undirected graphs compare to EdmondsKarp")
    void randomSmallGraphs() {
        Random rng = new Random(42);
        for (int n = 2; n <= 6; n++) {
            for (int iter = 0; iter < 10; iter++) {
                int[][] cap = randSymmetricMatrix(n, 0, 5, rng);
                int[][] tree = GomoryHuTree.buildTree(cap);
                validateAllPairs(cap, tree);
            }
        }
    }

    private static int[][] randSymmetricMatrix(int n, int lo, int hi, RandomGenerator rng) {
        int[][] a = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int w = rng.nextInt(hi - lo + 1) + lo;
                a[i][j] = w;
                a[j][i] = w;
            }
        }
        // zero diagonal
        for (int i = 0; i < n; i++) {
            a[i][i] = 0;
        }
        return a;
    }

    private static void validateAllPairs(int[][] cap, int[][] tree) {
        int n = cap.length;
        int[] parent = tree[0];
        int[] weight = tree[1];

        // build adjacency list of tree without generic array creation
        List<List<int[]>> g = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<>());
        }
        for (int v = 1; v < n; v++) {
            int u = parent[v];
            int w = weight[v];
            g.get(u).add(new int[] {v, w});
            g.get(v).add(new int[] {u, w});
        }

        for (int s = 0; s < n; s++) {
            for (int t = s + 1; t < n; t++) {
                int treeVal = minEdgeOnPath(g, s, t);
                int flowVal = EdmondsKarp.maxFlow(cap, s, t);
                assertEquals(flowVal, treeVal, "pair (" + s + "," + t + ")");
            }
        }
    }

    private static int minEdgeOnPath(List<List<int[]>> g, int s, int t) {
        // BFS to record parent and edge weight along the path, since it's a tree, unique path exists
        int n = g.size();
        int[] parent = new int[n];
        int[] edgeW = new int[n];
        Arrays.fill(parent, -1);
        Queue<Integer> q = new ArrayDeque<>();
        q.add(s);
        parent[s] = s;
        while (!q.isEmpty()) {
            int u = q.poll();
            if (u == t) {
                break;
            }
            for (int[] e : g.get(u)) {
                int v = e[0];
                int w = e[1];
                if (parent[v] == -1) {
                    parent[v] = u;
                    edgeW[v] = w;
                    q.add(v);
                }
            }
        }
        int cur = t;
        int ans = Integer.MAX_VALUE;
        while (cur != s) {
            ans = Math.min(ans, edgeW[cur]);
            cur = parent[cur];
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
