package com.thealgorithms.datastructures.graphs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.thealgorithms.datastructures.graphs.WelshPowell.WPGraph;
import org.junit.jupiter.api.Test;

class WelshPowellAlgorithmTest {

    @Test
    void testSimpleGraph() {
        WPGraph graph = new WPGraph(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        int[] colors = graph.welshPowellColoring();
        assertEquals(true, isValidColoring(graph, colors));
    }

    @Test
    void testDisconnectedGraph() {
        WPGraph graph = new WPGraph(3);
        // Disconnected graph, no edges
        int[] colors = graph.welshPowellColoring();
        assertEquals(true, isValidColoring(graph, colors));
    }

    @Test
    void testCompleteGraph() {
        WPGraph graph = new WPGraph(3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        int[] colors = graph.welshPowellColoring();
        assertEquals(true, isValidColoring(graph, colors));
    }

    private boolean isValidColoring(WPGraph graph, int[] colors) {
        for (int i = 0; i < graph.adjLists.length; i++) {
            for (int neighbor : graph.adjLists[i]) {
                if (colors[i] == colors[neighbor]) {
                    return false;
                }
            }
        }
        return true;
    }
}
