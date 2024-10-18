package com.thealgorithms.backtracking;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class AStarAlgorithmTest {

    @Test
    public void testBasicGraphWithManhattanHeuristic() {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 1);
        graph.addEdge(2, 3, 1);
        graph.addEdge(0, 3, 10);

        int[] xCoords = {0, 1, 2, 3};  
        int[] yCoords = {0, 0, 0, 0}; 

        Heuristic manhattanHeuristic = new ManhattanHeuristic(xCoords, yCoords);
        AStar aStar = new AStar(graph, manhattanHeuristic);

        List<Integer> path = aStar.findShortestPath(0, 3);

        List<Integer> expectedPath = Arrays.asList(0, 1, 2, 3);
        assertEquals(expectedPath, path);
    }

    @Test
    public void testBasicGraphWithEuclideanHeuristic() {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 2);
        graph.addEdge(1, 2, 2);
        graph.addEdge(2, 3, 2);
        graph.addEdge(0, 3, 5);

        int[] xCoords = {0, 2, 4, 6};  
        int[] yCoords = {0, 0, 0, 0}; 

        Heuristic euclideanHeuristic = new EuclideanHeuristic(xCoords, yCoords);
        AStar aStar = new AStar(graph, euclideanHeuristic);

        List<Integer> path = aStar.findShortestPath(0, 3);

        List<Integer> expectedPath = Arrays.asList(0, 3);
        assertEquals(expectedPath, path);
    }

    @Test
    public void testDisconnectedGraph() {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 2);
        graph.addEdge(1, 2, 2);

        int[] xCoords = {0, 2, 4, 6};
        int[] yCoords = {0, 0, 0, 0};

        Heuristic manhattanHeuristic = new ManhattanHeuristic(xCoords, yCoords);
        AStar aStar = new AStar(graph, manhattanHeuristic);

        List<Integer> path = aStar.findShortestPath(0, 3);
        
        assertTrue(path.isEmpty());
    }
}
