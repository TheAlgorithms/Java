package com.thealgorithms.datastructures.graphs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class KosarajuTest {

    private final Kosaraju kosaraju = new Kosaraju();

    @Test
    public void testFindStronglyConnectedComponents() {
        // Create a graph using adjacency list
        int n = 8;
        List<List<Integer>> adjList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        adjList.get(0).add(1);
        adjList.get(1).add(2);
        adjList.get(2).add(0);
        adjList.get(2).add(3);
        adjList.get(3).add(4);
        adjList.get(4).add(5);
        adjList.get(4).add(7);
        adjList.get(5).add(6);
        adjList.get(6).add(4);
        adjList.get(6).add(7);

        List<List<Integer>> actualResult = kosaraju.kosaraju(n, adjList);
        List<List<Integer>> expectedResult = new ArrayList<>();
        /*
            Expected result:
            {0, 1, 2}
            {3}
            {5, 4, 6}
            {7}
        */
        expectedResult.add(Arrays.asList(1, 2, 0));
        expectedResult.add(List.of(3));
        expectedResult.add(Arrays.asList(5, 6, 4));
        expectedResult.add(List.of(7));

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testFindSingleNodeSCC() {
        // Create a simple graph using adjacency list
        int n = 8;
        List<List<Integer>> adjList = new ArrayList<>(n);
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

        List<List<Integer>> actualResult = kosaraju.kosaraju(n, adjList);
        List<List<Integer>> expectedResult = new ArrayList<>();
        /*
            Expected result:
            {0, 1, 2, 3, 4, 5, 6, 7}
        */
        expectedResult.add(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 0));

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testDisconnectedGraph() {
        // Create a disconnected graph (two separate components)
        int n = 5;
        List<List<Integer>> adjList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        // Add edges for first component
        adjList.get(0).add(1);
        adjList.get(1).add(2);
        adjList.get(2).add(0);

        // Add edges for second component
        adjList.get(3).add(4);
        adjList.get(4).add(3);

        List<List<Integer>> actualResult = kosaraju.kosaraju(n, adjList);

        List<List<Integer>> expectedResult = new ArrayList<>();
        /*
            Expected result:
            {0, 1, 2}
            {3, 4}
        */
        expectedResult.add(Arrays.asList(4, 3));
        expectedResult.add(Arrays.asList(1, 2, 0));

        assertEquals(expectedResult, actualResult);
    }
}
