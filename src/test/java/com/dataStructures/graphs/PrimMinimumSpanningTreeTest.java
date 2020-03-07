package com.dataStructures.graphs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PrimMinimumSpanningTreeTest {

    @Test
    void testMinimumSpanningTree() {
        int[][] graph = new int[][]{
                {0, 2, 0, 6, 0},
                {2, 0, 3, 8, 5},
                {0, 3, 0, 0, 7},
                {6, 8, 0, 0, 9},
                {0, 5, 7, 9, 0}};
        PrimMinimumSpanningTree minimumSpanningTree = new PrimMinimumSpanningTree(graph, 5);
        Assertions.assertEquals("0 - 1, weight: 2\n" +
                "1 - 2, weight: 3\n" +
                "0 - 3, weight: 6\n" +
                "1 - 4, weight: 5\n", minimumSpanningTree.toString());

        int[][] graph2 = new int[][]{
                {0, 2, 0, 0},
                {2, 0, 8, 4},
                {0, 8, 0, 5},
                {0, 4, 5, 0}};
        PrimMinimumSpanningTree minimumSpanningTree2 = new PrimMinimumSpanningTree(graph2, 4);
        Assertions.assertEquals("0 - 1, weight: 2\n" +
                "3 - 2, weight: 5\n" +
                "1 - 3, weight: 4\n", minimumSpanningTree2.toString());

    }
}
