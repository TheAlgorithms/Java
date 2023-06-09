package com.thealgorithms.datastructures.graphs;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class TarjansAlgorithmTest {

    TarjansAlgorithm tarjansAlgo = new TarjansAlgorithm();

    @Test
    public void findStronglyConnectedComps() {
        var v = 5;
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

        expectedResult.add(Arrays.asList(4));
        expectedResult.add(Arrays.asList(3));
        expectedResult.add(Arrays.asList(2, 1, 0));
        assertTrue(expectedResult.equals(actualResult));
    }

    @Test
    public void findStronglyConnectedCompsShouldGetSingleNodes() {
        // Create a adjacency list of graph
        var n = 8;
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
        assertTrue(expectedResult.equals(actualResult));
    }
}
