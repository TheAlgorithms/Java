package com.thealgorithms.datastructures.graphs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.thealgorithms.datastructures.graphs.ArticulationPointsAndBridges.Edge;
import com.thealgorithms.datastructures.graphs.ArticulationPointsAndBridges.Result;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class ArticulationPointsAndBridgesTest {

    /**
     * Helper method to create an undirected graph adjacency list.
     */
    private List<List<Integer>> createGraph(int vertices) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            graph.add(new ArrayList<>());
        }
        return graph;
    }

    /**
     * Helper method to add an undirected edge to the graph.
     */
    private void addEdge(List<List<Integer>> graph, int u, int v) {
        graph.get(u).add(v);
        graph.get(v).add(u);
    }

    @Test
    public void testSimpleGraphWithOneArticulationPoint() {
        /*
         * Graph structure:
         *       0 -------- 1 -------- 2
         *       |          |
         *       |          |
         *       3 -------- 4
         *
         * Articulation Point: 1
         * Bridges: (0,1), (1,2), (1,4)
         */
        int vertices = 5;
        List<List<Integer>> graph = createGraph(vertices);
        addEdge(graph, 0, 1);
        addEdge(graph, 0, 3);
        addEdge(graph, 1, 2);
        addEdge(graph, 1, 4);
        addEdge(graph, 3, 4);

        Result result = ArticulationPointsAndBridges.findArticulationPointsAndBridges(vertices, graph);

        // Verify articulation points
        Set<Integer> articulationPoints = result.getArticulationPoints();
        assertEquals(1, articulationPoints.size());
        assertTrue(articulationPoints.contains(1));

        // Verify bridges
        Set<Edge> bridges = result.getBridges();
        assertEquals(2, bridges.size());
        assertTrue(bridges.contains(new Edge(1, 2)));
        assertTrue(bridges.contains(new Edge(0, 1)));
    }

    @Test
    public void testGraphWithMultipleArticulationPoints() {
        /*
         * Graph structure:
         *       0 -------- 1 -------- 2 -------- 3
         *
         * Articulation Points: 1, 2
         * Bridges: (0,1), (1,2), (2,3)
         */
        int vertices = 4;
        List<List<Integer>> graph = createGraph(vertices);
        addEdge(graph, 0, 1);
        addEdge(graph, 1, 2);
        addEdge(graph, 2, 3);

        Result result = ArticulationPointsAndBridges.findArticulationPointsAndBridges(vertices, graph);

        // Verify articulation points
        Set<Integer> articulationPoints = result.getArticulationPoints();
        assertEquals(2, articulationPoints.size());
        assertTrue(articulationPoints.contains(1));
        assertTrue(articulationPoints.contains(2));

        // Verify bridges
        Set<Edge> bridges = result.getBridges();
        assertEquals(3, bridges.size());
        assertTrue(bridges.contains(new Edge(0, 1)));
        assertTrue(bridges.contains(new Edge(1, 2)));
        assertTrue(bridges.contains(new Edge(2, 3)));
    }

    @Test
    public void testGraphWithNoArticulationPoints() {
        /*
         * Graph structure (triangle):
         *       0
         *      / \
         *     1---2
         *
         * Articulation Points: None (it's a cycle, removing any vertex still leaves graph connected)
         * Bridges: None
         */
        int vertices = 3;
        List<List<Integer>> graph = createGraph(vertices);
        addEdge(graph, 0, 1);
        addEdge(graph, 1, 2);
        addEdge(graph, 2, 0);

        Result result = ArticulationPointsAndBridges.findArticulationPointsAndBridges(vertices, graph);

        // Verify no articulation points
        Set<Integer> articulationPoints = result.getArticulationPoints();
        assertEquals(0, articulationPoints.size());

        // Verify no bridges
        Set<Edge> bridges = result.getBridges();
        assertEquals(0, bridges.size());
    }

    @Test
    public void testComplexGraphWithArticulationPointsAndBridges() {
        /*
         * Graph structure:
         *       0 -------- 1 -------- 2
         *       |        / |          |
         *       |       /  |          |
         *       |      /   |          |
         *       |     /    |          |
         *       |    /     |          |
         *       3 ---------4          5
         *                  |          |
         *                  |          |
         *                  6----------7
         *
         * Articulation Points: 1, 2, 4, 6
         * Bridges: (1,2), (2,5), (4,6), (5,7)
         */
        int vertices = 8;
        List<List<Integer>> graph = createGraph(vertices);
        addEdge(graph, 0, 1);
        addEdge(graph, 0, 3);
        addEdge(graph, 1, 2);
        addEdge(graph, 1, 3);
        addEdge(graph, 1, 4);
        addEdge(graph, 3, 4);
        addEdge(graph, 2, 5);
        addEdge(graph, 4, 6);
        addEdge(graph, 5, 7);
        addEdge(graph, 6, 7);

        Result result = ArticulationPointsAndBridges.findArticulationPointsAndBridges(vertices, graph);

        // Verify articulation points
        Set<Integer> articulationPoints = result.getArticulationPoints();
        assertEquals(4, articulationPoints.size());
        assertTrue(articulationPoints.contains(1));
        assertTrue(articulationPoints.contains(2));
        assertTrue(articulationPoints.contains(4));
        assertTrue(articulationPoints.contains(6));

        // Verify bridges
        Set<Edge> bridges = result.getBridges();
        assertEquals(3, bridges.size());
        assertTrue(bridges.contains(new Edge(1, 2)));
        assertTrue(bridges.contains(new Edge(2, 5)));
        assertTrue(bridges.contains(new Edge(4, 6)));
    }

    @Test
    public void testDisconnectedGraph() {
        /*
         * Graph structure:
         *       0 -------- 1          2 -------- 3
         *
         * Articulation Points: None (each component is too small)
         * Bridges: (0,1), (2,3)
         */
        int vertices = 4;
        List<List<Integer>> graph = createGraph(vertices);
        addEdge(graph, 0, 1);
        addEdge(graph, 2, 3);

        Result result = ArticulationPointsAndBridges.findArticulationPointsAndBridges(vertices, graph);

        // Verify no articulation points (each component has only 2 vertices)
        Set<Integer> articulationPoints = result.getArticulationPoints();
        assertEquals(0, articulationPoints.size());

        // Verify bridges
        Set<Edge> bridges = result.getBridges();
        assertEquals(2, bridges.size());
        assertTrue(bridges.contains(new Edge(0, 1)));
        assertTrue(bridges.contains(new Edge(2, 3)));
    }

    @Test
    public void testStarGraph() {
        /*
         * Graph structure (star with center at 0):
         *           1
         *           |
         *       2---0---3
         *           |
         *           4
         *
         * Articulation Point: 0 (center)
         * Bridges: (0,1), (0,2), (0,3), (0,4)
         */
        int vertices = 5;
        List<List<Integer>> graph = createGraph(vertices);
        addEdge(graph, 0, 1);
        addEdge(graph, 0, 2);
        addEdge(graph, 0, 3);
        addEdge(graph, 0, 4);

        Result result = ArticulationPointsAndBridges.findArticulationPointsAndBridges(vertices, graph);

        // Verify articulation point
        Set<Integer> articulationPoints = result.getArticulationPoints();
        assertEquals(1, articulationPoints.size());
        assertTrue(articulationPoints.contains(0));

        // Verify bridges (all edges are bridges in a star graph)
        Set<Edge> bridges = result.getBridges();
        assertEquals(4, bridges.size());
        assertTrue(bridges.contains(new Edge(0, 1)));
        assertTrue(bridges.contains(new Edge(0, 2)));
        assertTrue(bridges.contains(new Edge(0, 3)));
        assertTrue(bridges.contains(new Edge(0, 4)));
    }

    @Test
    public void testSingleVertexGraph() {
        /*
         * Graph with single vertex (no edges)
         * Articulation Points: None
         * Bridges: None
         */
        int vertices = 1;
        List<List<Integer>> graph = createGraph(vertices);

        Result result = ArticulationPointsAndBridges.findArticulationPointsAndBridges(vertices, graph);

        // Verify no articulation points
        Set<Integer> articulationPoints = result.getArticulationPoints();
        assertEquals(0, articulationPoints.size());

        // Verify no bridges
        Set<Edge> bridges = result.getBridges();
        assertEquals(0, bridges.size());
    }

    @Test
    public void testEmptyGraph() {
        /*
         * Empty graph (no vertices)
         * Articulation Points: None
         * Bridges: None
         */
        int vertices = 0;
        List<List<Integer>> graph = createGraph(vertices);

        Result result = ArticulationPointsAndBridges.findArticulationPointsAndBridges(vertices, graph);

        // Verify no articulation points
        Set<Integer> articulationPoints = result.getArticulationPoints();
        assertEquals(0, articulationPoints.size());

        // Verify no bridges
        Set<Edge> bridges = result.getBridges();
        assertEquals(0, bridges.size());
    }

    @Test
    public void testBiconnectedGraph() {
        /*
         * Graph structure (square with diagonal):
         *       0 -------- 1
         *       | \        |
         *       |   \      |
         *       |     \    |
         *       |       \  |
         *       3 -------- 2
         *
         * Articulation Points: None (biconnected - no single point of failure)
         * Bridges: None
         */
        int vertices = 4;
        List<List<Integer>> graph = createGraph(vertices);
        addEdge(graph, 0, 1);
        addEdge(graph, 1, 2);
        addEdge(graph, 2, 3);
        addEdge(graph, 3, 0);
        addEdge(graph, 0, 2); // Diagonal

        Result result = ArticulationPointsAndBridges.findArticulationPointsAndBridges(vertices, graph);

        // Verify no articulation points
        Set<Integer> articulationPoints = result.getArticulationPoints();
        assertEquals(0, articulationPoints.size());

        // Verify no bridges
        Set<Edge> bridges = result.getBridges();
        assertEquals(0, bridges.size());
    }

    @Test
    public void testConvenienceMethodFindArticulationPoints() {
        int vertices = 5;
        List<List<Integer>> graph = createGraph(vertices);
        addEdge(graph, 0, 1);
        addEdge(graph, 1, 2);
        addEdge(graph, 2, 3);
        addEdge(graph, 3, 4);

        Set<Integer> articulationPoints = ArticulationPointsAndBridges.findArticulationPoints(vertices, graph);

        assertEquals(3, articulationPoints.size());
        assertTrue(articulationPoints.contains(1));
        assertTrue(articulationPoints.contains(2));
        assertTrue(articulationPoints.contains(3));
    }

    @Test
    public void testConvenienceMethodFindBridges() {
        int vertices = 3;
        List<List<Integer>> graph = createGraph(vertices);
        addEdge(graph, 0, 1);
        addEdge(graph, 1, 2);

        Set<Edge> bridges = ArticulationPointsAndBridges.findBridges(vertices, graph);

        assertEquals(2, bridges.size());
        assertTrue(bridges.contains(new Edge(0, 1)));
        assertTrue(bridges.contains(new Edge(1, 2)));
    }

    @Test
    public void testNegativeVerticesThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            ArticulationPointsAndBridges.findArticulationPointsAndBridges(-1, new ArrayList<>());
        });
    }

    @Test
    public void testNullGraphThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            ArticulationPointsAndBridges.findArticulationPointsAndBridges(5, null);
        });
    }

    @Test
    public void testEdgeEquality() {
        Edge edge1 = new Edge(1, 2);
        Edge edge2 = new Edge(2, 1);
        Edge edge3 = new Edge(1, 3);

        // Test symmetry: (1,2) should equal (2,1)
        assertEquals(edge1, edge2);
        assertEquals(edge1.hashCode(), edge2.hashCode());

        // Test inequality
        assertTrue(!edge1.equals(edge3));
    }
}
