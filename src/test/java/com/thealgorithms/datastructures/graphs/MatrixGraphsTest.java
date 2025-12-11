package com.thealgorithms.datastructures.graphs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class MatrixGraphsTest {

    // ... (keep existing tests above)

    void testToString() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);

        String expected = "    0 1 2  \n"
                + "0 : 0 1 0 \n"
                + "1 : 1 0 1 \n"
                + "2 : 0 1 0 \n";

        assertEquals(expected, graph.toString());
    }

    @Test
    void testDisconnectedGraph() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(3);
        graph.addEdge(0, 1);

        List<Integer> dfs = graph.depthFirstOrder(0);
        List<Integer> bfs = graph.breadthFirstOrder(0);

        assertTrue(dfs.containsAll(Arrays.asList(0, 1)));
        assertTrue(bfs.containsAll(Arrays.asList(0, 1)));
    }

    @Test
    void testSingleVertexGraphDfs() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(1);

        List<Integer> dfs = graph.depthFirstOrder(0);
        assertEquals(1, dfs.size());
        assertEquals(0, dfs.getFirst());
    }

    @Test
    void testSingleVertexGraphBfs() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(1);

        List<Integer> bfs = graph.breadthFirstOrder(0);
        assertEquals(1, bfs.size());
        assertEquals(0, bfs.getFirst());
    }

    @Test
    void testBfsLevelOrder() {
        // Create a graph where BFS should visit level by level
        // 0
        // /|\
        // 1 2 3
        // |
        // 4
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(1, 4);

        List<Integer> bfs = graph.breadthFirstOrder(0);
        assertEquals(5, bfs.size());
        assertEquals(0, bfs.get(0));
        // Level 1 vertices (1, 2, 3) should appear before level 2 vertex (4)
        int indexOf4 = bfs.indexOf(4);
        assertTrue(bfs.indexOf(1) < indexOf4);
        assertTrue(bfs.indexOf(2) < indexOf4);
        assertTrue(bfs.indexOf(3) < indexOf4);
    }

    @Test
    void testDfsStartFromDifferentVertices() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        // DFS from vertex 0
        List<Integer> dfs0 = graph.depthFirstOrder(0);
        assertEquals(4, dfs0.size());
        assertEquals(0, dfs0.get(0));

        // DFS from vertex 2
        List<Integer> dfs2 = graph.depthFirstOrder(2);
        assertEquals(4, dfs2.size());
        assertEquals(2, dfs2.get(0));

        // DFS from vertex 3
        List<Integer> dfs3 = graph.depthFirstOrder(3);
        assertEquals(4, dfs3.size());
        assertEquals(3, dfs3.get(0));
    }

    @Test
    void testBfsStartFromDifferentVertices() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        // BFS from vertex 0
        List<Integer> bfs0 = graph.breadthFirstOrder(0);
        assertEquals(4, bfs0.size());
        assertEquals(0, bfs0.get(0));

        // BFS from vertex 2
        List<Integer> bfs2 = graph.breadthFirstOrder(2);
        assertEquals(4, bfs2.size());
        assertEquals(2, bfs2.get(0));
    }

    @Test
    void testStarTopologyBfs() {
        // Star graph: 0 is center connected to 1, 2, 3, 4
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(0, 4);

        List<Integer> bfs = graph.breadthFirstOrder(0);
        assertEquals(5, bfs.size());
        assertEquals(0, bfs.get(0));
        // All neighbors should be at distance 1
        assertTrue(bfs.containsAll(Arrays.asList(1, 2, 3, 4)));
    }

    @Test
    void testStarTopologyDfs() {
        // Star graph: 0 is center connected to 1, 2, 3, 4
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(0, 4);

        List<Integer> dfs = graph.depthFirstOrder(0);
        assertEquals(5, dfs.size());
        assertEquals(0, dfs.get(0));
        assertTrue(dfs.containsAll(Arrays.asList(1, 2, 3, 4)));
    }

    @Test
    void testNegativeStartVertexDfs() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(5);
        graph.addEdge(0, 1);

        List<Integer> dfs = graph.depthFirstOrder(-1);
        assertTrue(dfs.isEmpty());
    }

    @Test
    void testNegativeStartVertexBfs() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(5);
        graph.addEdge(0, 1);

        List<Integer> bfs = graph.breadthFirstOrder(-1);
        assertTrue(bfs.isEmpty());
    }

    @Test
    void testCompleteGraphKFour() {
        // Complete graph K4: every vertex connected to every other vertex
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);

        assertEquals(6, graph.numberOfEdges());

        List<Integer> dfs = graph.depthFirstOrder(0);
        List<Integer> bfs = graph.breadthFirstOrder(0);

        assertEquals(4, dfs.size());
        assertEquals(4, bfs.size());
        assertTrue(dfs.containsAll(Arrays.asList(0, 1, 2, 3)));
        assertTrue(bfs.containsAll(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    void testLargerGraphTraversal() {
        // Create a larger graph with 10 vertices
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(10);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(2, 6);
        graph.addEdge(3, 7);
        graph.addEdge(4, 8);
        graph.addEdge(5, 9);

        List<Integer> dfs = graph.depthFirstOrder(0);
        List<Integer> bfs = graph.breadthFirstOrder(0);

        assertEquals(10, dfs.size());
        assertEquals(10, bfs.size());
        assertEquals(0, dfs.get(0));
        assertEquals(0, bfs.get(0));
    }

    @Test
    void testSelfLoop() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(3);
        graph.addEdge(0, 0); // Self loop
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);

        List<Integer> dfs = graph.depthFirstOrder(0);
        List<Integer> bfs = graph.breadthFirstOrder(0);

        assertEquals(3, dfs.size());
        assertEquals(3, bfs.size());
    }

    @Test
    void testLinearGraphTraversal() {
        // Linear graph: 0 - 1 - 2 - 3 - 4
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(5);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        List<Integer> dfs = graph.depthFirstOrder(0);
        List<Integer> bfs = graph.breadthFirstOrder(0);

        assertEquals(5, dfs.size());
        assertEquals(5, bfs.size());

        // In a linear graph, BFS and DFS starting from 0 should be the same
        assertEquals(Arrays.asList(0, 1, 2, 3, 4), dfs);
        assertEquals(Arrays.asList(0, 1, 2, 3, 4), bfs);
    }
}
