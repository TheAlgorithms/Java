package com.dataStructures.graphs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AdjacencyMatrixGraphTest {

    @Test
    void testAdjacencyMatrixGraph() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(5);
        graph.addEdge(0, 2);
        graph.addEdge(1, 4);
        graph.addEdge(2, 4);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        Assertions.assertEquals(
                "0 0 1 0 0 \n" +
                        "0 0 0 0 1 \n" +
                        "1 0 0 1 1 \n" +
                        "0 0 1 0 1 \n" +
                        "0 1 1 1 0 \n", graph.toString());

        AdjacencyMatrixGraph graph2 = new AdjacencyMatrixGraph(6);
        graph2.addEdge(1, 2);
        graph2.addEdge(0, 3);
        graph2.addEdge(4, 5);
        graph2.addEdge(3, 4);
        Assertions.assertEquals("0 0 0 1 0 0 \n" +
                "0 0 1 0 0 0 \n" +
                "0 1 0 0 0 0 \n" +
                "1 0 0 0 1 0 \n" +
                "0 0 0 1 0 1 \n" +
                "0 0 0 0 1 0 \n", graph2.toString());
    }

    @Test
    void testEdgeDoesExist() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(5);
        graph.addEdge(0, 2);
        graph.addEdge(1, 4);
        graph.addEdge(2, 4);
        graph.addEdge(2, 3);
        Assertions.assertTrue(graph.edgeDoesExist(0, 2));
        Assertions.assertFalse(graph.edgeDoesExist(1, 5));
    }

    @Test
    void testAddEdge() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(5);
        graph.addEdge(0, 2);
        graph.addEdge(1, 4);
        graph.addEdge(2, 4);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        Assertions.assertFalse(graph.addEdge(0, 2));
        Assertions.assertTrue(graph.addEdge(0, 3));
    }

    @Test
    void testRemoveEdge() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(5);
        graph.addEdge(0, 2);
        graph.addEdge(1, 4);
        graph.addEdge(2, 4);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        Assertions.assertTrue(graph.removeEdge(0, 2));
        Assertions.assertFalse(graph.removeEdge(0, 3));
    }
}
