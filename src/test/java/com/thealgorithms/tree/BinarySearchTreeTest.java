package com.thealgorithms.tree;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class BinarySearchTreeTest {

    @Test
    void basicOperations() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(5);
        bst.insert(3);
        bst.insert(7);
        bst.insert(3); // duplicate

        assertTrue(bst.contains(5));
        assertTrue(bst.contains(3));
        assertEquals(2, bst.countOccurrences(3));

        List<Integer> inorder = bst.inorder();
        // inorder should be [3,3,5,7]
        assertArrayEquals(new Integer[]{3,3,5,7}, inorder.toArray(new Integer[0]));

        assertEquals(3, bst.findMin());
        assertEquals(7, bst.findMax());

        assertTrue(bst.delete(3)); // decrement duplicate
        assertEquals(1, bst.countOccurrences(3));
        assertTrue(bst.delete(3)); // remove node entirely
        assertFalse(bst.contains(3));
    }
}
