package com.thealgorithms.datastructures.graphs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.thealgorithms.datastructures.graphs.WelshPowell.WPGraph;


class WelshPowellAlgorithmTest {

    @Test
    void testSimpleGraph() {
        WPGraph graph = WelshPowell.createGraph(4);
        WelshPowell.addEdge(graph, 0, 1);
        WelshPowell.addEdge(graph, 1, 2);
        WelshPowell.addEdge(graph, 2, 3);
        int[] colors = WelshPowell.welshPowellColoring(graph);
        assertEquals(true, isValidColoring(graph, colors));
    }

    @Test
    void testDisconnectedGraph() {
        WPGraph graph = WelshPowell.createGraph(3);
        int[] colors = WelshPowell.welshPowellColoring(graph);
        assertEquals(true, isValidColoring(graph, colors));
    }

    @Test
    void testCompleteGraph() {
        WPGraph graph = WelshPowell.createGraph(3);
        WelshPowell.addEdge(graph, 0, 1);
        WelshPowell.addEdge(graph, 1, 2);
        WelshPowell.addEdge(graph, 2, 0);
        int[] colors = WelshPowell.welshPowellColoring(graph);
        assertEquals(true, isValidColoring(graph, colors));
    }

    private boolean isValidColoring(WelshPowell.WPGraph graph, int[] colors) {
        int numVertices = WelshPowell.getNumVertices(graph);
        for (int i = 0; i < numVertices; i++) {
            for (int neighbor : WelshPowell.getAdjList(graph, i)) {
                if (i != neighbor && colors[i] == colors[neighbor]) {
                    return false;
                }
            }
        }
        return true;
    }
}
