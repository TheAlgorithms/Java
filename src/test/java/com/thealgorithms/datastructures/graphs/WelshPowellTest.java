package com.thealgorithms.datastructures.graphs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.thealgorithms.datastructures.graphs.WelshPowell.Graph;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

class WelshPowellTest {

    @Test
    void testSimpleGraph() {
        final var graph = WelshPowell.makeGraph(4, new int[][] {{0, 1}, {1, 2}, {2, 3}});
        int[] colors = WelshPowell.findColoring(graph);
        assertTrue(isColoringValid(graph, colors));
        assertEquals(2, countDistinctColors(colors));
    }

    @Test
    void testDisconnectedGraph() {
        final var graph = WelshPowell.makeGraph(3, new int[][] {}); // No edges
        int[] colors = WelshPowell.findColoring(graph);
        assertTrue(isColoringValid(graph, colors));
        assertEquals(1, countDistinctColors(colors));
    }

    @Test
    void testCompleteGraph() {
        final var graph = WelshPowell.makeGraph(3, new int[][] {{0, 1}, {1, 2}, {2, 0}});
        int[] colors = WelshPowell.findColoring(graph);
        assertTrue(isColoringValid(graph, colors));
        assertEquals(3, countDistinctColors(colors));
    }

    @Test
    void testComplexGraph() {
        int[][] edges = {
            {0, 7},
            {0, 1},
            {1, 3},
            {2, 3},
            {3, 8},
            {3, 10},
            {4, 10},
            {4, 5},
            {5, 6},
            {6, 10},
            {6, 7},
            {7, 8},
            {7, 9},
            {7, 10},
            {8, 9},
            {9, 10},
        };

        final var graph = WelshPowell.makeGraph(11, edges); // 11 vertices from A (0) to K (10)
        int[] colors = WelshPowell.findColoring(graph);

        assertTrue(isColoringValid(graph, colors), "The coloring should be valid with no adjacent vertices sharing the same color.");
        assertEquals(3, countDistinctColors(colors), "The chromatic number of the graph should be 3.");
    }

    @Test
    void testNegativeVertices() {
        assertThrows(IllegalArgumentException.class, () -> { WelshPowell.makeGraph(-1, new int[][] {}); }, "Number of vertices cannot be negative");
    }

    @Test
    void testSelfLoop() {
        assertThrows(IllegalArgumentException.class, () -> { WelshPowell.makeGraph(3, new int[][] {{0, 0}}); }, "Self-loops are not allowed");
    }

    @Test
    void testInvalidVertex() {
        assertThrows(IllegalArgumentException.class, () -> { WelshPowell.makeGraph(3, new int[][] {{0, 3}}); }, "Vertex out of bounds");
        assertThrows(IllegalArgumentException.class, () -> { WelshPowell.makeGraph(3, new int[][] {{0, -1}}); }, "Vertex out of bounds");
    }

    @Test
    void testInvalidEdgeArray() {
        assertThrows(IllegalArgumentException.class, () -> { WelshPowell.makeGraph(3, new int[][] {{0}}); }, "Edge array must have exactly two elements");
    }

    @Test
    void testWithPreColoredVertex() {
        final var graph = WelshPowell.makeGraph(4, new int[][] {{0, 1}, {1, 2}, {2, 3}});
        int[] colors = WelshPowell.findColoring(graph);
        assertTrue(isColoringValid(graph, colors));
        assertTrue(countDistinctColors(colors) >= 2);
        for (int color : colors) {
            assertTrue(color >= 0);
        }
    }

    @Test
    void testLargeGraph() {
        int[][] edges = {{0, 1}, {1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 0}, {6, 7}, {7, 8}, {8, 6}, {9, 10}, {10, 11}, {11, 9}, {12, 13}, {13, 14}, {14, 15}};

        final var graph = WelshPowell.makeGraph(16, edges); // 16 vertices
        int[] colors = WelshPowell.findColoring(graph);
        assertTrue(isColoringValid(graph, colors));
        assertEquals(3, countDistinctColors(colors)); // Expecting a maximum of 3 colors
    }

    @Test
    void testStarGraph() {
        int[][] edges = {{0, 1}, {0, 2}, {0, 3}, {0, 4}};

        final var graph = WelshPowell.makeGraph(5, edges); // 5 vertices in a star formation
        int[] colors = WelshPowell.findColoring(graph);
        assertTrue(isColoringValid(graph, colors));
        assertEquals(2, countDistinctColors(colors)); // Star graph can be colored with 2 colors
    }

    private boolean isColoringValid(Graph graph, int[] colors) {
        if (Arrays.stream(colors).anyMatch(n -> n < 0)) {
            return false;
        }
        for (int i = 0; i < graph.getNumVertices(); i++) {
            for (int neighbor : graph.getAdjacencyList(i)) {
                if (i != neighbor && colors[i] == colors[neighbor]) {
                    return false; // Adjacent vertices have the same color
                }
            }
        }
        return true; // No adjacent vertices share the same color
    }

    private int countDistinctColors(int[] colors) {
        return (int) Arrays.stream(colors).distinct().count();
    }
}
