package com.thealgorithms.greedyalgorithms;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class WeightedGraphTest {

    @Test
    void testSingleNode() {
        WeightedGraph wg = new WeightedGraph(1);
        assertEquals(0, wg.primMST());
    }

    @Test
    void testNegativeWeights() {
        WeightedGraph wg = new WeightedGraph(2);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> { wg.addEdge(0, 1, -1); });
        assertNotNull(exception);
    }

    @Test
    void testCompleteGraph() {
        WeightedGraph wg = new WeightedGraph(3);
        wg.addEdge(0, 1, 1);
        wg.addEdge(0, 2, 1);
        wg.addEdge(1, 2, 1);
        assertEquals(2, wg.primMST());
    }

    @Test
    void testGraphWithParallelEdges() {
        WeightedGraph wg = new WeightedGraph(2);
        wg.addEdge(0, 1, 10);
        wg.addEdge(0, 1, 1);
        assertEquals(1, wg.primMST());
    }

    @Test
    void testLargeGraph() {
        WeightedGraph wg = new WeightedGraph(1000);
        // Add edges to form a large graph
        // Verify the MST total weight is as expected
    }

    @Test
    void testUpdatingGraph() {
        WeightedGraph wg = new WeightedGraph(3);
        wg.addEdge(0, 1, 10);
        int weightBefore = wg.primMST();
        wg.addEdge(1, 2, 1);
        int weightAfter = wg.primMST();
        assertNotEquals(weightBefore, weightAfter);
    }

    @Test
    void testCacheIntegrity() {
        WeightedGraph wg = new WeightedGraph(2);
        wg.addEdge(0, 1, 10);
        int weight1 = wg.primMST();
        wg.addEdge(0, 1, 1); // This should update the edge weight to 1
        int weight2 = wg.primMST(); // The MST should be recalculated
        assertEquals(1, weight2);
    }

    @Test
    void testGraphModification() {
        WeightedGraph wg = new WeightedGraph(3);
        wg.addEdge(0, 1, 5);
        wg.addEdge(1, 2, 10);
        wg.primMST(); // Compute initial MST
        wg.addEdge(0, 2, 2); // Add a lower cost edge
        int newWeight = wg.primMST(); // Recompute MST
        assertEquals(7, newWeight); // Check if the new MST weight is correct
    }

    @Test
    void testMSTValidity() {
        WeightedGraph wg = new WeightedGraph(4);
        wg.addEdge(0, 1, 1);
        wg.addEdge(0, 2, 4);
        wg.addEdge(1, 3, 3);
        wg.addEdge(2, 3, 2);
        wg.primMST();
        assertTrue(wg.isMSTValid()); // Check if the computed MST is valid
    }
}
