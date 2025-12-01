package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Test cases for CentroidDecomposition
 *
 * @author lens161
 */
class CentroidDecompositionTest {

    @Test
    void testSingleNode() {
        // Tree with just one node
        int[][] edges = {};
        CentroidDecomposition.CentroidTree tree = CentroidDecomposition.buildFromEdges(1, edges);

        assertEquals(1, tree.size());
        assertEquals(0, tree.getRoot());
        assertEquals(-1, tree.getParent(0));
    }

    @Test
    void testTwoNodes() {
        // Simple tree: 0 - 1
        int[][] edges = {{0, 1}};
        CentroidDecomposition.CentroidTree tree = CentroidDecomposition.buildFromEdges(2, edges);

        assertEquals(2, tree.size());
        int root = tree.getRoot();
        assertTrue(root == 0 || root == 1, "Root should be either node 0 or 1");

        // One node should be root, other should have the root as parent
        int nonRoot = (root == 0) ? 1 : 0;
        assertEquals(-1, tree.getParent(root));
        assertEquals(root, tree.getParent(nonRoot));
    }

    @Test
    void testLinearTree() {
        // Linear tree: 0 - 1 - 2 - 3 - 4
        int[][] edges = {{0, 1}, {1, 2}, {2, 3}, {3, 4}};
        CentroidDecomposition.CentroidTree tree = CentroidDecomposition.buildFromEdges(5, edges);

        assertEquals(5, tree.size());
        // For a linear tree of 5 nodes, the centroid should be the middle node (node 2)
        assertEquals(2, tree.getRoot());
        assertEquals(-1, tree.getParent(2));
    }

    @Test
    void testBalancedBinaryTree() {
        // Balanced binary tree:
        //       0
        //      / \
        //     1   2
        //    / \
        //   3   4
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {1, 4}};
        CentroidDecomposition.CentroidTree tree = CentroidDecomposition.buildFromEdges(5, edges);

        assertEquals(5, tree.size());
        // Root should be 0 or 1 (both are valid centroids)
        int root = tree.getRoot();
        assertTrue(root == 0 || root == 1);
        assertEquals(-1, tree.getParent(root));

        // All nodes should have a parent in centroid tree except root
        for (int i = 0; i < 5; i++) {
            if (i != root) {
                assertTrue(tree.getParent(i) >= 0 && tree.getParent(i) < 5);
            }
        }
    }

    @Test
    void testStarTree() {
        // Star tree: center node 0 connected to 1, 2, 3, 4
        int[][] edges = {{0, 1}, {0, 2}, {0, 3}, {0, 4}};
        CentroidDecomposition.CentroidTree tree = CentroidDecomposition.buildFromEdges(5, edges);

        assertEquals(5, tree.size());
        // Center node (0) should be the root
        assertEquals(0, tree.getRoot());

        // All other nodes should have 0 as parent
        for (int i = 1; i < 5; i++) {
            assertEquals(0, tree.getParent(i));
        }
    }

    @Test
    void testCompleteTree() {
        // Complete binary tree of 7 nodes:
        //       0
        //      / \
        //     1   2
        //    / \ / \
        //   3  4 5  6
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}};
        CentroidDecomposition.CentroidTree tree = CentroidDecomposition.buildFromEdges(7, edges);

        assertEquals(7, tree.size());
        assertEquals(0, tree.getRoot()); // Root should be the center

        // Verify all nodes are reachable in centroid tree
        boolean[] visited = new boolean[7];
        visited[0] = true;
        for (int i = 1; i < 7; i++) {
            int parent = tree.getParent(i);
            assertTrue(parent >= 0 && parent < 7);
            assertTrue(visited[parent], "Parent should be processed before child");
            visited[i] = true;
        }
    }

    @Test
    void testLargerTree() {
        // Tree with 10 nodes
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}, {3, 7}, {4, 8}, {5, 9}};
        CentroidDecomposition.CentroidTree tree = CentroidDecomposition.buildFromEdges(10, edges);

        assertEquals(10, tree.size());
        int root = tree.getRoot();
        assertTrue(root >= 0 && root < 10);
        assertEquals(-1, tree.getParent(root));

        // Verify centroid tree structure is valid
        for (int i = 0; i < 10; i++) {
            if (i != root) {
                assertTrue(tree.getParent(i) >= -1 && tree.getParent(i) < 10);
            }
        }
    }

    @Test
    void testPathGraph() {
        // Path graph with 8 nodes: 0-1-2-3-4-5-6-7
        int[][] edges = {{0, 1}, {1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}};
        CentroidDecomposition.CentroidTree tree = CentroidDecomposition.buildFromEdges(8, edges);

        assertEquals(8, tree.size());
        // For path of 8 nodes, centroid should be around middle
        int root = tree.getRoot();
        assertTrue(root >= 2 && root <= 5, "Root should be near the middle of path");
    }

    @Test
    void testInvalidEmptyTree() {
        assertThrows(IllegalArgumentException.class, () -> { CentroidDecomposition.buildFromEdges(0, new int[][] {}); });
    }

    @Test
    void testInvalidNegativeNodes() {
        assertThrows(IllegalArgumentException.class, () -> { CentroidDecomposition.buildFromEdges(-1, new int[][] {}); });
    }

    @Test
    void testInvalidNullEdges() {
        assertThrows(IllegalArgumentException.class, () -> { CentroidDecomposition.buildFromEdges(5, null); });
    }

    @Test
    void testInvalidEdgeCount() {
        // Tree with n nodes must have n-1 edges
        int[][] edges = {{0, 1}, {1, 2}}; // 2 edges for 5 nodes (should be 4)
        assertThrows(IllegalArgumentException.class, () -> { CentroidDecomposition.buildFromEdges(5, edges); });
    }

    @Test
    void testInvalidEdgeFormat() {
        int[][] edges = {{0, 1, 2}}; // Edge with 3 elements instead of 2
        assertThrows(IllegalArgumentException.class, () -> { CentroidDecomposition.buildFromEdges(3, edges); });
    }

    @Test
    void testInvalidNodeInEdge() {
        int[][] edges = {{0, 5}}; // Node 5 doesn't exist in tree of size 3
        assertThrows(IllegalArgumentException.class, () -> { CentroidDecomposition.buildFromEdges(3, edges); });
    }

    @Test
    void testInvalidNodeQuery() {
        int[][] edges = {{0, 1}, {1, 2}};
        CentroidDecomposition.CentroidTree tree = CentroidDecomposition.buildFromEdges(3, edges);

        assertThrows(IllegalArgumentException.class, () -> { tree.getParent(-1); });

        assertThrows(IllegalArgumentException.class, () -> { tree.getParent(5); });
    }

    @Test
    void testToString() {
        int[][] edges = {{0, 1}, {1, 2}};
        CentroidDecomposition.CentroidTree tree = CentroidDecomposition.buildFromEdges(3, edges);

        String result = tree.toString();
        assertNotNull(result);
        assertTrue(result.contains("Centroid Tree"));
        assertTrue(result.contains("Node"));
        assertTrue(result.contains("ROOT"));
    }

    @Test
    void testAdjacencyListConstructor() {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            adj.add(new ArrayList<>());
        }
        adj.get(0).add(1);
        adj.get(1).add(0);
        adj.get(1).add(2);
        adj.get(2).add(1);

        CentroidDecomposition.CentroidTree tree = new CentroidDecomposition.CentroidTree(adj);
        assertEquals(3, tree.size());
        assertEquals(1, tree.getRoot());
    }

    @Test
    void testNullAdjacencyList() {
        assertThrows(IllegalArgumentException.class, () -> { new CentroidDecomposition.CentroidTree(null); });
    }

    @Test
    void testEmptyAdjacencyList() {
        assertThrows(IllegalArgumentException.class, () -> { new CentroidDecomposition.CentroidTree(new ArrayList<>()); });
    }
}
