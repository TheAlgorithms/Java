package com.thealgorithms.tree;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class CentroidDecompositionTest {
    private CentroidDecomposition cd;

    @BeforeEach
    void setUp(){
        cd = new CentroidDecomposition(16);
        cd.addEdgeTree(0, 1);
        cd.addEdgeTree(0, 2);
        cd.addEdgeTree(0, 3);
        cd.addEdgeTree(1, 4);
        cd.addEdgeTree(1, 5);
        cd.addEdgeTree(2, 6);
        cd.addEdgeTree(2, 7);
        cd.addEdgeTree(3, 8);
        cd.addEdgeTree(8, 9);
        cd.addEdgeTree(8, 10);
        cd.addEdgeTree(6, 11);
        cd.addEdgeTree(11, 12);
        cd.addEdgeTree(11, 13);
        cd.addEdgeTree(13, 14);
        cd.addEdgeTree(14, 15);


        /*
         * Initial Tree:
         *           0
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


         * centroid Tree:
                    0
             /      |       \
            1      11        8
          /   \   / | \     / | \
         4     5 2 12 14   3  9 10
                / \    / \
               7   6  15 13
   
         */
    }

    @Test
    void testFindSubtreeSizes(){
        // int[] subtreeSizes = new int[n];

        cd.findSubtreeSizes(3);
        int[] subtreeSizes = cd.getSubtreeSizes();
        assertEquals(subtreeSizes[8], 3);
        assertEquals(subtreeSizes[0], 12);
    }

    @Test
    void IllegalArgumentThrows(){
        assertThrows(IllegalArgumentException.class, () -> {
            new CentroidDecomposition(10, 99);
        });
    }

    @Test
    void testGetParent(){
        cd.build();
        int three = 8;
        int Twelve = 11;
        int Five = 1;
        int Eleven = 0;

        assertEquals(cd.getParent(3), three);
        assertEquals(cd.getParent(12), Twelve);
        assertEquals(cd.getParent(5), Five);
        assertEquals(cd.getParent(11), Eleven);

    }

    @RepeatedTest(100)
    void testBuildCentroidTree(){
        cd.build();
        ArrayList<Integer>[] centroidTree = cd.getCentroidTree();
        ArrayList<Integer> correctEight = new ArrayList<Integer>();
        ArrayList<Integer> correctEleven = new ArrayList<Integer>();
        correctEight.add(0);
        correctEight.add(3);
        correctEight.add(9);
        correctEight.add(10);

        correctEleven.add(0);
        correctEleven.add(2);
        correctEleven.add(12);
        correctEleven.add(14);

        for (int j = 0; j < centroidTree[8].size(); j++) {
            assertEquals(correctEight.get(j), centroidTree[8].get(j));
        }

        for (int j = 0; j < centroidTree[8].size(); j++) {
            assertEquals(correctEleven.get(j), centroidTree[11].get(j));
        }

    }
}
