package com.thealgorithms.graph;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.jupiter.api.Test;

public class HierholzerAlgorithmTest {

    @Test
    public void testFindsEulerianCircuitInSimpleTriangleGraph() {
        // A simple triangle graph where a circuit definitely exists: 0-1, 1-2, 2-0
        Map<Integer, LinkedList<Integer>> graph = new HashMap<>();
        graph.put(0, new LinkedList<>(Arrays.asList(1, 2)));
        graph.put(1, new LinkedList<>(Arrays.asList(0, 2)));
        graph.put(2, new LinkedList<>(Arrays.asList(0, 1)));

        HierholzerAlgorithm algorithm = new HierholzerAlgorithm(graph);

        // Verify that the algorithm agrees a circuit exists
        assertTrue(algorithm.hasEulerianCircuit());

        List<Integer> circuit = algorithm.findEulerianCircuit();

        // A valid circuit for a triangle has 3 edges, so the path will have 4 vertices (e.g., 0 -> 1 -> 2 -> 0)
        assertEquals(4, circuit.size());

        // The path must start and end at the same vertex
        assertEquals(circuit.get(0), circuit.get(circuit.size() - 1));
    }

    @Test
    public void testFailsForGraphWithOddDegreeVertices() {
        // Create a graph where vertices 0 and 1 have an odd degree (1)
        Map<Integer, LinkedList<Integer>> graph = new HashMap<>();
        graph.put(0, new LinkedList<>(Collections.singletonList(1)));
        graph.put(1, new LinkedList<>(Collections.singletonList(0)));

        HierholzerAlgorithm algorithm = new HierholzerAlgorithm(graph);

        // The algorithm should correctly identify that no circuit exists
        assertFalse(algorithm.hasEulerianCircuit());
        // The find method should return an empty list
        assertTrue(algorithm.findEulerianCircuit().isEmpty());
    }
    
    @Test
    public void testFailsForDisconnectedGraph() {
        // Create a graph with two separate triangles (0-1-2 and 3-4-5)
        Map<Integer, LinkedList<Integer>> graph = new HashMap<>();
        graph.put(0, new LinkedList<>(Arrays.asList(1, 2)));
        graph.put(1, new LinkedList<>(Arrays.asList(0, 2)));
        graph.put(2, new LinkedList<>(Arrays.asList(0, 1)));
        graph.put(3, new LinkedList<>(Arrays.asList(4, 5)));
        graph.put(4, new LinkedList<>(Arrays.asList(3, 5)));
        graph.put(5, new LinkedList<>(Arrays.asList(3, 4)));

        HierholzerAlgorithm algorithm = new HierholzerAlgorithm(graph);

        // All degrees are even, but the graph is not connected, so no circuit exists
        assertFalse(algorithm.hasEulerianCircuit());
    }

    @Test
    public void testHandlesEmptyGraph() {
        Map<Integer, LinkedList<Integer>> graph = new HashMap<>();
        HierholzerAlgorithm algorithm = new HierholzerAlgorithm(graph);
        assertTrue(algorithm.hasEulerianCircuit());
        assertTrue(algorithm.findEulerianCircuit().isEmpty());
    }
}
