package com.thealgorithms.greedyalgorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class KruskalAlgorithmTest {

    @Test
    void testSimpleGraph() {
        List<KruskalAlgorithm.Edge> edges = new ArrayList<>();
        edges.add(new KruskalAlgorithm.Edge(0, 1, 10));
        edges.add(new KruskalAlgorithm.Edge(0, 2, 6));
        edges.add(new KruskalAlgorithm.Edge(0, 3, 5));
        edges.add(new KruskalAlgorithm.Edge(1, 3, 15));
        edges.add(new KruskalAlgorithm.Edge(2, 3, 4));

        List<KruskalAlgorithm.Edge> mst = KruskalAlgorithm.kruskal(4, edges);

        assertEquals(3, mst.size());
        assertEquals(19, KruskalAlgorithm.getMSTWeight(mst));
    }

    @Test
    void testDisconnectedGraph() {
        List<KruskalAlgorithm.Edge> edges = new ArrayList<>();
        edges.add(new KruskalAlgorithm.Edge(0, 1, 1));
        edges.add(new KruskalAlgorithm.Edge(2, 3, 2));

        List<KruskalAlgorithm.Edge> mst = KruskalAlgorithm.kruskal(4, edges);

        assertEquals(2, mst.size());
        assertEquals(3, KruskalAlgorithm.getMSTWeight(mst));
    }

    @Test
    void testSingleVertex() {
        List<KruskalAlgorithm.Edge> edges = new ArrayList<>();
        List<KruskalAlgorithm.Edge> mst = KruskalAlgorithm.kruskal(1, edges);

        assertTrue(mst.isEmpty());
        assertEquals(0, KruskalAlgorithm.getMSTWeight(mst));
    }

    @Test
    void testCompleteGraph() {
        List<KruskalAlgorithm.Edge> edges = new ArrayList<>();
        edges.add(new KruskalAlgorithm.Edge(0, 1, 1));
        edges.add(new KruskalAlgorithm.Edge(0, 2, 2));
        edges.add(new KruskalAlgorithm.Edge(0, 3, 3));
        edges.add(new KruskalAlgorithm.Edge(1, 2, 4));
        edges.add(new KruskalAlgorithm.Edge(1, 3, 5));
        edges.add(new KruskalAlgorithm.Edge(2, 3, 6));

        List<KruskalAlgorithm.Edge> mst = KruskalAlgorithm.kruskal(4, edges);

        assertEquals(3, mst.size());
        assertEquals(6, KruskalAlgorithm.getMSTWeight(mst));
    }

    @Test
    void testEqualWeights() {
        List<KruskalAlgorithm.Edge> edges = new ArrayList<>();
        edges.add(new KruskalAlgorithm.Edge(0, 1, 1));
        edges.add(new KruskalAlgorithm.Edge(1, 2, 1));
        edges.add(new KruskalAlgorithm.Edge(2, 0, 1));

        List<KruskalAlgorithm.Edge> mst = KruskalAlgorithm.kruskal(3, edges);

        assertEquals(2, mst.size());
        assertEquals(2, KruskalAlgorithm.getMSTWeight(mst));
    }

    @Test
    void testEmptyGraph() {
        List<KruskalAlgorithm.Edge> edges = new ArrayList<>();
        List<KruskalAlgorithm.Edge> mst = KruskalAlgorithm.kruskal(5, edges);

        assertTrue(mst.isEmpty());
    }

    @Test
    void testInvalidVertexCount() {
        List<KruskalAlgorithm.Edge> edges = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> KruskalAlgorithm.kruskal(0, edges));
        assertThrows(IllegalArgumentException.class, () -> KruskalAlgorithm.kruskal(-1, edges));
    }

    @Test
    void testNullEdges() {
        assertThrows(IllegalArgumentException.class, () -> KruskalAlgorithm.kruskal(5, null));
    }
}