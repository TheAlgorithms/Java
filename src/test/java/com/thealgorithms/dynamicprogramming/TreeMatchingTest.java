package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.thealgorithms.datastructures.graphs.UndirectedAdjacencyListGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TreeMatchingTest {
    UndirectedAdjacencyListGraph graph;

    @BeforeEach
    void setUp() {
        graph = new UndirectedAdjacencyListGraph();
        for (int i = 0; i < 14; i++) {
            graph.addNode();
        }
    }

    @Test
    void testMaxMatchingForGeneralTree() {
        graph.addEdge(0, 1, 20);
        graph.addEdge(0, 2, 30);
        graph.addEdge(1, 3, 40);
        graph.addEdge(1, 4, 10);
        graph.addEdge(2, 5, 20);
        graph.addEdge(3, 6, 30);
        graph.addEdge(3, 7, 30);
        graph.addEdge(5, 8, 40);
        graph.addEdge(5, 9, 10);

        TreeMatching treeMatching = new TreeMatching(graph);
        assertEquals(110, treeMatching.getMaxMatching(0, -1));
    }

    @Test
    void testMaxMatchingForBalancedTree() {
        graph.addEdge(0, 1, 20);
        graph.addEdge(0, 2, 30);
        graph.addEdge(0, 3, 40);
        graph.addEdge(1, 4, 10);
        graph.addEdge(1, 5, 20);
        graph.addEdge(2, 6, 20);
        graph.addEdge(3, 7, 30);
        graph.addEdge(5, 8, 10);
        graph.addEdge(5, 9, 20);
        graph.addEdge(7, 10, 10);
        graph.addEdge(7, 11, 10);
        graph.addEdge(7, 12, 5);
        TreeMatching treeMatching = new TreeMatching(graph);
        assertEquals(100, treeMatching.getMaxMatching(0, -1));
    }

    @Test
    void testMaxMatchingForTreeWithVariedEdgeWeights() {
        graph.addEdge(0, 1, 20);
        graph.addEdge(0, 2, 30);
        graph.addEdge(0, 3, 40);
        graph.addEdge(0, 4, 50);
        graph.addEdge(1, 5, 20);
        graph.addEdge(2, 6, 20);
        graph.addEdge(3, 7, 30);
        graph.addEdge(5, 8, 10);
        graph.addEdge(5, 9, 20);
        graph.addEdge(7, 10, 10);
        graph.addEdge(4, 11, 50);
        graph.addEdge(4, 12, 20);
        TreeMatching treeMatching = new TreeMatching(graph);
        assertEquals(140, treeMatching.getMaxMatching(0, -1));
    }

    @Test
    void emptyTree() {
        TreeMatching treeMatching = new TreeMatching(graph);
        assertEquals(0, treeMatching.getMaxMatching(0, -1));
    }

    @Test
    void testSingleNodeTree() {
        UndirectedAdjacencyListGraph singleNodeGraph = new UndirectedAdjacencyListGraph();
        singleNodeGraph.addNode();

        TreeMatching treeMatching = new TreeMatching(singleNodeGraph);
        assertEquals(0, treeMatching.getMaxMatching(0, -1));
    }

    @Test
    void testLinearTree() {
        graph.addEdge(0, 1, 10);
        graph.addEdge(1, 2, 20);
        graph.addEdge(2, 3, 30);
        graph.addEdge(3, 4, 40);

        TreeMatching treeMatching = new TreeMatching(graph);
        assertEquals(60, treeMatching.getMaxMatching(0, -1));
    }

    @Test
    void testStarShapedTree() {
        graph.addEdge(0, 1, 15);
        graph.addEdge(0, 2, 25);
        graph.addEdge(0, 3, 35);
        graph.addEdge(0, 4, 45);

        TreeMatching treeMatching = new TreeMatching(graph);
        assertEquals(45, treeMatching.getMaxMatching(0, -1));
    }

    @Test
    void testUnbalancedTree() {
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 20);
        graph.addEdge(1, 3, 30);
        graph.addEdge(2, 4, 40);
        graph.addEdge(4, 5, 50);

        TreeMatching treeMatching = new TreeMatching(graph);
        assertEquals(100, treeMatching.getMaxMatching(0, -1));
    }
}
