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
}
