package com.thealgorithms.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Implementation of the Bron–Kerbosch algorithm with pivoting for enumerating all maximal cliques
 * in an undirected graph.
 *
 * <p>The input graph is represented as an adjacency list where {@code adjacency.get(u)} returns the
 * set of vertices adjacent to {@code u}. The algorithm runs in time proportional to the number of
 * maximal cliques produced and is widely used for clique enumeration problems.</p>
 *
 * @author <a href="https://en.wikipedia.org/wiki/Bron%E2%80%93Kerbosch_algorithm">Wikipedia: Bron–Kerbosch algorithm</a>
 */
public final class BronKerbosch {

    private BronKerbosch() {
    }

    /**
     * Finds all maximal cliques of the provided graph.
     *
     * @param adjacency adjacency list where {@code adjacency.size()} equals the number of vertices
     * @return a list containing every maximal clique, each represented as a {@link Set} of vertices
     * @throws IllegalArgumentException if the adjacency list is {@code null}, contains {@code null}
     *         entries, or references invalid vertices
     */
    public static List<Set<Integer>> findMaximalCliques(List<Set<Integer>> adjacency) {
        if (adjacency == null) {
            throw new IllegalArgumentException("Adjacency list must not be null");
        }

        int n = adjacency.size();
        List<Set<Integer>> graph = new ArrayList<>(n);
        for (int u = 0; u < n; u++) {
            Set<Integer> neighbors = adjacency.get(u);
            if (neighbors == null) {
                throw new IllegalArgumentException("Adjacency list must not contain null sets");
            }
            Set<Integer> copy = new HashSet<>();
            for (int v : neighbors) {
                if (v < 0 || v >= n) {
                    throw new IllegalArgumentException("Neighbor index out of bounds: " + v);
                }
                if (v != u) {
                    copy.add(v);
                }
            }
            graph.add(copy);
        }

        Set<Integer> r = new HashSet<>();
        Set<Integer> p = new HashSet<>();
        Set<Integer> x = new HashSet<>();
        for (int v = 0; v < n; v++) {
            p.add(v);
        }

        List<Set<Integer>> cliques = new ArrayList<>();
        bronKerboschPivot(r, p, x, graph, cliques);
        return cliques;
    }

    private static void bronKerboschPivot(Set<Integer> r, Set<Integer> p, Set<Integer> x, List<Set<Integer>> graph, List<Set<Integer>> cliques) {
        if (p.isEmpty() && x.isEmpty()) {
            cliques.add(new HashSet<>(r));
            return;
        }

        int pivot = choosePivot(p, x, graph);
        Set<Integer> candidates = new HashSet<>(p);
        if (pivot != -1) {
            candidates.removeAll(graph.get(pivot));
        }

        for (Integer v : candidates) {
            r.add(v);
            Set<Integer> newP = intersection(p, graph.get(v));
            Set<Integer> newX = intersection(x, graph.get(v));
            bronKerboschPivot(r, newP, newX, graph, cliques);
            r.remove(v);
            p.remove(v);
            x.add(v);
        }
    }

    private static int choosePivot(Set<Integer> p, Set<Integer> x, List<Set<Integer>> graph) {
        int pivot = -1;
        int maxDegree = -1;
        Set<Integer> union = new HashSet<>(p);
        union.addAll(x);
        for (Integer v : union) {
            int degree = graph.get(v).size();
            if (degree > maxDegree) {
                maxDegree = degree;
                pivot = v;
            }
        }
        return pivot;
    }

    private static Set<Integer> intersection(Set<Integer> base, Set<Integer> neighbors) {
        Set<Integer> result = new HashSet<>();
        for (Integer v : base) {
            if (neighbors.contains(v)) {
                result.add(v);
            }
        }
        return result;
    }
}
