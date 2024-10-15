package com.thealgorithms.datastructures.graphs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.thealgorithms.datastructures.graphs.BoruvkaAlgorithm.Graph;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class BoruvkaAlgorithmTest {
    @Test
    public void testBoruvkaMSTV9E14() {
        List<BoruvkaAlgorithm.Edge> edges = new ArrayList<>();

        edges.add(new BoruvkaAlgorithm.Edge(0, 1, 10));
        edges.add(new BoruvkaAlgorithm.Edge(0, 2, 12));
        edges.add(new BoruvkaAlgorithm.Edge(1, 2, 9));
        edges.add(new BoruvkaAlgorithm.Edge(1, 3, 8));
        edges.add(new BoruvkaAlgorithm.Edge(2, 4, 3));
        edges.add(new BoruvkaAlgorithm.Edge(2, 5, 1));
        edges.add(new BoruvkaAlgorithm.Edge(4, 5, 3));
        edges.add(new BoruvkaAlgorithm.Edge(4, 3, 7));
        edges.add(new BoruvkaAlgorithm.Edge(3, 6, 8));
        edges.add(new BoruvkaAlgorithm.Edge(3, 7, 5));
        edges.add(new BoruvkaAlgorithm.Edge(5, 7, 6));
        edges.add(new BoruvkaAlgorithm.Edge(6, 7, 9));
        edges.add(new BoruvkaAlgorithm.Edge(6, 8, 2));
        edges.add(new BoruvkaAlgorithm.Edge(7, 8, 11));

        final var graph = new Graph(9, edges);
        /*
         * Adjacency matrix
         *    0   1   2   3   4   5   6   7   8
         * 0  0  10  12   0   0   0   0   0   0
         * 1 10   0   9   8   0   0   0   0   0
         * 2 12   9   0   0   3   1   0   0   0
         * 3  0   8   0   0   7   0   8   5   0
         * 4  0   0   3   7   0   3   0   0   0
         * 5  0   0   1   0   3   0   0   6   0
         * 6  0   0   0   8   0   0   0   9   2
         * 7  0   0   0   5   0   6   9   0  11
         * 8  0   0   0   0   0   0   2  11   0
         */
        final var result = BoruvkaAlgorithm.boruvkaMST(graph);
        assertEquals(8, result.size());
        assertEquals(43, computeTotalWeight(result));
    }

    @Test
    void testBoruvkaMSTV2E1() {
        List<BoruvkaAlgorithm.Edge> edges = new ArrayList<>();

        edges.add(new BoruvkaAlgorithm.Edge(0, 1, 10));

        final var graph = new Graph(2, edges);

        /*
         * Adjacency matrix
         *    0  1
         * 0  0  10
         * 1  10  0
         */
        final var result = BoruvkaAlgorithm.boruvkaMST(graph);
        assertEquals(1, result.size());
        assertEquals(10, computeTotalWeight(result));
    }

    @Test
    void testCompleteGraphK4() {
        List<BoruvkaAlgorithm.Edge> edges = new ArrayList<>();
        edges.add(new BoruvkaAlgorithm.Edge(0, 1, 7));
        edges.add(new BoruvkaAlgorithm.Edge(0, 2, 2));
        edges.add(new BoruvkaAlgorithm.Edge(0, 3, 5));
        edges.add(new BoruvkaAlgorithm.Edge(1, 2, 3));
        edges.add(new BoruvkaAlgorithm.Edge(1, 3, 4));
        edges.add(new BoruvkaAlgorithm.Edge(2, 3, 1));

        final var graph = new Graph(4, edges);

        /*
         * Adjacency matrix
         *    0  1  2  3
         * 0  0  7  2  5
         * 1  7  0  3  4
         * 2  2  3  0  1
         * 3  5  4  1  0
         */
        final var result = BoruvkaAlgorithm.boruvkaMST(graph);
        assertEquals(3, result.size());
        assertEquals(6, computeTotalWeight(result));
    }

    @Test
    void testNegativeVertices() {
        Exception exception1 = assertThrows(IllegalArgumentException.class, () -> new Graph(-1, null));
        String expectedMessage = "Number of vertices must be positive";
        String actualMessage = exception1.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testEdgesNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Graph(0, null));
        String expectedMessage = "Edges list must not be null or empty";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testEdgesEmpty() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Graph(0, new ArrayList<>()));
        String expectedMessage = "Edges list must not be null or empty";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testEdgesRange() {
        // Valid input
        List<BoruvkaAlgorithm.Edge> validEdges = new ArrayList<>();
        validEdges.add(new BoruvkaAlgorithm.Edge(0, 1, 2));
        validEdges.add(new BoruvkaAlgorithm.Edge(1, 2, 3));
        final var validGraph = new BoruvkaAlgorithm.Graph(3, validEdges);
        assertEquals(validEdges, validGraph.edges);

        // Edge source out of range
        Exception exception1 = assertThrows(IllegalArgumentException.class, () -> {
            List<BoruvkaAlgorithm.Edge> invalidEdges = new ArrayList<>();
            invalidEdges.add(new BoruvkaAlgorithm.Edge(-1, 1, 2));
            final var invalidGraph = new BoruvkaAlgorithm.Graph(1, invalidEdges);
            assertEquals(invalidEdges, invalidGraph.edges);
        });
        String expectedMessage1 = "Edge vertex out of range";
        String actualMessage1 = exception1.getMessage();

        assertTrue(actualMessage1.contains(expectedMessage1));

        // Edge source out of range
        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
            List<BoruvkaAlgorithm.Edge> invalidEdges = new ArrayList<>();
            invalidEdges.add(new BoruvkaAlgorithm.Edge(1, 0, 2));
            final var invalidGraph = new BoruvkaAlgorithm.Graph(1, invalidEdges);
            assertEquals(invalidEdges, invalidGraph.edges);
        });
        String expectedMessage2 = "Edge vertex out of range";
        String actualMessage2 = exception2.getMessage();

        assertTrue(actualMessage2.contains(expectedMessage2));

        // Edge destination out of range
        Exception exception3 = assertThrows(IllegalArgumentException.class, () -> {
            List<BoruvkaAlgorithm.Edge> invalidEdges = new ArrayList<>();
            invalidEdges.add(new BoruvkaAlgorithm.Edge(0, -1, 2));
            final var invalidGraph = new BoruvkaAlgorithm.Graph(1, invalidEdges);
            assertEquals(invalidEdges, invalidGraph.edges);
        });
        String expectedMessage3 = "Edge vertex out of range";
        String actualMessage3 = exception3.getMessage();

        assertTrue(actualMessage3.contains(expectedMessage3));

        // Edge destination out of range
        Exception exception4 = assertThrows(IllegalArgumentException.class, () -> {
            List<BoruvkaAlgorithm.Edge> invalidEdges = new ArrayList<>();
            invalidEdges.add(new BoruvkaAlgorithm.Edge(0, 1, 2));
            final var invalidGraph = new BoruvkaAlgorithm.Graph(1, invalidEdges);
            assertEquals(invalidEdges, invalidGraph.edges);
        });
        String expectedMessage4 = "Edge vertex out of range";
        String actualMessage4 = exception4.getMessage();

        assertTrue(actualMessage4.contains(expectedMessage4));
    }

    /**
     * Computes the total weight of the Minimum Spanning Tree
     *
     * @param result list of edges in the Minimum Spanning Tree
     * @return the total weight of the Minimum Spanning Tree
     */
    int computeTotalWeight(final Iterable<BoruvkaAlgorithm.Edge> result) {
        int totalWeight = 0;
        for (final var edge : result) {
            totalWeight += edge.weight;
        }
        return totalWeight;
    }
}
