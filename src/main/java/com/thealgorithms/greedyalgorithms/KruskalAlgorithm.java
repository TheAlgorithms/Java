package com.thealgorithms.greedyalgorithms;

/**
 * An encapsulated, self-contained implementation of Kruskal's algorithm
 * for computing the Minimum Spanning Tree (MST) of a weighted, undirected graph.
 * You can find more about this algorithm in the following link:
 * <a href="https://www.geeksforgeeks.org/dsa/kruskals-minimum-spanning-tree-algorithm-greedy-algo-2/">Kruskal algorithm - Geeks for Geeks</a>
 * <p>
 * To avoid namespace conflicts and maintain isolation within larger projects,
 * all collaborators (Edge, Graph, DisjointSet) are implemented as private
 * static nested classes. This ensures no type leakage outside this file while
 * preserving clean internal architecture.
 * </p>
 *
 * <h2>Usage</h2>
 * <pre>
 *     KruskalAlgorithm algo = new KruskalAlgorithm();
 *     KruskalAlgorithm.Graph graph = new KruskalAlgorithm.Graph(4);
 *     graph.addEdge(0,1,10);
 *     graph.addEdge(1,2,5);
 *     List&lt;KruskalAlgorithm.Edge&gt; mst = algo.computeMST(graph);
 * </pre>
 *
 * <h2>Design Notes</h2>
 * <ul>
 *     <li>Implements a fully isolated module without risk of polluting global scope.</li>
 *     <li>Inner classes preserve encapsulation but keep responsibilities separate.</li>
 *     <li>Algorithm complexity: O(e log e), dominated by edge sorting.</li>
 * </ul>
 */
public class KruskalAlgorithm {

    /**
     * Computes the Minimum Spanning Tree (or Minimum Spanning Forest if the graph
     * is disconnected) using Kruskal’s greedy strategy.
     *
     * @param graph the graph instance to process
     * @return a list of edges forming the MST
     */
    public java.util.List<Edge> computeMST(Graph graph) {
        java.util.List<Edge> mst = new java.util.ArrayList<>();
        java.util.List<Edge> edges = new java.util.ArrayList<>(graph.edges);

        // Sort edges by ascending weight
        java.util.Collections.sort(edges);

        DisjointSet ds = new DisjointSet(graph.numberOfVertices);

        for (Edge e : edges) {
            int rootA = ds.find(e.source);
            int rootB = ds.find(e.target);

            if (rootA != rootB) {
                mst.add(e);
                ds.union(rootA, rootB);

                if (mst.size() == graph.numberOfVertices - 1) break;
            }
        }

        return mst;
    }

    /**
     * Represents an immutable weighted edge between two vertices.
     */
    public static final class Edge implements Comparable<Edge> {
        private final int source;
        private final int target;
        private final int weight;

        public Edge(int source, int target, int weight) {
            if (weight < 0) {
                throw new IllegalArgumentException("Weight cannot be negative.");
            }
            this.source = source;
            this.target = target;
            this.weight = weight;
        }

        public int getSource() { return source; }
        public int getTarget() { return target; }
        public int getWeight() { return weight; }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    /**
     * Lightweight graph representation consisting solely of vertices and edges.
     * All algorithmic behavior is delegated to higher-level components.
     */
    public static final class Graph {
        private final int numberOfVertices;
        private final java.util.List<Edge> edges = new java.util.ArrayList<>();

        public Graph(int numberOfVertices) {
            if (numberOfVertices <= 0) {
                throw new IllegalArgumentException("Graph must have at least one vertex.");
            }
            this.numberOfVertices = numberOfVertices;
        }

        /**
         * Adds an undirected edge to the graph.
         */
        public void addEdge(int source, int target, int weight) {
            if (source < 0 || source >= numberOfVertices ||
                    target < 0 || target >= numberOfVertices) {
                throw new IndexOutOfBoundsException("Vertex index out of range.");
            }

            edges.add(new Edge(source, target, weight));
        }
    }

    /**
     * Disjoint Set Union data structure supporting path compression
     * and union-by-rank — essential for cycle detection in Kruskal's algorithm.
     */
    private static final class DisjointSet {
        private final int[] parent;
        private final int[] rank;

        public DisjointSet(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) parent[i] = i;
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // Path compression
            }
            return parent[x];
        }

        public void union(int a, int b) {
            int ra = find(a);
            int rb = find(b);
            if (ra == rb) return;

            if (rank[ra] < rank[rb]) {
                parent[ra] = rb;
            } else if (rank[ra] > rank[rb]) {
                parent[rb] = ra;
            } else {
                parent[rb] = ra;
                rank[ra]++;
            }
        }
    }
}

