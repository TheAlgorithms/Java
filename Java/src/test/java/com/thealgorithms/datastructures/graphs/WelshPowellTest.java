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

    // The following test originates from the following website : https://www.geeksforgeeks.org/welsh-powell-graph-colouring-algorithm/
    @Test
    void testComplexGraph() {
        int[][] edges = {
            {0, 7}, // A-H
            {0, 1}, // A-B
            {1, 3}, // B-D
            {2, 3}, // C-D
            {3, 8}, // D-I
            {3, 10}, // D-K
            {4, 10}, // E-K
            {4, 5}, // E-F
            {5, 6}, // F-G
            {6, 10}, // G-K
            {6, 7}, // G-H
            {7, 8}, // H-I
            {7, 9}, // H-J
            {7, 10}, // H-K
            {8, 9}, // I-J
            {9, 10}, // J-K
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
        // Create a linear graph with 4 vertices and edges connecting them in sequence
        final var graph = WelshPowell.makeGraph(4, new int[][] {{0, 1}, {1, 2}, {2, 3}});

        // Apply the Welsh-Powell coloring algorithm to the graph
        int[] colors = WelshPowell.findColoring(graph);

        // Validate that the coloring is correct (no two adjacent vertices have the same color)
        assertTrue(isColoringValid(graph, colors));

        // Check if the algorithm has used at least 2 colors (expected for a linear graph)
        assertTrue(countDistinctColors(colors) >= 2);

        // Verify that all vertices have been assigned a color
        for (int color : colors) {
            assertTrue(color >= 0);
        }
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
