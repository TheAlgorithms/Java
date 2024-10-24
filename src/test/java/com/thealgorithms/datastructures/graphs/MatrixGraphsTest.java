package com.thealgorithms.datastructures.graphs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class MatrixGraphsTest {

    @Test
    void testGraphConstruction() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(5);
        assertEquals(5, graph.numberOfVertices());
        assertEquals(0, graph.numberOfEdges());
    }

    @Test
    void testAddEdge() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(5);
        assertTrue(graph.addEdge(0, 1));
        assertTrue(graph.edgeDoesExist(0, 1));
        assertTrue(graph.edgeDoesExist(1, 0));
        assertEquals(1, graph.numberOfEdges());

        // Adding the same edge again should return false
        assertFalse(graph.addEdge(0, 1));
        assertFalse(graph.addEdge(5, 1));
        assertFalse(graph.addEdge(-1, 1));
    }

    @Test
    void testRemoveEdge() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(5);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);

        assertTrue(graph.removeEdge(0, 1));
        assertFalse(graph.edgeDoesExist(0, 1));
        assertFalse(graph.edgeDoesExist(1, 0));
        assertEquals(1, graph.numberOfEdges());

        assertFalse(graph.removeEdge(0, 3));
        assertFalse(graph.removeEdge(5, 1));
        assertFalse(graph.removeEdge(-1, 1));
    }

    @Test
    void testVertexDoesExist() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(5);
        assertTrue(graph.vertexDoesExist(0));
        assertTrue(graph.vertexDoesExist(4));
        assertFalse(graph.vertexDoesExist(5));
        assertFalse(graph.vertexDoesExist(-1));
    }

    @Test
    void testDepthFirstOrder() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);

        List<Integer> dfs = graph.depthFirstOrder(0);
        assertEquals(5, dfs.size());
        assertEquals(0, dfs.getFirst());

        assertTrue(dfs.containsAll(Arrays.asList(0, 1, 2, 3, 4)));

        List<Integer> emptyDfs = graph.depthFirstOrder(5);
        assertTrue(emptyDfs.isEmpty());
    }

    @Test
    void testBreadthFirstOrder() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);

        List<Integer> bfs = graph.breadthFirstOrder(0);
        assertEquals(5, bfs.size());
        assertEquals(0, bfs.getFirst());

        assertTrue(bfs.containsAll(Arrays.asList(0, 1, 2, 3, 4)));

        List<Integer> emptyBfs = graph.breadthFirstOrder(5);
        assertTrue(emptyBfs.isEmpty());
    }

    @Test
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
    void testCyclicGraph() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 0);

        List<Integer> dfs = graph.depthFirstOrder(0);
        List<Integer> bfs = graph.breadthFirstOrder(0);

        assertEquals(4, dfs.size());
        assertEquals(4, bfs.size());
        assertTrue(dfs.containsAll(Arrays.asList(0, 1, 2, 3)));
        assertTrue(bfs.containsAll(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    void testDisconnectedGraph() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(5);
        graph.addEdge(0, 1);
        graph.addEdge(2, 3);

        List<Integer> dfs = graph.depthFirstOrder(0);
        List<Integer> bfs = graph.breadthFirstOrder(0);

        assertEquals(2, dfs.size());
        assertEquals(2, bfs.size());
        assertTrue(dfs.containsAll(Arrays.asList(0, 1)));
        assertTrue(bfs.containsAll(Arrays.asList(0, 1)));
    }
}
