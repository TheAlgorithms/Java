package com.thealgorithms.tree;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CentroidDecompositionTest {
    private CentroidDecomposition cd;

    @BeforeEach
    void setUp(){
        cd = new CentroidDecomposition(16, 0);
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


         * centroid Tree (starting at 0):
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
    void startingNodeIsInRangeWhenRandomCtorUsed() {
        int n = 32;
        CentroidDecomposition rnd = new CentroidDecomposition(n);
        int s = rnd.getStartingNode();
        assertTrue(0 <= s && s < n, "starting node must be in [0, n)");
    }

    @Test
    void invalidStartingNodeThrows() {
        assertThrows(IllegalArgumentException.class, () -> new CentroidDecomposition(10, -1));
        assertThrows(IllegalArgumentException.class, () -> new CentroidDecomposition(10, 10));
    }

    @Test
    void IllegalArgumentThrows(){
        assertThrows(IllegalArgumentException.class, () -> {
            new CentroidDecomposition(10, 99);
        });
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
    void getCentroidChildrenReturnsDirectedChildrenOnly() {
        cd.build();

        // children of 0 in centroid tree should be [1, 11, 8] in any order
        List<Integer> children0 = cd.getCentroidChildren(0).stream().sorted().collect(Collectors.toList());
        assertEquals(Arrays.asList(1, 8, 11), children0);

        // children of 11 should be [2, 12, 14]
        List<Integer> children11 = cd.getCentroidChildren(11).stream().sorted().collect(Collectors.toList());
        assertEquals(Arrays.asList(2, 12, 14), children11);

        // leaves have no children
        assertTrue(cd.getCentroidChildren(4).isEmpty());
        assertTrue(cd.getCentroidChildren(9).isEmpty());
        assertTrue(cd.getCentroidChildren(15).isEmpty());
    }
    @Test
    void buildSetsExpectedParentsForKeyNodes() {
        cd.build();
        // parent(11) = 0, parent(1) = 0, parent(8) = 0
        assertEquals(0, cd.getParent(11));
        assertEquals(0, cd.getParent(1));
        assertEquals(0, cd.getParent(8));

        assertEquals(1, cd.getParent(4));
        assertEquals(1, cd.getParent(5));

        assertEquals(11, cd.getParent(2));
        assertEquals(11, cd.getParent(12));
        assertEquals(11, cd.getParent(14));

        assertEquals(2, cd.getParent(6));
        assertEquals(2, cd.getParent(7));

        assertEquals(14, cd.getParent(13));
        assertEquals(14, cd.getParent(15));

        assertEquals(8, cd.getParent(3));
        assertEquals(8, cd.getParent(9));
        assertEquals(8, cd.getParent(10));
    }

    @Test
    void centroidAdjacencyFor8And11MatchesIgnoringOrder() {
        cd.build();
        List<Integer>[] cg = cd.getCentroidTree();

        List<Integer> actual8 = new ArrayList<>(cg[8]);
        List<Integer> actual11 = new ArrayList<>(cg[11]);

        List<Integer> expected8 = Arrays.asList(0, 3, 9, 10);
        List<Integer> expected11 = Arrays.asList(0, 2, 12, 14);

        Collections.sort(actual8);
        Collections.sort(actual11);
        List<Integer> e8 = expected8.stream().sorted().collect(Collectors.toList());
        List<Integer> e11 = expected11.stream().sorted().collect(Collectors.toList());

        assertEquals(e8, actual8);
        assertEquals(e11, actual11);
    }

    @Test
    void resetClearsCentroidData() {
        cd.build();
        cd.reset();

        List<Integer>[] cg = cd.getCentroidTree();
        for (int i = 0; i < cg.length; i++) {
            assertTrue(cg[i].isEmpty(), "centroid adjacency must be empty after reset");
            assertEquals(-1, cd.getParent(i), "parent must be -1 after reset");
        }

        cd.build();
        assertEquals(0, cd.getParent(11));
    }


    @Test
    void forEachAncestorVisitsCorrectChain() {
        cd.build();

        int[] testNodes = {0, 3, 7, 11, 15};

        for (int node : testNodes) {

            List<Integer> visited = new ArrayList<>();
            cd.forEachAncestor(node, visited::add);

            List<Integer> expected = new ArrayList<>();
            int curr = node;
            while (curr != -1) {
                expected.add(curr);
                curr = cd.getParent(curr);
            }

            assertEquals(expected, visited,
                "forEachAncestor wrong for node " + node);
        }
    }

    @Test
    void buildingFromDifferentStartNodesYieldsValidTrees() {
        CentroidDecomposition a = new CentroidDecomposition(16, 0);
        copyEdges(cd, a);
        a.build();
        assertValidCentroidTree(a, 16);

        CentroidDecomposition b = new CentroidDecomposition(16, 7);
        copyEdges(cd, b);
        b.build();
        assertValidCentroidTree(b, 16);
    }

    private static void assertValidCentroidTree(CentroidDecomposition cd, int n) {
        int roots = 0;
        int edges = 0;

        List<Integer>[] cg = cd.getCentroidTree();

        for (int v = 0; v < n; v++) {
            if (cd.getParent(v) == -1) {
                roots++;
            }
            edges += cg[v].size();
        }

        // undirected edges counted twice
        edges /= 2;

        assertEquals(1, roots, "must have exactly one root");
        assertEquals(n - 1, edges, "centroid tree must have n-1 edges");
    }
    
    private static void copyEdges(CentroidDecomposition from, CentroidDecomposition to) {
        to.addEdgeTree(0, 1);
        to.addEdgeTree(0, 2);
        to.addEdgeTree(0, 3);
        to.addEdgeTree(1, 4);
        to.addEdgeTree(1, 5);
        to.addEdgeTree(2, 6);
        to.addEdgeTree(2, 7);
        to.addEdgeTree(3, 8);
        to.addEdgeTree(8, 9);
        to.addEdgeTree(8, 10);
        to.addEdgeTree(6, 11);
        to.addEdgeTree(11, 12);
        to.addEdgeTree(11, 13);
        to.addEdgeTree(13, 14);
        to.addEdgeTree(14, 15);
    }

    @Test
    void testBuildCentroidTree() {
        cd.build();
        List<Integer>[] centroidTree = cd.getCentroidTree();
        List<Integer> correctEight = new ArrayList<Integer>();
        List<Integer> correctEleven = new ArrayList<Integer>();
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

        for (int j = 0; j < centroidTree[11].size(); j++) {
            assertEquals(correctEleven.get(j), centroidTree[11].get(j));
        }

    }
}
