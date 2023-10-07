package com.thealgorithms.backtracking;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class HamiltonianCycleTest {
    @Test
    void testForFirstCase() {
        int graph[][] = {
            { 0, 1, 0, 1, 0 },
            { 1, 0, 1, 1, 1 },
            { 0, 1, 0, 0, 1 },
            { 1, 1, 0, 0, 1 },
            { 0, 1, 1, 1, 0 },
        };
        int vertices = 5;
        int output = HamiltonianCycle.findHamiltonianCycle(graph, vertices);
        assertEquals(output, 1);
    }

    @Test
    void testForSecondCase() {
        int graph[][] = {
            { 0, 1, 1, 1, 0 },
            { 1, 0, 1, 1, 0 },
            { 1, 1, 0, 1, 1 },
            { 1, 1, 1, 0, 1 },
            { 0, 0, 1, 1, 0 },
        };
        int vertices = 5;
        int output = HamiltonianCycle.findHamiltonianCycle(graph, vertices);
        assertEquals(output, 1);
    }

    @Test
    void testForThirdCase() {
        int graph[][] = {
            { 0, 1, 0, 1, 1, 0, 0 },
            { 1, 0, 1, 0, 0, 0, 0 },
            { 0, 1, 0, 1, 0, 0, 1 },
            { 1, 0, 1, 0, 0, 1, 0 },
            { 1, 0, 0, 0, 0, 1, 0 },
            { 0, 0, 0, 1, 1, 0, 1 },
            { 0, 0, 1, 0, 0, 1, 0 }
        };
        int vertices = 7;
        int output = HamiltonianCycle.findHamiltonianCycle(graph, vertices);
        assertEquals(output, 0);
    }

    @Test
    void testForFourthCase() {
        int graph[][] = {
            { 0, 1, 1, 0, 0, 0 },
            { 1, 0, 0, 1, 0, 0 },
            { 1, 0, 0, 1, 1, 1 },
            { 0, 1, 1, 0, 0, 0 },
            { 0, 0, 1, 0, 0, 1 },
            { 0, 0, 1, 0, 1, 0 }
        };
        int vertices = 6;
        int output = HamiltonianCycle.findHamiltonianCycle(graph, vertices);
        assertEquals(output, 0);
    }

    @Test
    void testForFifthCase() {
        int graph[][] = {
            { 0, 1, 1, 0, 1 },
            { 1, 0, 1, 1, 1 },
            { 1, 1, 0, 1, 0 },
            { 0, 1, 1, 0, 1 },
            { 1, 1, 0, 1, 0 }
        };
        int vertices = 5;
        int output = HamiltonianCycle.findHamiltonianCycle(graph, vertices);
        assertEquals(output, 1);
    }
}
