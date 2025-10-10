package com.thealgorithms.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.*;
import org.junit.jupiter.api.Test;

public class HierholzerAlgorithmTest {

    @Test
    public void testFindsEulerianCircuitInSimpleTriangleGraph() {
        // Create a simple triangle graph where a circuit definitely exists: 0-1, 1-2, 2-0
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
    public void testHandlesGraphWithNoEulerianCircuit() {
        // Create a graph where a vertex has an odd degree
        Map<Integer, LinkedList<Integer>> graph = new HashMap<>();
        graph.put(0, new LinkedList<>(Collections.singletonList(1)));
        graph.put(1, new LinkedList<>(Collections.singletonList(0)));
        graph.put(2, new LinkedList<>(Collections.emptyList())); // Vertex 2 is isolated

        HierholzerAlgorithm algorithm = new HierholzerAlgorithm(graph);

        // The algorithm should correctly identify that no circuit exists
        assertEquals(false, algorithm.hasEulerianCircuit());
    }
}
