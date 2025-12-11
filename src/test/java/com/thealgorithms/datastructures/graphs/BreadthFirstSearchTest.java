package com.thealgorithms.datastructures.graphs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link BreadthFirstSearch}.
 */
class BreadthFirstSearchTest {

    @Test
    void testBfsWithIntegerGraphSimple() {
        // Create a simple graph: 0 -> 1 -> 2
        // | |
        // v v
        // 3 4
        int[][] edges = { { 0, 1 }, { 0, 3 }, { 1, 2 }, { 1, 4 } };
        List<List<Integer>> graph = BreadthFirstSearch.createGraph(5, edges, false);

        List<Integer> result = BreadthFirstSearch.bfs(graph, 5, 0);

        assertEquals(5, result.size());
        assertEquals(0, result.get(0)); // Source is first
        // BFS visits by level: 0 -> [1, 3] -> [2, 4]
        assertTrue(result.indexOf(1) < result.indexOf(2)); // 1 before 2
        assertTrue(result.indexOf(1) < result.indexOf(4)); // 1 before 4
    }

    @Test
    void testBfsWithUndirectedGraph() {
        // Create an undirected graph
        int[][] edges = { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 } };
        List<List<Integer>> graph = BreadthFirstSearch.createGraph(5, edges, true);

        List<Integer> result = BreadthFirstSearch.bfs(graph, 5, 2);

        assertEquals(5, result.size());
        assertEquals(2, result.get(0)); // Source is first
    }

    @Test
    void testBfsWithDisconnectedGraph() {
        // Graph with disconnected components: 0 -> 1, 2 -> 3 (separate components)
        int[][] edges = { { 0, 1 }, { 2, 3 } };
        List<List<Integer>> graph = BreadthFirstSearch.createGraph(4, edges, false);

        List<Integer> result = BreadthFirstSearch.bfs(graph, 4, 0);

        // Should only visit reachable vertices from 0
        assertEquals(2, result.size());
        assertTrue(result.contains(0));
        assertTrue(result.contains(1));
    }

    @Test
    void testBfsWithSingleVertex() {
        int[][] edges = {};
        List<List<Integer>> graph = BreadthFirstSearch.createGraph(1, edges, false);

        List<Integer> result = BreadthFirstSearch.bfs(graph, 1, 0);

        assertEquals(1, result.size());
        assertEquals(0, result.get(0));
    }

    @Test
    void testBfsWithCyclicGraph() {
        // Graph with a cycle: 0 -> 1 -> 2 -> 0
        int[][] edges = { { 0, 1 }, { 1, 2 }, { 2, 0 } };
        List<List<Integer>> graph = BreadthFirstSearch.createGraph(3, edges, false);

        List<Integer> result = BreadthFirstSearch.bfs(graph, 3, 0);

        assertEquals(3, result.size());
        assertEquals(0, result.get(0)); // Source is first
    }

    @Test
    void testBfsWithMapBasedGraph() {
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", List.of("B", "C"));
        graph.put("B", List.of("D"));
        graph.put("C", List.of("E"));
        graph.put("D", new ArrayList<>());
        graph.put("E", new ArrayList<>());

        List<String> result = BreadthFirstSearch.bfs(graph, "A");

        assertEquals(5, result.size());
        assertEquals("A", result.get(0)); // Source is first
        // B and C should come before D and E
        assertTrue(result.indexOf("B") < result.indexOf("D"));
        assertTrue(result.indexOf("C") < result.indexOf("E"));
    }

    @Test
    void testBfsWithStringGraphCreation() {
        String[][] edges = { { "DELHI", "MUMBAI" }, { "DELHI", "PUNE" }, { "MUMBAI", "GOA" } };
        Map<String, List<String>> graph = BreadthFirstSearch.createStringGraph(edges, true);

        List<String> result = BreadthFirstSearch.bfs(graph, "DELHI");

        assertEquals(4, result.size());
        assertEquals("DELHI", result.get(0));
    }

    @Test
    void testBfsThrowsExceptionForNullSource() {
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", List.of("B"));

        assertThrows(IllegalArgumentException.class, () -> BreadthFirstSearch.bfs(graph, null));
    }

    @Test
    void testBfsThrowsExceptionForSourceNotInGraph() {
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", List.of("B"));

        assertThrows(IllegalArgumentException.class, () -> BreadthFirstSearch.bfs(graph, "X"));
    }

    @Test
    void testBfsThrowsExceptionForInvalidSourceIndex() {
        List<List<Integer>> graph = BreadthFirstSearch.createGraph(3, new int[][] {}, false);

        assertThrows(IllegalArgumentException.class, () -> BreadthFirstSearch.bfs(graph, 3, -1));
        assertThrows(IllegalArgumentException.class, () -> BreadthFirstSearch.bfs(graph, 3, 5));
    }

    @Test
    void testBfsLevelOrderProperty() {
        // Test that BFS visits vertices level by level
        // 0
        // /|\
        // 1 2 3
        // | |
        // 4 5
        int[][] edges = { { 0, 1 }, { 0, 2 }, { 0, 3 }, { 1, 4 }, { 3, 5 } };
        List<List<Integer>> graph = BreadthFirstSearch.createGraph(6, edges, false);

        List<Integer> result = BreadthFirstSearch.bfs(graph, 6, 0);

        // Level 0: 0
        // Level 1: 1, 2, 3
        // Level 2: 4, 5
        assertEquals(0, result.get(0));
        // All level-1 vertices come before level-2 vertices
        int level1End = Math.max(result.indexOf(1), Math.max(result.indexOf(2), result.indexOf(3)));
        int level2Start = Math.min(result.indexOf(4), result.indexOf(5));
        assertTrue(level1End < level2Start);
    }

    @Test
    void testBfsWithEmptyNeighborList() {
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", new ArrayList<>());

        List<String> result = BreadthFirstSearch.bfs(graph, "A");

        assertEquals(1, result.size());
        assertEquals("A", result.get(0));
    }
}
