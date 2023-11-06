package com.thealgorithms.greedyalgorithms;

import static com.thealgorithms.greedyalgorithms.BoruvkaMST.Edge;
import static com.thealgorithms.greedyalgorithms.BoruvkaMST.Graph;
import static com.thealgorithms.greedyalgorithms.BoruvkaMST.UnionFind;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

class BoruvkaMSTTest {

    @Test
    void testUnionFindInitialization() {
        UnionFind unionFind = new UnionFind(5);

        for (int i = 0; i < 5; i++) {
            assertEquals(i, unionFind.find(i));
        }
    }

    @Test
    void testUnionFindUnionAndFind() {
        BoruvkaMST.UnionFind unionFind = new BoruvkaMST.UnionFind(10);

        // Perform some unions
        unionFind.union(0, 1);
        unionFind.union(2, 3);
        unionFind.union(0, 2); // 0, 1, 2, 3 should now be connected

        assertEquals(unionFind.find(0), unionFind.find(1));
        assertEquals(unionFind.find(0), unionFind.find(3));
        assertNotEquals(unionFind.find(0), unionFind.find(4));
    }

    @Test
    void testUnionFindRank() {
        BoruvkaMST.UnionFind unionFind = new BoruvkaMST.UnionFind(10);

        // Connect two single-node trees, the rank should increase
        unionFind.union(0, 1);
        unionFind.union(2, 3);
        unionFind.union(0, 2); // Rank should increase only once here

        // Check ranks
        assertTrue(unionFind.rank[unionFind.find(0)] > 0);
        assertEquals(0, unionFind.rank[unionFind.find(4)]); // Should be unchanged
    }

    @Test
    void testSmallGraphMST() {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 6);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 3, 15);
        graph.addEdge(2, 3, 4);

        // Run Boruvka's algorithm
        graph.boruvkaMST();
    }

    @Test
    void testGraphWithEqualEdgeWeights() {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 1);
        graph.addEdge(2, 3, 1);
        graph.addEdge(3, 0, 1);

        graph.boruvkaMST();
    }

    @Test
    void testGraphWithMultipleEqualMinimumEdges() {
        Graph graph = new Graph(6);
        graph.addEdge(0, 1, 3);
        graph.addEdge(0, 2, 1);
        graph.addEdge(0, 3, 3);
        graph.addEdge(1, 4, 1);
        graph.addEdge(2, 5, 1);
        graph.addEdge(3, 4, 1);
        graph.addEdge(4, 5, 2);

        graph.boruvkaMST();
    }

    @Test
    void testAddingInvalidEdge() {
        BoruvkaMST.Graph graph = new BoruvkaMST.Graph(4);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            graph.addEdge(4, 1, 2); // Invalid edge, vertex 4 does not exist
        });

        String expectedMessage = "Vertex number is out of bounds.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testBoruvkaMSTOutput() {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 2);
        graph.addEdge(2, 3, 3);
        graph.addEdge(3, 0, 4);

        // Run Boruvka's algorithm
        List<Edge> mst = graph.boruvkaMST();

        // Sort edges by weight for deterministic assertion
        mst = mst.stream().sorted(Comparator.comparingInt(Edge::weight)).collect(Collectors.toList());

        // Expected MST edges
        List<Edge> expectedMst = List.of(new Edge(0, 1, 1), new Edge(1, 2, 2), new Edge(2, 3, 3));

        // Sort expected edges by weight for deterministic assertion
        expectedMst = expectedMst.stream().sorted(Comparator.comparingInt(Edge::weight)).collect(Collectors.toList());

        assertEquals(expectedMst, mst);
    }

    @Test
    void testBoruvkaMSTOutputT() {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 2);
        graph.addEdge(2, 3, 3);
        graph.addEdge(3, 0, 4);

        List<Edge> mst = graph.boruvkaMST();

        // Assertions to verify the MST
        assertEquals(3, mst.size()); // Expecting 3 edges in the MST
        assertTrue(mst.contains(new Edge(0, 1, 1)));
        assertTrue(mst.contains(new Edge(1, 2, 2)));
        assertTrue(mst.contains(new Edge(2, 3, 3)));
    }
}
