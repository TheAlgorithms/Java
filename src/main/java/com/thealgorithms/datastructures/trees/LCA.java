package com.thealgorithms.datastructures.trees;

import java.util.ArrayList;
import java.util.Scanner;

public class LCA {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // The adjacency list representation of a tree:
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        // v is the number of vertices and e is the number of edges
        int v = scanner.nextInt(), e = v - 1;

        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<Integer>());
        }

        // Storing the given tree as an adjacency list
        int to, from;
        for (int i = 0; i < e; i++) {
            to = scanner.nextInt();
            from = scanner.nextInt();

            adj.get(to).add(from);
            adj.get(from).add(to);
        }

        // parent[v1] gives parent of a vertex v1
        int[] parent = new int[v];

        // depth[v1] gives depth of vertex v1 with respect to the root
        int[] depth = new int[v];

        // Assuming the tree to be rooted at 0, hence calculating parent and depth of every vertex
        dfs(adj, 0, -1, parent, depth);

        // Inputting the two vertices whose LCA is to be calculated
        int v1 = scanner.nextInt(), v2 = scanner.nextInt();

        // Outputting the LCA
        System.out.println(getLCA(v1, v2, depth, parent));
    }

    /**
     * Depth first search to calculate parent and depth of every vertex
     *
     * @param adj The adjacency list representation of the tree
     * @param s The source vertex
     * @param p Parent of source
     * @param parent An array to store parents of all vertices
     * @param depth An array to store depth of all vertices
     */
    private static void dfs(ArrayList<ArrayList<Integer>> adj, int s, int p, int[] parent, int[] depth) {
        for (int adjacent : adj.get(s)) {
            if (adjacent != p) {
                parent[adjacent] = s;
                depth[adjacent] = 1 + depth[s];
                dfs(adj, adjacent, s, parent, depth);
            }
        }
    }

    /**
     * Method to calculate Lowest Common Ancestor
     *
     * @param v1 The first vertex
     * @param v2 The second vertex
     * @param depth An array with depths of all vertices
     * @param parent An array with parents of all vertices
     * @return Returns a vertex that is LCA of v1 and v2
     */
    private static int getLCA(int v1, int v2, int[] depth, int[] parent) {
        if (depth[v1] < depth[v2]) {
            int temp = v1;
            v1 = v2;
            v2 = temp;
        }
        while (depth[v1] != depth[v2]) {
            v1 = parent[v1];
        }
        if (v1 == v2) {
            return v1;
        }
        while (v1 != v2) {
            v1 = parent[v1];
            v2 = parent[v2];
        }
        return v1;
    }
}
/**
 * Input:
 * 10
 * 0 1
 * 0 2
 * 1 5
 * 5 6
 * 2 4
 * 2 3
 * 3 7
 * 7 9
 * 7 8
 * 9 4
 * Output:
 * 2
 */
