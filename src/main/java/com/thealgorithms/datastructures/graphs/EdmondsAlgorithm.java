package edmonds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EdmondsAlgorithm {

    // Class to represent edges in the graph
    static class Edge {
        int u;
        int v;
        int weight;

        Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }

    // Implementation of Edmonds' Algorithm
    public static int maxWeightMatching(List<Edge> edges, int n) {
        // The number of nodes in the graph
        int[] match = new int[n];
        Arrays.fill(match, -1); // no match

        // Perform the algorithm
        int result = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(visited, false);
            if (augmentPath(i, edges, match, visited)) {
                result++;
            }
        }

        return result;
    }

    // Augmenting path search using DFS
    private static boolean augmentPath(int u, List<Edge> edges, int[] match, boolean[] visited) {
        for (Edge edge : edges) {
            if (edge.u == u || edge.v == u) {
                int v = (edge.u == u) ? edge.v : edge.u;
                if (!visited[v]) {
                    visited[v] = true;

                    if (match[v] == -1 || augmentPath(match[v], edges, match, visited)) {
                        match[u] = v;
                        match[v] = u;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // Input for testing
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 10));
        edges.add(new Edge(1, 2, 15));
        edges.add(new Edge(0, 2, 20));
        edges.add(new Edge(2, 3, 25));
        edges.add(new Edge(3, 4, 30));

        int n = 5; // Number of vertices

        System.out.println("Maximum weight matching: " + maxWeightMatching(edges, n));
    }
}
