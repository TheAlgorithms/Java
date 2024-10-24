package com.thealgorithms.datastructures.graphs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class TarjansAlgorithmTest {

    private final TarjansAlgorithm tarjansAlgo = new TarjansAlgorithm();

    @Test
    public void testFindStronglyConnectedComponents() {
        int v = 5;
        var graph = new ArrayList<List<Integer>>();
        for (int i = 0; i < v; i++) {
            graph.add(new ArrayList<>());
        }
        graph.get(0).add(1);
        graph.get(1).add(2);
        graph.get(2).add(0);
        graph.get(1).add(3);
        graph.get(3).add(4);

        var actualResult = tarjansAlgo.stronglyConnectedComponents(v, graph);
        /*
            Expected result:
            0, 1, 2
            3
            4
        */
        List<List<Integer>> expectedResult = new ArrayList<>();
        expectedResult.add(List.of(4));
        expectedResult.add(List.of(3));
        expectedResult.add(Arrays.asList(2, 1, 0));
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testFindStronglyConnectedComponentsWithSingleNodes() {
        // Create a graph where each node is its own SCC
        int n = 8;
        var adjList = new ArrayList<List<Integer>>(n);
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }
        adjList.get(0).add(1);
        adjList.get(1).add(2);
        adjList.get(2).add(3);
        adjList.get(3).add(4);
        adjList.get(4).add(5);
        adjList.get(5).add(6);
        adjList.get(6).add(7);
        adjList.get(7).add(0);

        List<List<Integer>> actualResult = tarjansAlgo.stronglyConnectedComponents(n, adjList);
        List<List<Integer>> expectedResult = new ArrayList<>();
        /*
            Expected result:
            7, 6, 5, 4, 3, 2, 1, 0
        */
        expectedResult.add(Arrays.asList(7, 6, 5, 4, 3, 2, 1, 0));
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testGraphWithMultipleSCCs() {
        int v = 6;
        var graph = new ArrayList<List<Integer>>();
        for (int i = 0; i < v; i++) {
            graph.add(new ArrayList<>());
        }
        graph.get(0).add(1);
        graph.get(1).add(2);
        graph.get(2).add(0);
        graph.get(3).add(4);
        graph.get(4).add(5);
        graph.get(5).add(3);

        var actualResult = tarjansAlgo.stronglyConnectedComponents(v, graph);
        List<List<Integer>> expectedResult = new ArrayList<>();
        expectedResult.add(Arrays.asList(2, 1, 0)); // SCC containing 0, 1, 2
        expectedResult.add(Arrays.asList(5, 4, 3)); // SCC containing 3, 4, 5
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testDisconnectedGraph() {
        int v = 7;
        var graph = new ArrayList<List<Integer>>();
        for (int i = 0; i < v; i++) {
            graph.add(new ArrayList<>());
        }
        graph.get(0).add(1);
        graph.get(1).add(0);
        graph.get(2).add(3);
        graph.get(3).add(4);
        graph.get(4).add(2);

        var actualResult = tarjansAlgo.stronglyConnectedComponents(v, graph);
        List<List<Integer>> expectedResult = new ArrayList<>();
        expectedResult.add(Arrays.asList(1, 0)); // SCC containing 0, 1
        expectedResult.add(Arrays.asList(4, 3, 2)); // SCC containing 2, 3, 4
        expectedResult.add(List.of(5)); // SCC containing 5
        expectedResult.add(List.of(6)); // SCC containing 6
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testSingleNodeGraph() {
        int v = 1;
        var graph = new ArrayList<List<Integer>>();
        graph.add(new ArrayList<>());

        var actualResult = tarjansAlgo.stronglyConnectedComponents(v, graph);
        List<List<Integer>> expectedResult = new ArrayList<>();
        expectedResult.add(List.of(0)); // SCC with a single node
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testEmptyGraph() {
        int v = 0;
        var graph = new ArrayList<List<Integer>>();

        var actualResult = tarjansAlgo.stronglyConnectedComponents(v, graph);
        List<List<Integer>> expectedResult = new ArrayList<>(); // No SCCs in an empty graph
        assertEquals(expectedResult, actualResult);
    }
}
