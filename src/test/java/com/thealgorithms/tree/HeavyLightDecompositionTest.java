package com.thealgorithms.tree;

/**
 * Testcases for Heavy-Light Decomposition (HLD) implementation in Java.
 * 
 * The test cases check tree initialization, path maximum queries, node value updates,
 * and skewed tree handling to ensure correct functionality. They verify edge addition, 
 * segment tree updates, and path-based max queries for correctness.
 * 
 * Author: Nithin U.
 * Github: https://github.com/NithinU2802
 * 
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HeavyLightDecompositionTest {

    private HeavyLightDecomposition hld;
    private int[] values;

    /**
     * Initializes the test environment with a predefined tree structure and values.
     */
    @BeforeEach
    void setUp() {
        hld = new HeavyLightDecomposition(5);
        hld.addEdge(1, 2);
        hld.addEdge(1, 3);
        hld.addEdge(2, 4);
        hld.addEdge(2, 5);

        // Single array initialization for all test cases
        values = new int[]{0, 10, 20, 30, 40, 50}; 
        hld.initialize(1, values);
    }

    /**
     * Tests the basic initialization of the tree structure.
     * Expected: The tree should initialize without errors.
     */
    @Test
    void testBasicTreeInitialization() {
        assertTrue(true, "Basic tree structure initialized successfully");
    }

    /**
     * Tests the maximum value query in a path between nodes.
     * Expected: The max value in the path (4,5) should be 50.
     * Expected: The max value in the path (3,2) should be 30.
     */
    @Test
    void testQueryMaxInPath() {
        assertEquals(50, hld.queryMaxInPath(4, 5), "Max value in path should be 50");
        assertEquals(30, hld.queryMaxInPath(3, 2), "Max value in path should be 30");
    }

    /**
     * Tests updating a node's value and ensuring it's reflected in queries.
     * Expected: The updated node's value should affect query results.
     */
    @Test
    void testUpdateNodeValue() {
        hld.updateSegmentTree(1, 0, hld.getPositionIndex() - 1, hld.getPosition(4), 100);
        assertEquals(100, hld.queryMaxInPath(4, 5), "Updated value should be reflected in query");
    }

    /**
     * Tests a skewed tree structure to ensure max path queries work correctly.
     * Expected: The max value in the path (1,4) should be 35.
     */
    @Test
    void testSkewedTreeMaxQuery() {
        hld = new HeavyLightDecomposition(4);
        hld.addEdge(1, 2);
        hld.addEdge(2, 3);
        hld.addEdge(3, 4);
        values = new int[]{0, 5, 15, 25, 35}; // Adjusted values for the skewed tree
        hld.initialize(1, values);
        
        assertEquals(35, hld.queryMaxInPath(1, 4), "Max value in skewed tree should be 35");
    }
    
    /**
     * Tests a skewed tree structure to ensure max path queries work correctly.
     * Expected: When called with u as a deeper node, it should swap correctly.
     */
    @Test
    void testDepthSwapInPathQuery() {
        assertEquals(50, hld.queryMaxInPath(5, 2), "Query should handle depth swap correctly");
        assertEquals(40, hld.queryMaxInPath(4, 1), "Query handle swap nodes and return max value");
    }
}
