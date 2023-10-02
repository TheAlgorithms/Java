package com.thealgorithms.greedyalgorithms;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class DijkstrasAlgorithmTest {
    @Test
    public void testDijkstraShortestPath() {
        int[][] graph = {{0, 2, 4, 0, 0, 0}, {0, 0, 1, 7, 0, 0}, {0, 0, 0, 0, 3, 0}, {0, 0, 0, 0, 0, 1}, {0, 0, 0, 2, 0, 5}, {0, 0, 0, 0, 0, 0}};

        int src = 0;
        int[] expectedDistances = {0, 2, 3, 8, 6, 9};

        int[] shortestDistances = DijkstrasAlgorithm.dijkstra(graph, src);
        assertArrayEquals(expectedDistances, shortestDistances);
    }
    @Test
    public void testDijkstraNoPath() {
        int[][] graph = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};

        int src = 0;
        int[] expectedDistances = {0, Integer.MAX_VALUE, Integer.MAX_VALUE};

        int[] shortestDistances = DijkstrasAlgorithm.dijkstra(graph, src);
        assertArrayEquals(expectedDistances, shortestDistances);
    }

    @Test
    public void testDijkstraSingleVertex() {
        int[][] graph = {{0}};

        int src = 0;
        int[] expectedDistances = {0};

        int[] shortestDistances = DijkstrasAlgorithm.dijkstra(graph, src);
        assertArrayEquals(expectedDistances, shortestDistances);
    }
}
