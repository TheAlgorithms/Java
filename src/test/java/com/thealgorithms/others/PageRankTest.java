package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Test class for PageRank algorithm implementation
 *
 * @author Hardvan
 */
class PageRankTest {

    private static final double EPSILON = 0.0001; // Tolerance for floating point comparisons

    /**
     * Test basic PageRank calculation with a simple 3-node graph
     * Graph: 1 -> 2, 2 -> 3, 3 -> 1
     */
    @Test
    void testSimpleThreeNodeGraph() {
        PageRank pageRank = new PageRank(3);

        // Create a simple circular graph: 1 -> 2 -> 3 -> 1
        int[][] adjacencyMatrix = new int[10][10];
        adjacencyMatrix[1][2] = 1; // Node 1 links to Node 2
        adjacencyMatrix[2][3] = 1; // Node 2 links to Node 3
        adjacencyMatrix[3][1] = 1; // Node 3 links to Node 1

        pageRank.setAdjacencyMatrix(adjacencyMatrix);
        double[] result = pageRank.calculatePageRank(3);

        // All nodes should have equal PageRank in a circular graph
        assertNotNull(result);
        assertEquals(result[1], result[2], EPSILON);
        assertEquals(result[2], result[3], EPSILON);
    }

    /**
     * Test PageRank with a two-node graph where one node points to another
     */
    @Test
    void testTwoNodeGraph() {
        PageRank pageRank = new PageRank(2);

        // Node 1 links to Node 2
        pageRank.setEdge(1, 2, 1);

        double[] result = pageRank.calculatePageRank(2);

        // Node 2 should have higher PageRank than Node 1 (after 2 iterations)
        assertNotNull(result);
        assertEquals(0.2775, result[2], EPSILON);
        assertEquals(0.15, result[1], EPSILON);
    }

    /**
     * Test PageRank with a single node (no links)
     */
    @Test
    void testSingleNode() {
        PageRank pageRank = new PageRank(1);

        double[] result = pageRank.calculatePageRank(1);

        // Single node should have (1-d) = 0.15 after applying damping
        assertNotNull(result);
        assertEquals(0.15, result[1], EPSILON);
    }

    /**
     * Test PageRank with a hub-and-spoke configuration
     * Node 1 is the hub, pointing to nodes 2, 3, and 4
     */
    @Test
    void testHubAndSpokeGraph() {
        PageRank pageRank = new PageRank(4);

        // Hub node (1) links to all other nodes
        pageRank.setEdge(1, 2, 1);
        pageRank.setEdge(1, 3, 1);
        pageRank.setEdge(1, 4, 1);

        // All spokes link back to hub
        pageRank.setEdge(2, 1, 1);
        pageRank.setEdge(3, 1, 1);
        pageRank.setEdge(4, 1, 1);

        double[] result = pageRank.calculatePageRank(4);

        assertNotNull(result);
        // Hub should have higher PageRank
        assertEquals(result[2], result[3], EPSILON);
        assertEquals(result[3], result[4], EPSILON);
    }

    /**
     * Test PageRank with multiple iterations
     */
    @Test
    void testMultipleIterations() {
        PageRank pageRank = new PageRank(3);

        pageRank.setEdge(1, 2, 1);
        pageRank.setEdge(2, 3, 1);
        pageRank.setEdge(3, 1, 1);

        double[] result2Iterations = pageRank.calculatePageRank(3, 0.85, 2, false);
        double[] result5Iterations = pageRank.calculatePageRank(3, 0.85, 5, false);

        assertNotNull(result2Iterations);
        assertNotNull(result5Iterations);
    }

    /**
     * Test getPageRank method for individual node
     */
    @Test
    void testGetPageRank() {
        PageRank pageRank = new PageRank(2);

        pageRank.setEdge(1, 2, 1);
        pageRank.calculatePageRank(2);

        double node1PageRank = pageRank.getPageRank(1);
        double node2PageRank = pageRank.getPageRank(2);

        assertEquals(0.15, node1PageRank, EPSILON);
        assertEquals(0.2775, node2PageRank, EPSILON);
    }

    /**
     * Test getAllPageRanks method
     */
    @Test
    void testGetAllPageRanks() {
        PageRank pageRank = new PageRank(2);

        pageRank.setEdge(1, 2, 1);
        pageRank.calculatePageRank(2);

        double[] allPageRanks = pageRank.getAllPageRanks();

        assertNotNull(allPageRanks);
        assertEquals(0.15, allPageRanks[1], EPSILON);
        assertEquals(0.2775, allPageRanks[2], EPSILON);
    }

    /**
     * Test that self-loops are not allowed
     */
    @Test
    void testNoSelfLoops() {
        PageRank pageRank = new PageRank(2);

        // Try to set a self-loop
        pageRank.setEdge(1, 1, 1);
        pageRank.setEdge(1, 2, 1);

        double[] result = pageRank.calculatePageRank(2);

        assertNotNull(result);
        // Self-loop should be ignored
    }

    /**
     * Test exception when node count is too small
     */
    @Test
    void testInvalidNodeCountTooSmall() {
        assertThrows(IllegalArgumentException.class, () -> new PageRank(0));
    }

    /**
     * Test exception when node count is too large
     */
    @Test
    void testInvalidNodeCountTooLarge() {
        assertThrows(IllegalArgumentException.class, () -> new PageRank(11));
    }

    /**
     * Test exception for invalid damping factor (negative)
     */
    @Test
    void testInvalidDampingFactorNegative() {
        PageRank pageRank = new PageRank(2);
        pageRank.setEdge(1, 2, 1);

        assertThrows(IllegalArgumentException.class, () -> pageRank.calculatePageRank(2, -0.1, 2, false));
    }

    /**
     * Test exception for invalid damping factor (greater than 1)
     */
    @Test
    void testInvalidDampingFactorTooLarge() {
        PageRank pageRank = new PageRank(2);
        pageRank.setEdge(1, 2, 1);

        assertThrows(IllegalArgumentException.class, () -> pageRank.calculatePageRank(2, 1.5, 2, false));
    }

    /**
     * Test exception for invalid iterations (less than 1)
     */
    @Test
    void testInvalidIterations() {
        PageRank pageRank = new PageRank(2);
        pageRank.setEdge(1, 2, 1);

        assertThrows(IllegalArgumentException.class, () -> pageRank.calculatePageRank(2, 0.85, 0, false));
    }

    /**
     * Test exception when getting PageRank for invalid node
     */
    @Test
    void testGetPageRankInvalidNode() {
        PageRank pageRank = new PageRank(2);
        pageRank.calculatePageRank(2);

        assertThrows(IllegalArgumentException.class, () -> pageRank.getPageRank(3));
    }

    /**
     * Test exception when getting PageRank for node less than 1
     */
    @Test
    void testGetPageRankNodeLessThanOne() {
        PageRank pageRank = new PageRank(2);
        pageRank.calculatePageRank(2);

        assertThrows(IllegalArgumentException.class, () -> pageRank.getPageRank(0));
    }

    /**
     * Test complex graph with multiple incoming and outgoing links
     */
    @Test
    void testComplexGraph() {
        PageRank pageRank = new PageRank(4);

        // Create a more complex graph
        pageRank.setEdge(1, 2, 1);
        pageRank.setEdge(1, 3, 1);
        pageRank.setEdge(2, 3, 1);
        pageRank.setEdge(3, 4, 1);
        pageRank.setEdge(4, 1, 1);

        double[] result = pageRank.calculatePageRank(4);

        assertNotNull(result);
        // Node 3 should have high PageRank (receives links from nodes 1 and 2)
        // After 2 iterations, the sum will not equal total nodes
        double sum = result[1] + result[2] + result[3] + result[4];
        assertEquals(1.8325, sum, EPSILON);
    }

    /**
     * Test that PageRank values sum after 2 iterations
     */
    @Test
    void testPageRankSum() {
        PageRank pageRank = new PageRank(5);

        // Create arbitrary graph
        pageRank.setEdge(1, 2, 1);
        pageRank.setEdge(2, 3, 1);
        pageRank.setEdge(3, 4, 1);
        pageRank.setEdge(4, 5, 1);
        pageRank.setEdge(5, 1, 1);

        double[] result = pageRank.calculatePageRank(5);

        double sum = 0;
        for (int i = 1; i <= 5; i++) {
            sum += result[i];
        }

        // Sum after 2 iterations with default damping factor
        assertEquals(2.11, sum, EPSILON);
    }

    /**
     * Test graph with isolated node (no incoming or outgoing links)
     */
    @Test
    void testGraphWithIsolatedNode() {
        PageRank pageRank = new PageRank(3);

        // Node 1 and 2 are connected, Node 3 is isolated
        pageRank.setEdge(1, 2, 1);
        pageRank.setEdge(2, 1, 1);

        double[] result = pageRank.calculatePageRank(3);

        assertNotNull(result);
        // Isolated node should have some PageRank due to damping factor
        assertEquals(0.15, result[3], EPSILON);
    }

    /**
     * Test verbose mode (should not throw exception)
     */
    @Test
    void testVerboseMode() {
        PageRank pageRank = new PageRank(2);

        pageRank.setEdge(1, 2, 1);

        // This should execute without throwing an exception
        double[] result = pageRank.calculatePageRank(2, 0.85, 2, true);

        assertNotNull(result);
    }
}
