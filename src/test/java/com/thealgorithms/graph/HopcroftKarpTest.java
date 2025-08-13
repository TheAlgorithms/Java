package com.thealgorithms.graph;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HopcroftKarpTest {

    private static List<List<Integer>> adj(int nLeft) {
        List<List<Integer>> g = new ArrayList<>(nLeft);
        for (int i = 0; i < nLeft; i++) g.add(new ArrayList<>());
        return g;
    }

    @Test
    @DisplayName("Empty graph has matching 0")
    void emptyGraph() {
        List<List<Integer>> g = adj(3);
        HopcroftKarp hk = new HopcroftKarp(3, 4, g);
        assertEquals(0, hk.maxMatching());
    }

    @Test
    @DisplayName("Single edge gives matching 1")
    void singleEdge() {
        List<List<Integer>> g = adj(1);
        g.get(0).add(0);
        HopcroftKarp hk = new HopcroftKarp(1, 1, g);
        assertEquals(1, hk.maxMatching());

        int[] L = hk.getLeftMatches();
        int[] R = hk.getRightMatches();
        assertEquals(0, L[0]);
        assertEquals(0, R[0]);
    }

    @Test
    @DisplayName("Disjoint edges match perfectly")
    void disjointEdges() {
        // L0-R0, L1-R1, L2-R2
        List<List<Integer>> g = adj(3);
        g.get(0).add(0);
        g.get(1).add(1);
        g.get(2).add(2);

        HopcroftKarp hk = new HopcroftKarp(3, 3, g);
        assertEquals(3, hk.maxMatching());

        int[] L = hk.getLeftMatches();
        int[] R = hk.getRightMatches();
        for (int i = 0; i < 3; i++) {
            assertEquals(i, L[i]);
            assertEquals(i, R[i]);
        }
    }

    @Test
    @DisplayName("Complete bipartite K(3,4) matches min(3,4)=3")
    void completeK34() {
        int nLeft = 3, nRight = 4;
        List<List<Integer>> g = adj(nLeft);
        for (int u = 0; u < nLeft; u++) {
            g.get(u).addAll(Arrays.asList(0, 1, 2, 3));
        }
        HopcroftKarp hk = new HopcroftKarp(nLeft, nRight, g);
        assertEquals(3, hk.maxMatching());

        // sanity: no two left vertices share the same matched right vertex
        int[] L = hk.getLeftMatches();
        boolean[] used = new boolean[nRight];
        for (int u = 0; u < nLeft; u++) {
            int v = L[u];
            if (v != -1) {
                assertFalse(used[v]);
                used[v] = true;
            }
        }
    }

    @Test
    @DisplayName("Non-square, sparse graph")
    void rectangularSparse() {
        // Left: 5, Right: 2
        // edges: L0-R0, L1-R1, L2-R0, L3-R1 (max matching = 2)
        List<List<Integer>> g = adj(5);
        g.get(0).add(0);
        g.get(1).add(1);
        g.get(2).add(0);
        g.get(3).add(1);
        // L4 isolated

        HopcroftKarp hk = new HopcroftKarp(5, 2, g);
        assertEquals(2, hk.maxMatching());

        int[] L = hk.getLeftMatches();
        int[] R = hk.getRightMatches();

        // Check consistency: if L[u]=v then R[v]=u
        for (int u = 0; u < 5; u++) {
            int v = L[u];
            if (v != -1) {
                assertEquals(u, R[v]);
            }
        }
    }

    @Test
    @DisplayName("Layering advantage case (chains of short augmenting paths)")
    void layeringAdvantage() {
        // Left 4, Right 4
        // Build a structure that benefits from BFS layering
        // L0: R0, R1
        // L1: R1, R2
        // L2: R2, R3
        // L3: R0, R3
        List<List<Integer>> g = adj(4);
        g.get(0).addAll(Arrays.asList(0, 1));
        g.get(1).addAll(Arrays.asList(1, 2));
        g.get(2).addAll(Arrays.asList(2, 3));
        g.get(3).addAll(Arrays.asList(0, 3));

        HopcroftKarp hk = new HopcroftKarp(4, 4, g);
        assertEquals(4, hk.maxMatching()); // perfect matching exists
    }
}