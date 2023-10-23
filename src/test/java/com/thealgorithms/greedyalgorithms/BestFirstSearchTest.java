package com.thealgorithms.greedyalgorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.*;
import org.junit.jupiter.api.Test;

public class BestFirstSearchTest {
    @Test
    public void testBestFirstSearchTargetNotFound() {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(1, Arrays.asList(2, 3));
        graph.put(2, Arrays.asList(4));
        graph.put(3, Arrays.asList(5, 6));
        graph.put(4, Arrays.asList(7));
        graph.put(5, Arrays.asList(8));
        graph.put(6, Arrays.asList(9));

        Map<Integer, Integer> heuristic = new HashMap<>();
        heuristic.put(1, 8);
        heuristic.put(2, 6);
        heuristic.put(3, 5);
        heuristic.put(4, 4);
        heuristic.put(5, 3);
        heuristic.put(6, 2);
        heuristic.put(7, 1);
        heuristic.put(8, 0);
        heuristic.put(9, 0);

        Node result = BestFirstSearch.bestFirstSearch(1, 10, graph, heuristic);

        assertNull(result);
    }

    @Test
    public void testBestFirstSearchEmptyGraph() {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> heuristic = new HashMap<>();

        assertNull(BestFirstSearch.bestFirstSearch(1, 2, graph, heuristic));
    }

    @Test
    public void testBestFirstSearchTargetFound() {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(1, Arrays.asList(2, 3));
        graph.put(2, Arrays.asList(4));
        graph.put(3, Arrays.asList(5, 6));
        graph.put(4, Arrays.asList(7));
        graph.put(5, Arrays.asList(8));
        graph.put(6, Arrays.asList(9));

        Map<Integer, Integer> heuristic = new HashMap<>();
        heuristic.put(1, 8);
        heuristic.put(2, 6);
        heuristic.put(3, 5);
        heuristic.put(4, 4);
        heuristic.put(5, 3);
        heuristic.put(6, 2);
        heuristic.put(7, 1);
        heuristic.put(8, 0);
        heuristic.put(9, 0);

        Node result = BestFirstSearch.bestFirstSearch(1, 8, graph, heuristic);

        assertEquals(8, result.value);
    }
}
