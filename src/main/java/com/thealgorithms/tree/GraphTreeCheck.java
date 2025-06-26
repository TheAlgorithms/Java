package com.thealgorithms.tree;

import java.util.Scanner;

/**
 * Check if an undirected graph is a tree using Depth-First Search (DFS).
 * A graph is a tree if it is connected and acyclic.
 * This implementation reads an adjacency matrix as input and verifies both conditions.
 * 
 * Wikipedia Reference: https://en.wikipedia.org/wiki/Tree_(graph_theory)
 * Author: Aman
 */


class GraphTreeCheck {

    private static final int MAX = 10;
    private int[][] adjMatrix = new int[MAX][MAX];
    private boolean[] visited = new boolean[MAX];
    private int nodes;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        GraphTreeCheck graph = new GraphTreeCheck();

        System.out.print("Enter the number of nodes: ");
        graph.nodes = in.nextInt();

        System.out.println("Enter the Adjacency Matrix (1 -> PATH, 0 -> NO PATH):");
        for (int i = 1; i <= graph.nodes; i++) {
            for (int j = 1; j <= graph.nodes; j++) {
                graph.adjMatrix[i][j] = in.nextInt();
                if (i == j) {
                    graph.adjMatrix[i][j] = 0; // No self loops
                }
            }
        }

        if (graph.isTree()) {
            System.out.println("The graph is a TREE.");
        } else {
            System.out.println("The graph is NOT a tree.");
        }
    }

    public boolean isTree() {
        // Check for cycles using DFS
        if (hasCycle(1, -1)) {
            return false;
        }

        // Check for connectivity
        for (int i = 1; i <= nodes; i++) {
            if (!visited[i]) {
                return false;
            }
        }

        return true;
    }

    private boolean hasCycle(int current, int parent) {
        visited[current] = true;

        for (int neighbor = 1; neighbor <= nodes; neighbor++) {
            if (adjMatrix[current][neighbor] == 1) {
                if (!visited[neighbor]) {
                    if (hasCycle(neighbor, current)) {
                        return true;
                    }
                } else if (neighbor != parent) {
                    // Found a back edge indicating a cycle
                    return true;
                }
            }
        }
        return false;
    }
}
