package com.thealgorithms.tree;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Answers.values;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CentroidDecompositionTest {
    private CentroidDecomposition cd;

    @BeforeEach
    void setUp(){
        cd = new CentroidDecomposition(16);
        cd.addEdge(0, 1);
        cd.addEdge(0, 2);
        cd.addEdge(0, 3);
        cd.addEdge(1, 4);
        cd.addEdge(1, 5);
        cd.addEdge(2, 6);
        cd.addEdge(2, 7);
        cd.addEdge(3, 8);
        cd.addEdge(8, 9);
        cd.addEdge(8, 10);
        cd.addEdge(6, 11);
        cd.addEdge(11, 12);
        cd.addEdge(11, 13);
        cd.addEdge(13, 14);
        cd.addEdge(14, 15);

        /*
         *          0
                /   |    \
               1    2     3
              / \  / \     \
             4  5  6  7     8
                      |    / \
                      11  9  10
                     /  \
                    12  13
                         \
                         14
                          \
                           15
   
         */
    }

    @Test
    void testFindSubtreeSizes(){
        boolean[] visited = new boolean[16];
        int[] subtreeSizes = new int[16];

        cd.findSubtreeSizes(3, visited, subtreeSizes);
        assertEquals(subtreeSizes[8], 3);
        assertEquals(subtreeSizes[0], 12);
    }



}
