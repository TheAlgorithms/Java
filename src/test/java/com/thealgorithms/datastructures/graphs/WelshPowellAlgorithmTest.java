package com.thealgorithms.datastructures.graphs;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class WelshPowellAlgorithmTest {

    @Test
    void testSimpleGraph() {
        WelshPowell.WPGraph graph = WelshPowell.createGraph(4);
        WelshPowell.addEdge(graph, 0, 1);
        WelshPowell.addEdge(graph, 1, 2);
        WelshPowell.addEdge(graph, 2, 3);
        int[] colors = WelshPowell.welshPowellColoring(graph);
        assertTrue(isValidColoring(graph, colors));
    }

    @Test
    void testDisconnectedGraph() {
        WelshPowell.WPGraph graph = WelshPowell.createGraph(3);
        int[] colors = WelshPowell.welshPowellColoring(graph);
        assertTrue(isValidColoring(graph, colors));
    }

    @Test
    void testCompleteGraph() {
        WelshPowell.WPGraph graph = WelshPowell.createGraph(3);
        WelshPowell.addEdge(graph, 0, 1);
        WelshPowell.addEdge(graph, 1, 2);
        WelshPowell.addEdge(graph, 2, 0);
        int[] colors = WelshPowell.welshPowellColoring(graph);
        assertTrue(isValidColoring(graph, colors));
    }

    private boolean isValidColoring(WelshPowell.WPGraph graph, int[] colors) {
        int numVertices = graph.getNumVertices();
        for (int i = 0; i < numVertices; i++) {
            for (int neighbor : graph.getAdjList(i)) {
                if (colors[i] == colors[neighbor]) {
                    return false;
                }
            }
        }
        return true;
    }
}
