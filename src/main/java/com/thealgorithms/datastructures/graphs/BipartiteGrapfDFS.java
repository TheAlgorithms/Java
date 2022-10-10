package com.thealgorithms.datastructures.graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Given an adjacency list of a graph adj of V no. of vertices having 0 based
 * index. Check whether the graph is bipartite or not.
 *
 * Input : {{0, 1, 0, 1}, {1, 0, 1, 0}, {0, 1, 0, 1}, {1, 0, 1, 0}}
 *
 * Output : YES
 */
public class BipartiteGrapfDFS {

    private static boolean bipartite(
        int V,
        ArrayList<ArrayList<Integer>> adj,
        int[] color,
        int node
    ) {
        if (color[node] == -1) {
            color[node] = 1;
        }
        for (Integer it : adj.get(node)) {
            if (color[it] == -1) {
                color[it] = 1 - color[node];
                if (bipartite(V, adj, color, it) == false) {
                    return false;
                }
            } else if (color[it] == color[node]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isBipartite(
        int V,
        ArrayList<ArrayList<Integer>> adj
    ) {
        // Code here
        int[] color = new int[V + 1];
        Arrays.fill(color, -1);

        for (int i = 0; i < V; i++) {
            if (color[i] == -1) {
                if (!bipartite(V, adj, color, i)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(
            new InputStreamReader(System.in)
        );
        int t = Integer.parseInt(read.readLine().trim());
        while (t-- > 0) {
            String[] S = read.readLine().trim().split(" ");
            int V = Integer.parseInt(S[0]);
            int E = Integer.parseInt(S[1]);

            ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
            for (int i = 0; i < V; i++) {
                adj.add(new ArrayList<>());
            }
            for (int i = 0; i < E; i++) {
                String[] s = read.readLine().trim().split(" ");
                int u = Integer.parseInt(s[0]);
                int v = Integer.parseInt(s[1]);
                adj.get(u).add(v);
                adj.get(v).add(u);
            }

            boolean ans = isBipartite(V, adj);
            if (ans) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
