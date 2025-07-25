package com.thealgorithms.datastructures.disjointsetunion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class DisjointSetUnionTest {

    @Test
    public void testMakeSet() {
        DisjointSetUnion<Integer> dsu = new DisjointSetUnion<>();
        Node<Integer> node = dsu.makeSet(1);
        assertNotNull(node);
        assertEquals(node, node.parent);
    }

    @Test
    public void testUnionFindSet() {
        DisjointSetUnion<Integer> dsu = new DisjointSetUnion<>();
        Node<Integer> node1 = dsu.makeSet(1);
        Node<Integer> node2 = dsu.makeSet(2);
        Node<Integer> node3 = dsu.makeSet(3);
        Node<Integer> node4 = dsu.makeSet(4);

        dsu.unionSets(node1, node2);
        dsu.unionSets(node3, node2);
        dsu.unionSets(node3, node4);
        dsu.unionSets(node1, node3);

        Node<Integer> root1 = dsu.findSet(node1);
        Node<Integer> root2 = dsu.findSet(node2);
        Node<Integer> root3 = dsu.findSet(node3);
        Node<Integer> root4 = dsu.findSet(node4);

        assertEquals(node1, node1.parent);
        assertEquals(node1, node2.parent);
        assertEquals(node1, node3.parent);
        assertEquals(node1, node4.parent);

        assertEquals(node1, root1);
        assertEquals(node1, root2);
        assertEquals(node1, root3);
        assertEquals(node1, root4);

        assertEquals(1, node1.rank);
        assertEquals(0, node2.rank);
        assertEquals(0, node3.rank);
        assertEquals(0, node4.rank);
    }

    @Test
    public void testFindSetOnSingleNode() {
        DisjointSetUnion<String> dsu = new DisjointSetUnion<>();
        Node<String> node = dsu.makeSet("A");
        assertEquals(node, dsu.findSet(node));
    }

    @Test
    public void testUnionAlreadyConnectedNodes() {
        DisjointSetUnion<Integer> dsu = new DisjointSetUnion<>();
        Node<Integer> node1 = dsu.makeSet(1);
        Node<Integer> node2 = dsu.makeSet(2);
        Node<Integer> node3 = dsu.makeSet(3);

        dsu.unionSets(node1, node2);
        dsu.unionSets(node2, node3);

        // Union nodes that are already connected
        dsu.unionSets(node1, node3);

        // All should have the same root
        Node<Integer> root = dsu.findSet(node1);
        assertEquals(root, dsu.findSet(node2));
        assertEquals(root, dsu.findSet(node3));
    }

    @Test
    public void testRankIncrease() {
        DisjointSetUnion<Integer> dsu = new DisjointSetUnion<>();
        Node<Integer> node1 = dsu.makeSet(1);
        Node<Integer> node2 = dsu.makeSet(2);
        Node<Integer> node3 = dsu.makeSet(3);
        Node<Integer> node4 = dsu.makeSet(4);

        dsu.unionSets(node1, node2); // rank of node1 should increase
        dsu.unionSets(node3, node4); // rank of node3 should increase
        dsu.unionSets(node1, node3); // union two trees of same rank -> rank increases

        assertEquals(2, dsu.findSet(node1).rank);
    }

    @Test
    public void testMultipleMakeSets() {
        DisjointSetUnion<Integer> dsu = new DisjointSetUnion<>();
        Node<Integer> node1 = dsu.makeSet(1);
        Node<Integer> node2 = dsu.makeSet(2);
        Node<Integer> node3 = dsu.makeSet(3);

        assertNotEquals(node1, node2);
        assertNotEquals(node2, node3);
        assertNotEquals(node1, node3);

        assertEquals(node1, node1.parent);
        assertEquals(node2, node2.parent);
        assertEquals(node3, node3.parent);
    }

    @Test
    public void testPathCompression() {
        DisjointSetUnion<Integer> dsu = new DisjointSetUnion<>();
        Node<Integer> node1 = dsu.makeSet(1);
        Node<Integer> node2 = dsu.makeSet(2);
        Node<Integer> node3 = dsu.makeSet(3);

        dsu.unionSets(node1, node2);
        dsu.unionSets(node2, node3);

        // After findSet, path compression should update parent to root directly
        Node<Integer> root = dsu.findSet(node3);
        assertEquals(root, node1);
        assertEquals(node1, node3.parent);
    }

    @Test
    public void testUnionByRankSmallerToLarger() {
        DisjointSetUnion<Integer> dsu = new DisjointSetUnion<>();
        Node<Integer> node1 = dsu.makeSet(1);
        Node<Integer> node2 = dsu.makeSet(2);
        Node<Integer> node3 = dsu.makeSet(3);

        // Create tree with node1 as root and rank 1
        dsu.unionSets(node1, node2);
        assertEquals(1, node1.rank);
        assertEquals(0, node2.rank);

        // Union single node (rank 0) with tree (rank 1)
        // Smaller rank tree should attach to larger rank tree
        dsu.unionSets(node3, node1);
        assertEquals(node1, dsu.findSet(node3));
        assertEquals(1, node1.rank); // Rank should not increase
    }

    @Test
    public void testUnionByRankEqualRanks() {
        DisjointSetUnion<Integer> dsu = new DisjointSetUnion<>();
        Node<Integer> node1 = dsu.makeSet(1);
        Node<Integer> node2 = dsu.makeSet(2);
        Node<Integer> node3 = dsu.makeSet(3);
        Node<Integer> node4 = dsu.makeSet(4);

        // Create two trees of equal rank (1)
        dsu.unionSets(node1, node2);
        dsu.unionSets(node3, node4);
        assertEquals(1, node1.rank);
        assertEquals(1, node3.rank);

        // Union two trees of equal rank
        dsu.unionSets(node1, node3);
        Node<Integer> root = dsu.findSet(node1);
        assertEquals(2, root.rank); // Rank should increase by 1
    }

    @Test
    public void testLargeChainPathCompression() {
        DisjointSetUnion<Integer> dsu = new DisjointSetUnion<>();
        Node<Integer> node1 = dsu.makeSet(1);
        Node<Integer> node2 = dsu.makeSet(2);
        Node<Integer> node3 = dsu.makeSet(3);
        Node<Integer> node4 = dsu.makeSet(4);
        Node<Integer> node5 = dsu.makeSet(5);

        // Create a long chain: 1 -> 2 -> 3 -> 4 -> 5
        dsu.unionSets(node1, node2);
        dsu.unionSets(node2, node3);
        dsu.unionSets(node3, node4);
        dsu.unionSets(node4, node5);

        // Find from the deepest node
        Node<Integer> root = dsu.findSet(node5);

        // Path compression should make all nodes point directly to root
        assertEquals(root, node5.parent);
        assertEquals(root, node4.parent);
        assertEquals(root, node3.parent);
        assertEquals(root, node2.parent);
        assertEquals(root, node1.parent);
    }

    @Test
    public void testMultipleDisjointSets() {
        DisjointSetUnion<Integer> dsu = new DisjointSetUnion<>();
        Node<Integer> node1 = dsu.makeSet(1);
        Node<Integer> node2 = dsu.makeSet(2);
        Node<Integer> node3 = dsu.makeSet(3);
        Node<Integer> node4 = dsu.makeSet(4);
        Node<Integer> node5 = dsu.makeSet(5);
        Node<Integer> node6 = dsu.makeSet(6);

        // Create two separate components
        dsu.unionSets(node1, node2);
        dsu.unionSets(node2, node3);

        dsu.unionSets(node4, node5);
        dsu.unionSets(node5, node6);

        // Verify they are separate
        assertEquals(dsu.findSet(node1), dsu.findSet(node2));
        assertEquals(dsu.findSet(node2), dsu.findSet(node3));
        assertEquals(dsu.findSet(node4), dsu.findSet(node5));
        assertEquals(dsu.findSet(node5), dsu.findSet(node6));

        assertNotEquals(dsu.findSet(node1), dsu.findSet(node4));
        assertNotEquals(dsu.findSet(node3), dsu.findSet(node6));
    }

    @Test
    public void testEmptyValues() {
        DisjointSetUnion<String> dsu = new DisjointSetUnion<>();
        Node<String> emptyNode = dsu.makeSet("");
        Node<String> nullNode = dsu.makeSet(null);

        assertEquals(emptyNode, dsu.findSet(emptyNode));
        assertEquals(nullNode, dsu.findSet(nullNode));

        dsu.unionSets(emptyNode, nullNode);
        assertEquals(dsu.findSet(emptyNode), dsu.findSet(nullNode));
    }
}
