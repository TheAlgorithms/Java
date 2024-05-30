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
public final class BipartiteGrapfDFS {
    private BipartiteGrapfDFS() {
    }

    private static boolean bipartite(int v, ArrayList<ArrayList<Integer>> adj, int[] color, int node) {
        if (color[node] == -1) {
            color[node] = 1;
        }
        for (Integer it : adj.get(node)) {
            if (color[it] == -1) {
                color[it] = 1 - color[node];
                if (!bipartite(v, adj, color, it)) {
                    return false;
                }
            } else if (color[it] == color[node]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isBipartite(int v, ArrayList<ArrayList<Integer>> adj) {
        // Code here
        int[] color = new int[v + 1];
        Arrays.fill(color, -1);

        for (int i = 0; i < v; i++) {
            if (color[i] == -1) {
                if (!bipartite(v, adj, color, i)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine().trim());
        while (t-- > 0) {
            String[] str1 = read.readLine().trim().split(" ");
            int numVertices = Integer.parseInt(str1[0]);
            int numEdges = Integer.parseInt(str1[1]);

            ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
            for (int i = 0; i < numVertices; i++) {
                adj.add(new ArrayList<>());
            }
            for (int i = 0; i < numEdges; i++) {
                String[] str2 = read.readLine().trim().split(" ");
                int vertexU = Integer.parseInt(str2[0]);
                int vertexV = Integer.parseInt(str2[1]);
                adj.get(vertexU).add(vertexV);
                adj.get(vertexV).add(vertexU);
            }

            boolean ans = isBipartite(numVertices, adj);
            if (ans) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
