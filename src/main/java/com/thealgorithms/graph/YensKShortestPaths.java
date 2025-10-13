package com.thealgorithms.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Yen's algorithm for finding K loopless shortest paths in a directed graph with non-negative edge weights.
 *
 * <p>Input is an adjacency matrix of edge weights. A value of -1 indicates no edge.
 * All existing edge weights must be non-negative. Zero-weight edges are allowed.</p>
 *
 * <p>References:
 * - Wikipedia: Yen's algorithm (https://en.wikipedia.org/wiki/Yen%27s_algorithm)
 * - Dijkstra's algorithm for the base shortest path computation.</p>
 */
public final class YensKShortestPaths {

    private YensKShortestPaths() {
    }

    /**
     * Compute up to k loopless shortest paths from src to dst using Yen's algorithm.
     *
     * @param weights adjacency matrix; weights[u][v] = -1 means no edge; otherwise non-negative edge weight
     * @param src source vertex index
     * @param dst destination vertex index
     * @param k maximum number of paths to return (k >= 1)
     * @return list of paths, each path is a list of vertex indices in order from src to dst
     * @throws IllegalArgumentException on invalid inputs (null, non-square, negatives on existing edges, bad indices, k < 1)
     */
    public static List<List<Integer>> kShortestPaths(int[][] weights, int src, int dst, int k) {
        validate(weights, src, dst, k);
        final int n = weights.length;
        // Make a defensive copy to avoid mutating caller's matrix
        int[][] w = new int[n][n];
        for (int i = 0; i < n; i++) {
            w[i] = Arrays.copyOf(weights[i], n);
        }

        List<Path> A = new ArrayList<>();
        PriorityQueue<Path> B = new PriorityQueue<>(); // min-heap by cost then lexicographic nodes
        Set<String> seen = new HashSet<>(); // deduplicate candidate paths by node sequence key

        Path first = dijkstra(w, src, dst, new boolean[n]);
        if (first == null) {
            return List.of();
        }
        A.add(first);

        for (int kIdx = 1; kIdx < k; kIdx++) {
            Path lastPath = A.get(kIdx - 1);
            List<Integer> lastNodes = lastPath.nodes;
            for (int i = 0; i < lastNodes.size() - 1; i++) {
                int spurNode = lastNodes.get(i);
                List<Integer> rootPath = lastNodes.subList(0, i + 1);

                // Build modified graph: remove edges that would recreate same root + next edge as any A path
                int[][] wMod = cloneMatrix(w);

                for (Path p : A) {
                    if (startsWith(p.nodes, rootPath) && p.nodes.size() > i + 1) {
                        int u = p.nodes.get(i);
                        int v = p.nodes.get(i + 1);
                        wMod[u][v] = -1; // remove edge
                    }
                }
                // Prevent revisiting nodes in rootPath (loopless constraint), except spurNode itself
                boolean[] blocked = new boolean[n];
                for (int j = 0; j < rootPath.size() - 1; j++) {
                    blocked[rootPath.get(j)] = true;
                }

                Path spurPath = dijkstra(wMod, spurNode, dst, blocked);
                if (spurPath != null) {
                    // concatenate rootPath (excluding spurNode at end) + spurPath
                    List<Integer> totalNodes = new ArrayList<>(rootPath);
                    // spurPath.nodes starts with spurNode; avoid duplication
                    for (int idx = 1; idx < spurPath.nodes.size(); idx++) {
                        totalNodes.add(spurPath.nodes.get(idx));
                    }
                    long rootCost = pathCost(w, rootPath);
                    long totalCost = rootCost + spurPath.cost; // spurPath.cost covers from spurNode to dst
                    Path candidate = new Path(totalNodes, totalCost);
                    String key = candidate.key();
                    if (!seen.contains(key)) {
                        B.add(candidate);
                        seen.add(key);
                    }
                }
            }
            if (B.isEmpty()) {
                break;
            }
            A.add(B.poll());
        }

        // Map to list of node indices for output
        List<List<Integer>> result = new ArrayList<>(A.size());
        for (Path p : A) {
            result.add(new ArrayList<>(p.nodes));
        }
        return result;
    }

    private static void validate(int[][] weights, int src, int dst, int k) {
        if (weights == null || weights.length == 0) {
            throw new IllegalArgumentException("Weights matrix must not be null or empty");
        }
        int n = weights.length;
        for (int i = 0; i < n; i++) {
            if (weights[i] == null || weights[i].length != n) {
                throw new IllegalArgumentException("Weights matrix must be square");
            }
            for (int j = 0; j < n; j++) {
                int val = weights[i][j];
                if (val < -1) {
                    throw new IllegalArgumentException("Weights must be -1 (no edge) or >= 0");
                }
            }
        }
        if (src < 0 || dst < 0 || src >= n || dst >= n) {
            throw new IllegalArgumentException("Invalid src/dst indices");
        }
        if (k < 1) {
            throw new IllegalArgumentException("k must be >= 1");
        }
        if (src == dst) {
            // allowed: path is [src] with cost 0 (handled by dijkstra)
        }
    }

    private static boolean startsWith(List<Integer> list, List<Integer> prefix) {
        if (prefix.size() > list.size()) return false;
        for (int i = 0; i < prefix.size(); i++) {
            if (!Objects.equals(list.get(i), prefix.get(i))) return false;
        }
        return true;
    }

    private static int[][] cloneMatrix(int[][] a) {
        int n = a.length;
        int[][] b = new int[n][n];
        for (int i = 0; i < n; i++) b[i] = Arrays.copyOf(a[i], n);
        return b;
    }

    private static long pathCost(int[][] w, List<Integer> nodes) {
        long cost = 0;
        for (int i = 0; i + 1 < nodes.size(); i++) {
            int u = nodes.get(i), v = nodes.get(i + 1);
            int c = w[u][v];
            if (c < 0) return Long.MAX_VALUE / 4; // invalid
            cost += c;
        }
        return cost;
    }

    private static Path dijkstra(int[][] w, int src, int dst, boolean[] blocked) {
        int n = w.length;
        final long INF = Long.MAX_VALUE / 4;
        long[] dist = new long[n];
        int[] parent = new int[n];
        Arrays.fill(dist, INF);
        Arrays.fill(parent, -1);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        if (blocked[src]) return null;
        dist[src] = 0;
        pq.add(new Node(src, 0));
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.dist != dist[cur.u]) continue;
            if (cur.u == dst) break;
            for (int v = 0; v < n; v++) {
                int wuv = w[cur.u][v];
                if (wuv >= 0 && !blocked[v]) {
                    long nd = cur.dist + wuv;
                    if (nd < dist[v]) {
                        dist[v] = nd;
                        parent[v] = cur.u;
                        pq.add(new Node(v, nd));
                    }
                }
            }
        }
        if (dist[dst] >= INF) {
            // If src==dst and not blocked, the path is trivial with cost 0
            if (src == dst) {
                List<Integer> nodes = new ArrayList<>();
                nodes.add(src);
                return new Path(nodes, 0);
            }
            return null;
        }
        // Reconstruct path
        List<Integer> nodes = new ArrayList<>();
        int cur = dst;
        while (cur != -1) {
            nodes.add(0, cur);
            cur = parent[cur];
        }
        return new Path(nodes, dist[dst]);
    }

    private static final class Node implements Comparable<Node> {
        final int u;
        final long dist;
        Node(int u, long dist) {
            this.u = u;
            this.dist = dist;
        }
        public int compareTo(Node o) {
            return Long.compare(this.dist, o.dist);
        }
    }

    private static final class Path implements Comparable<Path> {
        final List<Integer> nodes;
        final long cost;
        Path(List<Integer> nodes, long cost) {
            this.nodes = nodes;
            this.cost = cost;
        }
        String key() {
            return nodes.toString();
        }
        @Override
        public int compareTo(Path o) {
            int c = Long.compare(this.cost, o.cost);
            if (c != 0) return c;
            // tie-break lexicographically on nodes
            int m = Math.min(this.nodes.size(), o.nodes.size());
            for (int i = 0; i < m; i++) {
                int a = this.nodes.get(i), b = o.nodes.get(i);
                if (a != b) return Integer.compare(a, b);
            }
            return Integer.compare(this.nodes.size(), o.nodes.size());
        }
    }
}
