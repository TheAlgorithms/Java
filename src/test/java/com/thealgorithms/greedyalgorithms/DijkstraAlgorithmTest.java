package com.thealgorithms.greedyalgorithms;

import static com.thealgorithms.greedyalgorithms.DijkstraAlgorithm.Graph;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


/**
 * Test class for {@code DijkstraAlgorithm}.
 */
class DijkstraAlgorithmTest {

    @Test
    void testDijkstraAlgorithmSmallGraph() {
        Graph smallTestGraph = new Graph(4);
        smallTestGraph.addEdge(0, 1, 1);
        smallTestGraph.addEdge(1, 2, 1);
        smallTestGraph.addEdge(2, 3, 1);
        smallTestGraph.addEdge(0, 3, 3);

        int[] distances = smallTestGraph.dijkstraGetMinDistances(0);
        int[] expected = new int[] {0, 1, 2, 3};
        assertArrayEquals(expected, distances);
    }

    @Test
    void testDijkstraAlgorithmMediumGraph() {
        Graph mediumTestGraph = new Graph(6);
        mediumTestGraph.addEdge(0, 1, 7);
        mediumTestGraph.addEdge(0, 2, 9);
        mediumTestGraph.addEdge(0, 5, 14);
        mediumTestGraph.addEdge(1, 2, 10);
        mediumTestGraph.addEdge(1, 3, 15);
        mediumTestGraph.addEdge(2, 3, 11);
        mediumTestGraph.addEdge(2, 5, 2);
        mediumTestGraph.addEdge(3, 4, 6);
        mediumTestGraph.addEdge(4, 5, 9);

        int[] distances = mediumTestGraph.dijkstraGetMinDistances(0);
        int[] expected = {0, 7, 9, 20, 20, 11}; // Expected shortest distances from vertex 0
        assertArrayEquals(expected, distances);
    }

    @Test
    void testDijkstraAlgorithmEmptyGraph() {
        Graph emptyGraph = new Graph(5); // No edges added

        int[] distances = emptyGraph.dijkstraGetMinDistances(0);
        int[] expected = new int[] {0, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
        assertArrayEquals(expected, distances);
    }

    @Test
    void testDijkstraAlgorithmDisconnectedGraph() {
        Graph disconnectedGraph = new Graph(5);
        disconnectedGraph.addEdge(0, 1, 10);
        // No edges for vertices 2, 3, 4

        int[] distances = disconnectedGraph.dijkstraGetMinDistances(0);
        int[] expected = new int[] {0, 10, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
        assertArrayEquals(expected, distances, "Unconnected vertices should have MAX_VALUE distance");
    }

    @Test
    void testDijkstraAlgorithmWithGraphStructureChange() {
        Graph graph = new Graph(5);
        graph.addEdge(0, 1, 2);
        graph.addEdge(1, 2, 3);

        int[] distancesBefore = graph.dijkstraGetMinDistances(0);
        int[] expectedBefore = {0, 2, 5, Integer.MAX_VALUE, Integer.MAX_VALUE};
        assertArrayEquals(expectedBefore, distancesBefore);

        // Add a new edge and expect the algorithm to update distances accordingly
        graph.addEdge(0, 3, 1);
        graph.addEdge(3, 4, 1);
        int[] distancesAfter = graph.dijkstraGetMinDistances(0);
        int[] expectedAfter = {0, 2, 5, 1, 2}; // The new shortest path to 4 is through the new edge
        assertArrayEquals(expectedAfter, distancesAfter);
    }
}
