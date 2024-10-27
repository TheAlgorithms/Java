package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class BinaryTreeTest {

    @Test
    void test1() {
        BinaryTree t = new BinaryTree();
        t.put(3);
        t.put(5);
        t.put(7);
        t.put(9);
        t.put(12);

        assertNotNull(t.find(5));
        assertEquals(5, t.find(5).data);
        assertEquals(7, t.find(7).data);
    }

    @Test
    void test2() {
        BinaryTree t = new BinaryTree();
        t.put(3);
        t.put(5);
        t.put(7);
        t.put(9);
        t.put(12);
        t.remove(3);
        t.remove(5);
        t.remove(7);

        assertNotNull(t.getRoot(), "Root should not be null after removals");
        assertEquals(9, t.getRoot().data);
    }

    @Test
    void test3() {
        BinaryTree t = new BinaryTree();
        t.put(3);
        t.put(5);
        t.put(7);
        t.put(9);
        t.put(12);

        assertEquals(true, t.remove(9));
        assertEquals(false, t.remove(398745987));
    }

    @Test
    void test4() {
        BinaryTree t = new BinaryTree();
        t.put(3);
        t.put(5);
        t.put(7);
        t.put(9);
        t.put(12);

        t.bfs(t.getRoot());
        t.inOrder(t.getRoot());
        t.preOrder(t.getRoot());
        t.postOrder(t.getRoot());

        assertEquals(true, t.remove(9));
        assertEquals(false, t.remove(398745987));
    }
}
