package com.thealgorithms.datastructures.disjointsetunion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class DisjointSetUnionBySizeTest {

    @Test
    public void testMakeSet() {
        DisjointSetUnionBySize<Integer> dsu = new DisjointSetUnionBySize<>();
        DisjointSetUnionBySize.Node<Integer> node = dsu.makeSet(1);
        assertNotNull(node);
        assertEquals(node, node.parent);
        assertEquals(1, node.size);
    }

    @Test
    public void testUnionFindSet() {
        DisjointSetUnionBySize<Integer> dsu = new DisjointSetUnionBySize<>();
        DisjointSetUnionBySize.Node<Integer> node1 = dsu.makeSet(1);
        DisjointSetUnionBySize.Node<Integer> node2 = dsu.makeSet(2);
        DisjointSetUnionBySize.Node<Integer> node3 = dsu.makeSet(3);
        DisjointSetUnionBySize.Node<Integer> node4 = dsu.makeSet(4);

        dsu.unionSets(node1, node2);
        dsu.unionSets(node3, node2);
        dsu.unionSets(node3, node4);
        dsu.unionSets(node1, node3);

        DisjointSetUnionBySize.Node<Integer> root1 = dsu.findSet(node1);
        DisjointSetUnionBySize.Node<Integer> root2 = dsu.findSet(node2);
        DisjointSetUnionBySize.Node<Integer> root3 = dsu.findSet(node3);
        DisjointSetUnionBySize.Node<Integer> root4 = dsu.findSet(node4);

        assertEquals(root1, root2);
        assertEquals(root1, root3);
        assertEquals(root1, root4);
        assertEquals(4, root1.size);
    }

    @Test
    public void testFindSetOnSingleNode() {
        DisjointSetUnionBySize<String> dsu = new DisjointSetUnionBySize<>();
        DisjointSetUnionBySize.Node<String> node = dsu.makeSet("A");
        assertEquals(node, dsu.findSet(node));
    }

    @Test
    public void testUnionAlreadyConnectedNodes() {
        DisjointSetUnionBySize<Integer> dsu = new DisjointSetUnionBySize<>();
        DisjointSetUnionBySize.Node<Integer> node1 = dsu.makeSet(1);
        DisjointSetUnionBySize.Node<Integer> node2 = dsu.makeSet(2);
        DisjointSetUnionBySize.Node<Integer> node3 = dsu.makeSet(3);

        dsu.unionSets(node1, node2);
        dsu.unionSets(node2, node3);

        // Union nodes that are already connected
        dsu.unionSets(node1, node3);

        // All should have the same root
        DisjointSetUnionBySize.Node<Integer> root = dsu.findSet(node1);
        assertEquals(root, dsu.findSet(node2));
        assertEquals(root, dsu.findSet(node3));
        assertEquals(3, root.size);
    }

    @Test
    public void testMultipleMakeSets() {
        DisjointSetUnionBySize<Integer> dsu = new DisjointSetUnionBySize<>();
        DisjointSetUnionBySize.Node<Integer> node1 = dsu.makeSet(1);
        DisjointSetUnionBySize.Node<Integer> node2 = dsu.makeSet(2);
        DisjointSetUnionBySize.Node<Integer> node3 = dsu.makeSet(3);

        assertNotEquals(node1, node2);
        assertNotEquals(node2, node3);
        assertNotEquals(node1, node3);

        assertEquals(node1, node1.parent);
        assertEquals(node2, node2.parent);
        assertEquals(node3, node3.parent);
        assertEquals(1, node1.size);
        assertEquals(1, node2.size);
        assertEquals(1, node3.size);
    }

    @Test
    public void testPathCompression() {
        DisjointSetUnionBySize<Integer> dsu = new DisjointSetUnionBySize<>();
        DisjointSetUnionBySize.Node<Integer> node1 = dsu.makeSet(1);
        DisjointSetUnionBySize.Node<Integer> node2 = dsu.makeSet(2);
        DisjointSetUnionBySize.Node<Integer> node3 = dsu.makeSet(3);

        dsu.unionSets(node1, node2);
        dsu.unionSets(node2, node3);

        // After findSet, path compression should update parent to root directly
        DisjointSetUnionBySize.Node<Integer> root = dsu.findSet(node3);
        assertEquals(root, node1);
        assertEquals(node1, node3.parent);
        assertEquals(3, root.size);
    }

    @Test
    public void testMultipleDisjointSets() {
        DisjointSetUnionBySize<Integer> dsu = new DisjointSetUnionBySize<>();
        DisjointSetUnionBySize.Node<Integer> node1 = dsu.makeSet(1);
        DisjointSetUnionBySize.Node<Integer> node2 = dsu.makeSet(2);
        DisjointSetUnionBySize.Node<Integer> node3 = dsu.makeSet(3);
        DisjointSetUnionBySize.Node<Integer> node4 = dsu.makeSet(4);
        DisjointSetUnionBySize.Node<Integer> node5 = dsu.makeSet(5);
        DisjointSetUnionBySize.Node<Integer> node6 = dsu.makeSet(6);

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
        DisjointSetUnionBySize<String> dsu = new DisjointSetUnionBySize<>();
        DisjointSetUnionBySize.Node<String> emptyNode = dsu.makeSet("");
        DisjointSetUnionBySize.Node<String> nullNode = dsu.makeSet(null);

        assertEquals(emptyNode, dsu.findSet(emptyNode));
        assertEquals(nullNode, dsu.findSet(nullNode));

        dsu.unionSets(emptyNode, nullNode);
        assertEquals(dsu.findSet(emptyNode), dsu.findSet(nullNode));
    }
}
