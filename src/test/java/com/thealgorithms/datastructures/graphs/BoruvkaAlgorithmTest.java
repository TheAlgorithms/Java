package com.thealgorithms.datastructures.graphs;

import static org.junit.jupiter.api.Assertions.*;

import com.thealgorithms.datastructures.graphs.BoruvkaAlgorithm.Graph;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class BoruvkaAlgorithmTest {
    @Test
    public void testBoruvkaMSTV9E14() {
        // Test case 1
        int V1 = 9;

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

        Graph graph1 = new Graph(V1, edges);
        /**
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

        List<BoruvkaAlgorithm.Edge> result1 = BoruvkaAlgorithm.boruvkaMST(graph1);
        // Expected result:
        // Edges: 8
        // Total weight: 43
        assertEquals(8, result1.size());
        assertEquals(43, result1.stream().mapToInt(edge -> edge.weight).sum());
    }

    @Test
    void testBoruvkaMSTV2E1() {
        // Test case 2
        int V2 = 2;

        List<BoruvkaAlgorithm.Edge> edges = new ArrayList<>();

        edges.add(new BoruvkaAlgorithm.Edge(0, 1, 10));

        Graph graph2 = new Graph(V2, edges);

        /**
         * Adjacency matrix
         *    0  1
         * 0  0  10
         * 1  10  0
         */
        List<BoruvkaAlgorithm.Edge> result2 = BoruvkaAlgorithm.boruvkaMST(graph2);
        // Expected result:
        // Edges: 1
        // Total weight: 10
        assertEquals(1, result2.size());
        assertEquals(10, result2.stream().mapToInt(edge -> edge.weight).sum());
    }

    @Test
    void testCompleteGraphK4() {
        // Test case 3
        int V3 = 4;

        List<BoruvkaAlgorithm.Edge> edges = new ArrayList<>();
        edges.add(new BoruvkaAlgorithm.Edge(0, 1, 7));
        edges.add(new BoruvkaAlgorithm.Edge(0, 2, 2));
        edges.add(new BoruvkaAlgorithm.Edge(0, 3, 5));
        edges.add(new BoruvkaAlgorithm.Edge(1, 2, 3));
        edges.add(new BoruvkaAlgorithm.Edge(1, 3, 4));
        edges.add(new BoruvkaAlgorithm.Edge(2, 3, 1));

        Graph graph3 = new Graph(V3, edges);

        /**
         * Adjacency matrix
         *    0  1  2  3
         * 0  0  7  2  5
         * 1  7  0  3  4
         * 2  2  3  0  1
         * 3  5  4  1  0
         */

        List<BoruvkaAlgorithm.Edge> result3 = BoruvkaAlgorithm.boruvkaMST(graph3);
        // Expected result:
        // Edges: 3
        // Total weight: 6
        assertEquals(3, result3.size());
        assertEquals(6, result3.stream().mapToInt(edge -> edge.weight).sum());
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
        BoruvkaAlgorithm.Graph validGraph = new BoruvkaAlgorithm.Graph(3, validEdges);
        assertEquals(validEdges, validGraph.edges);

        // Edge source out of range
        Exception exception1 = assertThrows(IllegalArgumentException.class, () -> {
            List<BoruvkaAlgorithm.Edge> invalidEdges = new ArrayList<>();
            invalidEdges.add(new BoruvkaAlgorithm.Edge(-1, 1, 2));
            BoruvkaAlgorithm.Graph invalidGraph = new BoruvkaAlgorithm.Graph(1, invalidEdges);
            assertEquals(invalidEdges, invalidGraph.edges);
        });
        String expectedMessage1 = "Edge source out of range";
        String actualMessage1 = exception1.getMessage();

        assertTrue(actualMessage1.contains(expectedMessage1));

        // Edge destination out of range
        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
            List<BoruvkaAlgorithm.Edge> invalidEdges = new ArrayList<>();
            invalidEdges.add(new BoruvkaAlgorithm.Edge(0, 5, 2));
            BoruvkaAlgorithm.Graph invalidGraph = new BoruvkaAlgorithm.Graph(1, invalidEdges);
            assertEquals(invalidEdges, invalidGraph.edges);
        });
        String expectedMessage2 = "Edge destination out of range";
        String actualMessage2 = exception2.getMessage();

        assertTrue(actualMessage2.contains(expectedMessage2));
    }
}
