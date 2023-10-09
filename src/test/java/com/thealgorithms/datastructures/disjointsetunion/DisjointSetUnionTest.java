package com.thealgorithms.datastructures.disjointsetunion;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DisjointSetUnionTest {

    @Test
    public void testMakeSet() {
        DisjointSetUnion<Integer> dsu = new DisjointSetUnion<>();
        Node<Integer> node = dsu.makeSet(1);
        assertNotNull(node);
        Assertions.assertEquals(node, node.parent);
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

        Assertions.assertEquals(node1, node1.parent);
        Assertions.assertEquals(node1, node2.parent);
        Assertions.assertEquals(node1, node3.parent);
        Assertions.assertEquals(node1, node4.parent);

        Assertions.assertEquals(node1, root1);
        Assertions.assertEquals(node1, root2);
        Assertions.assertEquals(node1, root3);
        Assertions.assertEquals(node1, root4);

        Assertions.assertEquals(1, node1.rank);
        Assertions.assertEquals(0, node2.rank);
        Assertions.assertEquals(0, node3.rank);
        Assertions.assertEquals(0, node4.rank);
    }
}
