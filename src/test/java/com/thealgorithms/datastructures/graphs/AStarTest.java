package com.thealgorithms.datastructures.graphs;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AStarTest {

    private AStar.Graph graph;
    private int[] heuristic;

    @BeforeEach
    public void setUp() {
        // Initialize graph and heuristic values for testing
        graph = new AStar.Graph(5);
        ArrayList<Integer> graphData = new ArrayList<>(Arrays.asList(0, 1, 1, null, 0, 2, 2, null, 1, 3, 1, null, 2, 3, 1, null, 3, 4, 1, null));
        AStar.initializeGraph(graph, graphData);

        heuristic = new int[] {5, 4, 3, 2, 0}; // Heuristic values for each node
    }

    @Test
    public void testAStarFindsPath() {
        AStar.PathAndDistance result = AStar.aStar(0, 4, graph, heuristic);
        assertEquals(3, result.getDistance(), "Expected distance from 0 to 4 is 3");
        assertEquals(Arrays.asList(0, 1, 3, 4), result.getPath(), "Expected path from 0 to 4");
    }

    @Test
    public void testAStarPathNotFound() {
        AStar.PathAndDistance result = AStar.aStar(0, 5, graph, heuristic); // Node 5 does not exist
        assertEquals(-1, result.getDistance(), "Expected distance when path not found is -1");
        assertNull(result.getPath(), "Expected path should be null when no path exists");
    }

    @Test
    public void testAStarSameNode() {
        AStar.PathAndDistance result = AStar.aStar(0, 0, graph, heuristic);
        assertEquals(0, result.getDistance(), "Expected distance from 0 to 0 is 0");
        assertEquals(singletonList(0), result.getPath(), "Expected path should only contain the start node");
    }
}
