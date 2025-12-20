package com.thealgorithms.graphs;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link DijkstraPriorityQueue}.
 */
public class DijkstraPriorityQueueTest {

    private DijkstraPriorityQueue dijkstra;

    @BeforeEach
    void setUp() {
        dijkstra = new DijkstraPriorityQueue();
    }

    @Test
    void testSimpleGraph() {
        Map<Integer, List<DijkstraPriorityQueue.Edge>> graph = new HashMap<>();
        graph.put(
            0,
            List.of(
                new DijkstraPriorityQueue.Edge(1, 7), new DijkstraPriorityQueue.Edge(2, 9)));
        graph.put(1, List.of(new DijkstraPriorityQueue.Edge(2, 10)));
        graph.put(2, new ArrayList<>());

        int[] result = dijkstra.runDijkstra(0, graph, 3);
        int[] expected = {0, 7, 9};
        assertArrayEquals(expected, result);
    }

    @Test
    void testShorterPathFoundLater() {
        // This ensures the 'if (dist[u] + edge.weight < dist[edge.target])' logic
        // is fully tested
        Map<Integer, List<DijkstraPriorityQueue.Edge>> graph = new HashMap<>();
        graph.put(
            0,
            List.of(
                new DijkstraPriorityQueue.Edge(1, 10),
                new DijkstraPriorityQueue.Edge(2, 2)));
        graph.put(
            2,
            List.of(
                new DijkstraPriorityQueue.Edge(1, 3))); // Path 0->2->1 is shorter (5)
        // than 0->1 (10)

        int[] result = dijkstra.runDijkstra(0, graph, 3);
        int[] expected = {0, 5, 2};
        assertArrayEquals(expected, result);
    }

    @Test
    void testStalePriorityQueueEntry() {
        // This forces 'if (d > dist[u]) continue;' to execute
        Map<Integer, List<DijkstraPriorityQueue.Edge>> graph = new HashMap<>();
        // Two edges to the same target: the PQ will have two entries for node 1
        graph.put(
            0,
            List.of(
                new DijkstraPriorityQueue.Edge(1, 10), new DijkstraPriorityQueue.Edge(1, 2)));

        int[] result = dijkstra.runDijkstra(0, graph, 2);
        int[] expected = {0, 2};
        assertArrayEquals(expected, result);
    }

    @Test
    void testNegativeWeightThrows() {
        Map<Integer, List<DijkstraPriorityQueue.Edge>> graph = new HashMap<>();
        graph.put(0, List.of(new DijkstraPriorityQueue.Edge(1, -1)));
        graph.put(1, new ArrayList<>());

        assertThrows(IllegalArgumentException.class, () -> dijkstra.runDijkstra(0, graph, 2));
    }

    @Test
    void testDisconnectedGraph() {
        Map<Integer, List<DijkstraPriorityQueue.Edge>> graph = new HashMap<>();
        graph.put(0, new ArrayList<>());
        graph.put(1, new ArrayList<>());

        int[] result = dijkstra.runDijkstra(0, graph, 2);
        int[] expected = {0, Integer.MAX_VALUE};
        assertArrayEquals(expected, result);
    }

    @Test
    void testEmptyGraph() {
        Map<Integer, List<DijkstraPriorityQueue.Edge>> graph = new HashMap<>();
        int[] result = dijkstra.runDijkstra(0, graph, 0);
        int[] expected = {};
        assertArrayEquals(expected, result);
    }
}