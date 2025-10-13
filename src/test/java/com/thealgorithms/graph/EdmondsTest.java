package com.thealgorithms.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class EdmondsTest {

    @Test
    void testSimpleGraphNoCycle() {
        int n = 4;
        int root = 0;
        List<Edmonds.Edge> edges = new ArrayList<>();
        edges.add(new Edmonds.Edge(0, 1, 10));
        edges.add(new Edmonds.Edge(0, 2, 1));
        edges.add(new Edmonds.Edge(2, 1, 2));
        edges.add(new Edmonds.Edge(2, 3, 5));

        // Expected arborescence edges: (0,2), (2,1), (2,3)
        // Weights: 1 + 2 + 5 = 8
        long result = Edmonds.findMinimumSpanningArborescence(n, edges, root);
        assertEquals(8, result);
    }

    @Test
    void testGraphWithOneCycle() {
        int n = 4;
        int root = 0;
        List<Edmonds.Edge> edges = new ArrayList<>();
        edges.add(new Edmonds.Edge(0, 1, 10));
        edges.add(new Edmonds.Edge(2, 1, 4));
        edges.add(new Edmonds.Edge(1, 2, 5));
        edges.add(new Edmonds.Edge(2, 3, 6));

        // Min edges: (2,1, w=4), (1,2, w=5), (2,3, w=6)
        // Cycle: 1 -> 2 -> 1, cost = 4 + 5 = 9
        // Contract {1,2} to C.
        // New edge (0,C) with w = 10 - min_in(1) = 10 - 4 = 6
        // New edge (C,3) with w = 6
        // Contracted MSA cost = 6 + 6 = 12
        // Total cost = cycle_cost + contracted_msa_cost = 9 + 12 = 21
        long result = Edmonds.findMinimumSpanningArborescence(n, edges, root);
        assertEquals(21, result);
    }

    @Test
    void testComplexGraphWithCycle() {
        int n = 6;
        int root = 0;
        List<Edmonds.Edge> edges = new ArrayList<>();
        edges.add(new Edmonds.Edge(0, 1, 10));
        edges.add(new Edmonds.Edge(0, 2, 20));
        edges.add(new Edmonds.Edge(1, 2, 5));
        edges.add(new Edmonds.Edge(2, 3, 10));
        edges.add(new Edmonds.Edge(3, 1, 3));
        edges.add(new Edmonds.Edge(1, 4, 7));
        edges.add(new Edmonds.Edge(3, 4, 2));
        edges.add(new Edmonds.Edge(4, 5, 5));

        // Min edges: (3,1,3), (1,2,5), (2,3,10), (3,4,2), (4,5,5)
        // Cycle: 1->2->3->1, cost = 5+10+3=18
        // Contract {1,2,3} to C.
        // Edge (0,1,10) -> (0,C), w = 10-3=7
        // Edge (0,2,20) -> (0,C), w = 20-5=15. Min is 7.
        // Edge (1,4,7) -> (C,4,7)
        // Edge (3,4,2) -> (C,4,2). Min is 2.
        // Edge (4,5,5) -> (4,5,5)
        // Contracted MSA: (0,C,7), (C,4,2), (4,5,5). Cost = 7+2+5=14
        // Total cost = 18 + 14 = 32
        long result = Edmonds.findMinimumSpanningArborescence(n, edges, root);
        assertEquals(32, result);
    }

    @Test
    void testUnreachableNode() {
        int n = 4;
        int root = 0;
        List<Edmonds.Edge> edges = new ArrayList<>();
        edges.add(new Edmonds.Edge(0, 1, 10));
        edges.add(new Edmonds.Edge(2, 3, 5)); // Node 2 and 3 are unreachable from root 0

        long result = Edmonds.findMinimumSpanningArborescence(n, edges, root);
        assertEquals(-1, result);
    }

    @Test
    void testNoEdgesToNonRootNodes() {
        int n = 3;
        int root = 0;
        List<Edmonds.Edge> edges = new ArrayList<>();
        edges.add(new Edmonds.Edge(0, 1, 10)); // Node 2 is unreachable

        long result = Edmonds.findMinimumSpanningArborescence(n, edges, root);
        assertEquals(-1, result);
    }

    @Test
    void testSingleNode() {
        int n = 1;
        int root = 0;
        List<Edmonds.Edge> edges = new ArrayList<>();

        long result = Edmonds.findMinimumSpanningArborescence(n, edges, root);
        assertEquals(0, result);
    }

    @Test
    void testInvalidInputThrowsException() {
        List<Edmonds.Edge> edges = new ArrayList<>();

        assertThrows(IllegalArgumentException.class,
                () -> Edmonds.findMinimumSpanningArborescence(0, edges, 0));
        assertThrows(IllegalArgumentException.class,
                () -> Edmonds.findMinimumSpanningArborescence(5, edges, -1));
        assertThrows(IllegalArgumentException.class,
                () -> Edmonds.findMinimumSpanningArborescence(5, edges, 5));
    }
}