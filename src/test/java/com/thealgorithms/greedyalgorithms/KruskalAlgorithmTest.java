package com.thealgorithms.greedyalgorithms;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the KruskalAlgorithm implementation.
 */
public class KruskalAlgorithmTest {

    @Test
    @DisplayName("Computes MST for a standard connected graph")
    void testMSTCorrectness() {
        KruskalAlgorithm.Graph graph = new KruskalAlgorithm.Graph(4);

        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 6);
        graph.addEdge(0, 3, 5);
        graph.addEdge(2, 3, 4);

        KruskalAlgorithm algo = new KruskalAlgorithm();
        List<KruskalAlgorithm.Edge> mst = algo.computeMST(graph);

        assertEquals(3, mst.size());
        int totalWeight = mst.stream().mapToInt(KruskalAlgorithm.Edge::getWeight).sum();

        assertEquals(19, totalWeight); // Correct MST weight
    }

    @Test
    @DisplayName("Graph with a single vertex produces an empty MST")
    void testSingleVertexGraph() {
        KruskalAlgorithm.Graph graph = new KruskalAlgorithm.Graph(1);

        KruskalAlgorithm algo = new KruskalAlgorithm();
        List<KruskalAlgorithm.Edge> mst = algo.computeMST(graph);

        assertTrue(mst.isEmpty());
    }

    @Test
    @DisplayName("Disconnected graph yields a minimum spanning forest")
    void testDisconnectedGraph() {
        KruskalAlgorithm.Graph graph = new KruskalAlgorithm.Graph(4);

        graph.addEdge(0, 1, 3);
        graph.addEdge(2, 3, 1);

        KruskalAlgorithm algo = new KruskalAlgorithm();
        List<KruskalAlgorithm.Edge> mst = algo.computeMST(graph);

        assertEquals(2, mst.size());
    }

    @Test
    @DisplayName("Adding an edge with negative weight should throw an exception")
    void testNegativeWeightThrowsException() {
        KruskalAlgorithm.Graph graph = new KruskalAlgorithm.Graph(3);

        assertThrows(IllegalArgumentException.class, () -> graph.addEdge(0, 1, -5));
    }

    @Test
    @DisplayName("Parallel edges: algorithm should choose the cheaper one")
    void testParallelEdges() {
        KruskalAlgorithm.Graph graph = new KruskalAlgorithm.Graph(3);

        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 1, 3);   // cheaper parallel edge
        graph.addEdge(1, 2, 4);

        KruskalAlgorithm algo = new KruskalAlgorithm();
        List<KruskalAlgorithm.Edge> mst = algo.computeMST(graph);

        int totalWeight = mst.stream().mapToInt(KruskalAlgorithm.Edge::getWeight).sum();

        assertEquals(7, totalWeight);
    }

    @Test
    @DisplayName("Graph with no edges must produce an empty MST")
    void testEmptyGraph() {
        KruskalAlgorithm.Graph graph = new KruskalAlgorithm.Graph(5);

        KruskalAlgorithm algo = new KruskalAlgorithm();
        List<KruskalAlgorithm.Edge> mst = algo.computeMST(graph);

        assertTrue(mst.isEmpty());
    }

    // ---------------------------
    // ADDITIONAL ROBUSTNESS TESTS
    // ---------------------------

    @Test
    @DisplayName("Edge with invalid vertex index should throw exception")
    void testOutOfBoundsVertexIndex() {
        KruskalAlgorithm.Graph graph = new KruskalAlgorithm.Graph(3);

        assertThrows(IndexOutOfBoundsException.class, () -> graph.addEdge(0, 5, 10));
        assertThrows(IndexOutOfBoundsException.class, () -> graph.addEdge(-1, 1, 2));
    }

    @Test
    @DisplayName("Zero-weight edges are allowed and handled correctly")
    void testZeroWeightEdges() {
        KruskalAlgorithm.Graph graph = new KruskalAlgorithm.Graph(3);

        graph.addEdge(0, 1, 0);
        graph.addEdge(1, 2, 1);

        KruskalAlgorithm algo = new KruskalAlgorithm();

        assertDoesNotThrow(() -> algo.computeMST(graph));
        List<KruskalAlgorithm.Edge> mst = algo.computeMST(graph);

        int totalWeight = mst.stream().mapToInt(KruskalAlgorithm.Edge::getWeight).sum();
        assertEquals(1, totalWeight);
    }
}
