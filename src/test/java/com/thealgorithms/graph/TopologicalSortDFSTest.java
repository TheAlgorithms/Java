package com.thealgorithms.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link TopologicalSortDFS}.
 */
public final class TopologicalSortDFSTest {

    @Test
    void testSimpleGraph() {
        int vertices = 6;
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        adjacencyList.get(5).add(2);
        adjacencyList.get(5).add(0);
        adjacencyList.get(4).add(0);
        adjacencyList.get(4).add(1);
        adjacencyList.get(2).add(3);
        adjacencyList.get(3).add(1);

        List<Integer> result = TopologicalSortDFS.topologicalSort(vertices, adjacencyList);

        // A valid topological order is one of the possible ones
        List<Integer> expected = Arrays.asList(5, 4, 2, 3, 1, 0);
        Assertions.assertTrue(result.containsAll(expected) && expected.containsAll(result));
    }

    @Test
    void testEmptyGraph() {
        int vertices = 0;
        List<List<Integer>> adjacencyList = new ArrayList<>();
        List<Integer> result = TopologicalSortDFS.topologicalSort(vertices, adjacencyList);
        Assertions.assertTrue(result.isEmpty());
    }
}
