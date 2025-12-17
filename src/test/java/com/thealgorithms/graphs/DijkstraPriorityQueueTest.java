package com.thealgorithms.graphs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        graph.put(0, List.of(new DijkstraPriorityQueue.Edge(1, 7), new DijkstraPriorityQueue.Edge(2, 9)));
        graph.put(1, List.of(new DijkstraPriorityQueue.Edge(2, 10)));
        graph.put(2, new ArrayList<>());

        int[] result = dijkstra.runDijkstra(0, graph, 3);
        int[] expected = {0, 7, 9};
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
