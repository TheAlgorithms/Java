package com.thealgorithms.datastructures.graphs;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.thealgorithms.datastructures.graphs.WelshPowell.WPGraph;
import org.junit.jupiter.api.Test;

class WelshPowellAlgorithmTest {

    @Test
    void testSimpleGraph() {
        int[][] edges = {{0, 1}, {1, 2}, {2, 3}};
        WPGraph graph = WelshPowell.makeGraph(4, edges);
        int[] colors = WelshPowell.welshPowellColoring(graph);
        assertTrue(isValidColoring(graph, colors));
    }

    @Test
    void testDisconnectedGraph() {
        WPGraph graph = WelshPowell.makeGraph(3, new int[][] {}); // No edges
        int[] colors = WelshPowell.welshPowellColoring(graph);
        assertTrue(isValidColoring(graph, colors));
    }

    @Test
    void testCompleteGraph() {
        int[][] edges = {{0, 1}, {1, 2}, {2, 0}};
        WPGraph graph = WelshPowell.makeGraph(3, edges);
        int[] colors = WelshPowell.welshPowellColoring(graph);
        assertTrue(isValidColoring(graph, colors));
    }

    private boolean isValidColoring(WPGraph graph, int[] colors) {
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
}
