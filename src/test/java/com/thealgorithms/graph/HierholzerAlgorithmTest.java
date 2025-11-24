package com.thealgorithms.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class HierholzerAlgorithmTest {

    @Test
    public void testFindsEulerianCircuitInSimpleTriangleGraph() {
        Map<Integer, LinkedList<Integer>> graph = new HashMap<>();
        graph.put(0, new LinkedList<>(Arrays.asList(1, 2)));
        graph.put(1, new LinkedList<>(Arrays.asList(0, 2)));
        graph.put(2, new LinkedList<>(Arrays.asList(0, 1)));
        HierholzerAlgorithm algorithm = new HierholzerAlgorithm(graph);
        assertTrue(algorithm.hasEulerianCircuit());
        List<Integer> circuit = algorithm.findEulerianCircuit();
        assertEquals(4, circuit.size());
        assertEquals(circuit.get(0), circuit.get(circuit.size() - 1));
    }

    @Test
    public void testFailsForGraphWithOddDegreeVertices() {
        Map<Integer, LinkedList<Integer>> graph = new HashMap<>();
        graph.put(0, new LinkedList<>(Collections.singletonList(1)));
        graph.put(1, new LinkedList<>(Collections.singletonList(0)));
        HierholzerAlgorithm algorithm = new HierholzerAlgorithm(graph);
        assertFalse(algorithm.hasEulerianCircuit());
        assertTrue(algorithm.findEulerianCircuit().isEmpty());
    }

    @Test
    public void testFailsForDisconnectedGraph() {
        Map<Integer, LinkedList<Integer>> graph = new HashMap<>();
        graph.put(0, new LinkedList<>(Arrays.asList(1, 2)));
        graph.put(1, new LinkedList<>(Arrays.asList(0, 2)));
        graph.put(2, new LinkedList<>(Arrays.asList(0, 1)));
        graph.put(3, new LinkedList<>(Arrays.asList(4, 5)));
        graph.put(4, new LinkedList<>(Arrays.asList(3, 5)));
        graph.put(5, new LinkedList<>(Arrays.asList(3, 4)));
        HierholzerAlgorithm algorithm = new HierholzerAlgorithm(graph);
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
