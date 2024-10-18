package com.thealgorithms.backtracking;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

class DijkstraTest {

    @Test
    void testSingleNodeGraph() {
        Graph graph = new Graph();
        graph.addEdge(1, 1, 0);

        Dijkstra dijkstra = new Dijkstra();
        Map<Integer, Integer> distances = dijkstra.findShortestPaths(graph, 1);

        assertEquals(0, (int) distances.get(1));
    }

    @Test
    void testSimpleGraph() {
        Graph graph = new Graph();
        graph.addEdge(1, 2, 1);
        graph.addEdge(2, 3, 2);
        graph.addEdge(1, 3, 4);

        Dijkstra dijkstra = new Dijkstra();
        Map<Integer, Integer> distances = dijkstra.findShortestPaths(graph, 1);

        assertEquals(0, (int) distances.get(1));
        assertEquals(1, (int) distances.get(2));
        assertEquals(3, (int) distances.get(3));
    }

    @Test
    void testComplexGraph() {
        Graph graph = new Graph();
        graph.addEdge(1, 2, 10);
        graph.addEdge(1, 3, 5);
        graph.addEdge(3, 2, 3);
        graph.addEdge(2, 4, 1);
        graph.addEdge(3, 4, 9);
        graph.addEdge(4, 5, 2);

        Dijkstra dijkstra = new Dijkstra();
        Map<Integer, Integer> distances = dijkstra.findShortestPaths(graph, 1);

        assertEquals(0, (int) distances.get(1));
        assertEquals(8, (int) distances.get(2));
        assertEquals(5, (int) distances.get(3));
        assertEquals(9, (int) distances.get(4));
        assertEquals(11, (int) distances.get(5));
    }

    @Test
    void testDisconnectedGraph() {
        Graph graph = new Graph();
        graph.addEdge(1, 2, 5);
        graph.addEdge(3, 4, 7);

        Dijkstra dijkstra = new Dijkstra();
        Map<Integer, Integer> distances = dijkstra.findShortestPaths(graph, 1);

        assertEquals(0, (int) distances.get(1));
        assertEquals(5, (int) distances.get(2));
        assertNull(distances.get(3)); 
    }

    @Test
    void testGraphWithLoops() {
        Graph graph = new Graph();
        graph.addEdge(1, 1, 0);  
        graph.addEdge(1, 2, 2);
        graph.addEdge(2, 3, 4);
        graph.addEdge(3, 1, 6);

        Dijkstra dijkstra = new Dijkstra();
        Map<Integer, Integer> distances = dijkstra.findShortestPaths(graph, 1);

        assertEquals(0, (int) distances.get(1));
        assertEquals(2, (int) distances.get(2));
        assertEquals(6, (int) distances.get(3));
    }
}
