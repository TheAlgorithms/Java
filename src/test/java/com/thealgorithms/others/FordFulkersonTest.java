package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class FordFulkersonTest {

    @Test
    void testMaxFlowSmallGraph() {
        // Small graph test case
        int[][] graph = {
            {0, 16, 13, 0, 0, 0},
            {0, 0, 10, 12, 0, 0},
            {0, 4, 0, 0, 14, 0},
            {0, 0, 9, 0, 0, 20},
            {0, 0, 0, 7, 0, 4},
            {0, 0, 0, 0, 0, 0}
        };
        int source = 0;
        int sink = 5;
        FordFulkerson maxFlow = new FordFulkerson();
        assertEquals(23, maxFlow.fordFulkerson(graph, source, sink));
    }

    @Test
    void testMaxFlowDisconnectedGraph() {
        // Disconnected graph test case (no path from source to sink)
        int[][] graph = {
            {0, 16, 0, 0, 0, 0},
            {0, 0, 10, 0, 0, 0},
            {0, 4, 0, 0, 14, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 7, 0, 0},
            {0, 0, 0, 0, 0, 0}
        };
        int source = 0;
        int sink = 5;
        FordFulkerson maxFlow = new FordFulkerson();
        assertEquals(0, maxFlow.fordFulkerson(graph, source, sink));
    }

    @Test
    void testMaxFlowMediumGraph() {
        // Medium graph test case
        int[][] graph = {
            {0, 10, 10, 0, 0, 0},
            {0, 0, 0, 4, 8, 0},
            {0, 0, 0, 5, 9, 0},
            {0, 0, 0, 0, 0, 10},
            {0, 0, 0, 6, 0, 10},
            {0, 0, 0, 0, 0, 0}
        };
        int source = 0;
        int sink = 5;
        FordFulkerson maxFlow = new FordFulkerson();
        assertEquals(19, maxFlow.fordFulkerson(graph, source, sink));
    }
}
