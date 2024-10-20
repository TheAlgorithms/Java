package com.thealgorithms.backtracking;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class DijkstraTest {

    @Test
    void testSingleNodeGraph() {
        // Test case where graph has a single node
        List<List<Dijkstra.Edge>> graph = new ArrayList<>();
        graph.add(new ArrayList<>());

        Dijkstra dijkstra = new Dijkstra();
        int[] result = dijkstra.dijkstra(graph, 0);

        // Since it's the only node, the distance to itself is 0
        assertArrayEquals(new int[]{0}, result);
    }

    @Test
    void testDisconnectedGraph() {
        // Test case where graph is disconnected
        int V = 3;  // 3 nodes in the graph
        List<List<Dijkstra.Edge>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        Dijkstra dijkstra = new Dijkstra();
        int[] result = dijkstra.dijkstra(graph, 0);

        // Node 0 can only reach itself, others should be unreachable (infinity)
        assertArrayEquals(new int[]{0, Integer.MAX_VALUE, Integer.MAX_VALUE}, result);
    }

    @Test
    void testSimpleGraph() {
        // Test case for a simple connected graph
        int V = 4;
        List<List<Dijkstra.Edge>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        // Add edges to the graph
        graph.get(0).add(new Dijkstra.Edge(1, 1));
        graph.get(0).add(new Dijkstra.Edge(2, 4));
        graph.get(1).add(new Dijkstra.Edge(2, 2));
        graph.get(1).add(new Dijkstra.Edge(3, 6));
        graph.get(2).add(new Dijkstra.Edge(3, 3));

        Dijkstra dijkstra = new Dijkstra();
        int[] result = dijkstra.dijkstra(graph, 0);

        // Expected shortest distances from node 0 to all other nodes
        assertArrayEquals(new int[]{0, 1, 3, 6}, result);
    }

    @Test
    void testInvalidSourceNode() {
        int V = 3;
        List<List<Dijkstra.Edge>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        Dijkstra dijkstra = new Dijkstra();

        assertThrows(IndexOutOfBoundsException.class, () -> {
            dijkstra.dijkstra(graph, 5);  
        });
    }

    @Test
    void testCyclicGraph() {
        // Test case for a cyclic graph
        int V = 3;
        List<List<Dijkstra.Edge>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        graph.get(0).add(new Dijkstra.Edge(1, 2));
        graph.get(1).add(new Dijkstra.Edge(2, 3));
        graph.get(2).add(new Dijkstra.Edge(0, 4));

        Dijkstra dijkstra = new Dijkstra();
        int[] result = dijkstra.dijkstra(graph, 0);

        assertArrayEquals(new int[]{0, 2, 5}, result);
    }
}
