package com.thealgorithms.tree;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class AVLTreeTest {

    @Test
    void insertAndBalance() {
        AVLTree<Integer> avl = new AVLTree<>();
        avl.insert(30);
        avl.insert(20);
        avl.insert(10); // triggers right rotation (LL)

        assertTrue(avl.contains(20));
        assertEquals(10, avl.findMin());
        assertEquals(30, avl.findMax());

        List<Integer> in = avl.inorder();
        assertArrayEquals(new Integer[]{10,20,30}, in.toArray(new Integer[0]));
    }

    @Test
    void duplicatesAndDelete() {
        AVLTree<Integer> avl = new AVLTree<>();
        avl.insert(5);
        avl.insert(5);
        assertEquals(2, avl.countOccurrences(5));
        avl.delete(5);
        assertEquals(1, avl.countOccurrences(5));
        avl.delete(5);
        assertFalse(avl.contains(5));
    }
}
