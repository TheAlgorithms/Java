package com.thealgorithms.datastructures.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Centroid Decomposition is a divide-and-conquer technique for trees.
 * It recursively partitions a tree by finding centroids - nodes whose removal
 * creates balanced subtrees (each with at most N/2 nodes).
 *
 * <p>
 * Time Complexity: O(N log N) for construction
 * Space Complexity: O(N)
 *
 * <p>
 * Applications:
 * - Distance queries on trees
 * - Path counting problems
 * - Nearest neighbor searches
 *
 * @see <a href="https://en.wikipedia.org/wiki/Centroid_decomposition">Centroid Decomposition</a>
 * @see <a href="https://codeforces.com/blog/entry/81661">Centroid Decomposition Tutorial</a>
 * @author lens161
 */
public final class CentroidDecomposition {

    private CentroidDecomposition() {
    }

    /**
     * Represents the centroid tree structure.
     */
    public static final class CentroidTree {
        private final int n;
        private final List<List<Integer>> adj;
        private final int[] parent;
        private final int[] subtreeSize;
        private final boolean[] removed;
        private int root;

        /**
         * Constructs a centroid tree from an adjacency list.
         *
         * @param adj adjacency list representation of the tree (0-indexed)
         * @throws IllegalArgumentException if tree is empty or null
         */
        public CentroidTree(List<List<Integer>> adj) {
            if (adj == null || adj.isEmpty()) {
                throw new IllegalArgumentException("Tree cannot be empty or null");
            }

            this.n = adj.size();
            this.adj = adj;
            this.parent = new int[n];
            this.subtreeSize = new int[n];
            this.removed = new boolean[n];
            Arrays.fill(parent, -1);

            // Build centroid tree starting from node 0
            this.root = decompose(0, -1);
        }

        /**
         * Recursively builds the centroid tree.
         *
         * @param u current node
         * @param p parent in centroid tree
         * @return centroid of current component
         */
        private int decompose(int u, int p) {
            int size = getSubtreeSize(u, -1);
            int centroid = findCentroid(u, -1, size);

            removed[centroid] = true;
            parent[centroid] = p;

            // Recursively decompose each subtree
            for (int v : adj.get(centroid)) {
                if (!removed[v]) {
                    decompose(v, centroid);
                }
            }

            return centroid;
        }

        /**
         * Calculates subtree size from node u.
         *
         * @param u current node
         * @param p parent node (-1 for root)
         * @return size of subtree rooted at u
         */
        private int getSubtreeSize(int u, int p) {
            subtreeSize[u] = 1;
            for (int v : adj.get(u)) {
                if (v != p && !removed[v]) {
                    subtreeSize[u] += getSubtreeSize(v, u);
                }
            }
            return subtreeSize[u];
        }

        /**
         * Finds the centroid of a subtree.
         * A centroid is a node whose removal creates components with size &lt;= totalSize/2.
         *
         * @param u current node
         * @param p parent node
         * @param totalSize total size of current component
         * @return centroid node
         */
        private int findCentroid(int u, int p, int totalSize) {
            for (int v : adj.get(u)) {
                if (v != p && !removed[v] && subtreeSize[v] > totalSize / 2) {
                    return findCentroid(v, u, totalSize);
                }
            }
            return u;
        }

        /**
         * Gets the parent of a node in the centroid tree.
         *
         * @param node the node
         * @return parent node in centroid tree, or -1 if root
         */
        public int getParent(int node) {
            if (node < 0 || node >= n) {
                throw new IllegalArgumentException("Invalid node: " + node);
            }
            return parent[node];
        }

        /**
         * Gets the root of the centroid tree.
         *
         * @return root node
         */
        public int getRoot() {
            return root;
        }

        /**
         * Gets the number of nodes in the tree.
         *
         * @return number of nodes
         */
        public int size() {
            return n;
        }

        /**
         * Returns the centroid tree structure as a string.
         * Format: node -&gt; parent (or ROOT for root node)
         *
         * @return string representation
         */
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("Centroid Tree:\n");
            for (int i = 0; i < n; i++) {
                sb.append("Node ").append(i).append(" -> ");
                if (parent[i] == -1) {
                    sb.append("ROOT");
                } else {
                    sb.append("Parent ").append(parent[i]);
                }
                sb.append("\n");
            }
            return sb.toString();
        }
    }

    /**
     * Creates a centroid tree from an edge list.
     *
     * @param n number of nodes (0-indexed: 0 to n-1)
     * @param edges list of edges where each edge is [u, v]
     * @return CentroidTree object
     * @throws IllegalArgumentException if n &lt;= 0 or edges is invalid
     */
    public static CentroidTree buildFromEdges(int n, int[][] edges) {
        if (n <= 0) {
            throw new IllegalArgumentException("Number of nodes must be positive");
        }
        if (edges == null) {
            throw new IllegalArgumentException("Edges cannot be null");
        }
        if (edges.length != n - 1) {
            throw new IllegalArgumentException("Tree must have exactly n-1 edges");
        }

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            if (edge.length != 2) {
                throw new IllegalArgumentException("Each edge must have exactly 2 nodes");
            }
            int u = edge[0];
            int v = edge[1];

            if (u < 0 || u >= n || v < 0 || v >= n) {
                throw new IllegalArgumentException("Invalid node in edge: [" + u + ", " + v + "]");
            }

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        return new CentroidTree(adj);
    }
}
