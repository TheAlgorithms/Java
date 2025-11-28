package com.thealgorithms.greedyalgorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class KruskalMSTTest {

    @Test
    void testSimpleConnectedGraph() {
        int n = 4;
        List<KruskalMST.Edge> edges = List.of(
            new KruskalMST.Edge(0, 1, 10),
            new KruskalMST.Edge(0, 2, 6),
            new KruskalMST.Edge(0, 3, 5),
            new KruskalMST.Edge(1, 3, 15),
            new KruskalMST.Edge(2, 3, 4)
        );

        int mstWeight = KruskalMST.minimumSpanningTreeWeight(n, edges);
        // Known MST: edges (2-3, 0-3, 0-1) = 4 + 5 + 10 = 19
        assertEquals(19, mstWeight);
    }

    @Test
    void testDisconnectedGraph() {
        int n = 4;
        List<KruskalMST.Edge> edges = List.of(
            new KruskalMST.Edge(0, 1, 3),
            new KruskalMST.Edge(2, 3, 5)
        );

        int mstWeight = KruskalMST.minimumSpanningTreeWeight(n, edges);
        assertEquals(-1, mstWeight);
    }
}
