package com.thealgorithms.datastructures.graphs;

import static org.junit.jupiter.api.Assertions.*;

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
        final var graph = WelshPowell.makeGraph(5, new int[][] {{0, 1}, {1, 2}, {2, 3}, {3, 4}, {4, 0}, {1, 3}});
        int[] colors = WelshPowell.findColoring(graph);
        assertTrue(isColoringValid(graph, colors));
        assertEquals(3, countDistinctColors(colors)); // Expect exactly 3 colors
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
    }

    @Test
    void testInvalidEdgeArray() {
        assertThrows(IllegalArgumentException.class, () -> { WelshPowell.makeGraph(3, new int[][] {{0}}); }, "Edge array must have exactly two elements");
    }

    @Test
    void testWithPreColoredVertex() {
        final var graph = WelshPowell.makeGraph(4, new int[][] {{0, 1}, {1, 2}, {2, 3}});
        // Simulate pre-coloring vertex 1 with color 0
        int[] colors = WelshPowell.findColoring(graph, 1, 0);
        assertTrue(isColoringValid(graph, colors));

        // Ensure that the pre-colored vertex retains its color
        assertEquals(0, colors[1]);

        // Check if other vertices are colored correctly
        assertTrue(countDistinctColors(colors) >= 2);

        // Additional check to ensure that all vertices are colored
        for (int color : colors) {
            assertTrue(color >= 0);
        }
    }

    private boolean isColoringValid(Graph graph, int[] colors) {
        for (int i = 0; i < graph.getNumVertices(); i++) {
            for (int neighbor : graph.getAdjList(i)) {
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
