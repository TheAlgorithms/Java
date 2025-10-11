package com.thealgorithms.graph;

import java.util.*;  // For ArrayList, Queue, LinkedList

public class IsGraphBipartite {
    // Inner class to represent an edge
    class Edge {
        int src;
        int dest;

        public Edge(int s, int d) {
            this.src = s;
            this.dest = d;
        }
    }

    // Function to check if a graph is bipartite
    public static boolean isbipartite(ArrayList<Edge>[] graph) {
        int col[] = new int[graph.length];
        Arrays.fill(col, -1); // initialize all vertices with -1 (uncolored)

        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < graph.length; i++) {
            if (col[i] == -1) { // unvisited node
                q.add(i);
                col[i] = 0; // assign first color

                while (!q.isEmpty()) {
                    int curr = q.remove();
                    for (int j = 0; j < graph[curr].size(); j++) {
                        Edge e = graph[curr].get(j);

                        if (col[e.dest] == -1) {
                            int nextCol = (col[curr] == 0) ? 1 : 0;
                            col[e.dest] = nextCol;
                            q.add(e.dest);
                        } else if (col[e.dest] == col[curr]) {
                            return false; // same color conflict
                        }
                    }
                }
            }
        }
        return true;
    }

    // Converts adjacency list (int[][]) to Edge-based graph and checks bipartiteness
    public boolean isBipartite(int[][] graph) {
        ArrayList<Edge>[] graph2 = new ArrayList[graph.length];
        for (int i = 0; i < graph2.length; i++) {
            graph2[i] = new ArrayList<>();
        }

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                int neighbor = graph[i][j];
                graph2[i].add(new Edge(i, neighbor));
            }
        }

        return isbipartite(graph2);
    }

    // Test the function
    public static void main(String[] args) {
        IsGraphBipartite sol = new IsGraphBipartite();

        // Example 1: Bipartite graph
        int[][] graph1 = {
            {1, 3},
            {0, 2},
            {1, 3},
            {0, 2}
        };
        System.out.println("Graph 1 is bipartite: " + sol.isBipartite(graph1)); // true

        // Example 2: Non-bipartite graph (odd cycle)
        int[][] graph2 = {
            {1, 2},
            {0, 2},
            {0, 1}
        };
        System.out.println("Graph 2 is bipartite: " + sol.isBipartite(graph2)); // false
    }
}
