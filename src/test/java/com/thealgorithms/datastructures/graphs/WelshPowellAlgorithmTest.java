package com.thealgorithms.datastructures.graphs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.thealgorithms.datastructures.graphs.WelshPowell.Graph;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

class WelshPowellAlgorithmTest {

    @Test
    void testSimpleGraph() {
        int[][] edges = {{0, 1}, {1, 2}, {2, 3}};
        Graph graph = WelshPowell.makeGraph(4, edges);
        int[] colors = WelshPowell.findColoring(graph);
        assertTrue(isColoringValid(graph, colors));
        assertEquals(2, countDistinctColors(colors));
    }

    @Test
    void testDisconnectedGraph() {
        Graph graph = WelshPowell.makeGraph(3, new int[][] {}); // No edges
        int[] colors = WelshPowell.findColoring(graph);
        assertTrue(isColoringValid(graph, colors));
        assertEquals(1, countDistinctColors(colors));
    }

    @Test
    void testCompleteGraph() {
        int[][] edges = {{0, 1}, {1, 2}, {2, 0}};
        Graph graph = WelshPowell.makeGraph(3, edges);
        int[] colors = WelshPowell.findColoring(graph);
        assertTrue(isColoringValid(graph, colors));
        assertEquals(3, countDistinctColors(colors));
    }

    @Test
    void testComplexGraph() {
        int[][] edges = {{0, 1}, {1, 2}, {2, 3}, {3, 4}, {4, 0}, {1, 3}};
        Graph graph = WelshPowell.makeGraph(5, edges);
        int[] colors = WelshPowell.findColoring(graph);
        assertTrue(isColoringValid(graph, colors));
        // The expected number of colors may vary depending on the graph structure
        assertTrue(countDistinctColors(colors) >= 3);
    }

    private boolean isColoringValid(Graph graph, int[] colors) {
        int numVertices = graph.getNumVertices();
        for (int i = 0; i < numVertices; i++) {
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
