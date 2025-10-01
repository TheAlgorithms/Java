package com.thealgorithms.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BronKerboschTest {

    @Test
    @DisplayName("Complete graph returns single clique")
    void completeGraph() {
        List<Set<Integer>> adjacency = buildGraph(4);
        addUndirectedEdge(adjacency, 0, 1);
        addUndirectedEdge(adjacency, 0, 2);
        addUndirectedEdge(adjacency, 0, 3);
        addUndirectedEdge(adjacency, 1, 2);
        addUndirectedEdge(adjacency, 1, 3);
        addUndirectedEdge(adjacency, 2, 3);

        List<Set<Integer>> cliques = BronKerbosch.findMaximalCliques(adjacency);
        assertEquals(1, cliques.size());
        assertEquals(Set.of(0, 1, 2, 3), cliques.get(0));
    }

    @Test
    @DisplayName("Path graph produces individual edges")
    void pathGraph() {
        List<Set<Integer>> adjacency = buildGraph(3);
        addUndirectedEdge(adjacency, 0, 1);
        addUndirectedEdge(adjacency, 1, 2);

        List<Set<Integer>> cliques = BronKerbosch.findMaximalCliques(adjacency);
        Set<Set<Integer>> result = new HashSet<>(cliques);
        Set<Set<Integer>> expected = Set.of(Set.of(0, 1), Set.of(1, 2));
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Disconnected graph finds cliques per component")
    void disconnectedGraph() {
        List<Set<Integer>> adjacency = buildGraph(5);
        addUndirectedEdge(adjacency, 0, 1);
        addUndirectedEdge(adjacency, 0, 2);
        addUndirectedEdge(adjacency, 1, 2);
        addUndirectedEdge(adjacency, 3, 4);

        List<Set<Integer>> cliques = BronKerbosch.findMaximalCliques(adjacency);
        Set<Set<Integer>> result = new HashSet<>(cliques);
        Set<Set<Integer>> expected = Set.of(Set.of(0, 1, 2), Set.of(3, 4));
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Null neighbor set triggers exception")
    void nullNeighborSet() {
        List<Set<Integer>> adjacency = new ArrayList<>();
        adjacency.add(null);
        assertThrows(IllegalArgumentException.class, () -> BronKerbosch.findMaximalCliques(adjacency));
    }

    private static List<Set<Integer>> buildGraph(int n) {
        List<Set<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new HashSet<>());
        }
        return graph;
    }

    private static void addUndirectedEdge(List<Set<Integer>> graph, int u, int v) {
        graph.get(u).add(v);
        graph.get(v).add(u);
    }
}
