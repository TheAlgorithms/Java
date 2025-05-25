package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class BTreeTest {

    @Test
    public void testInsertSearchDelete() {
        BTree bTree = new BTree(3); // Minimum degree t = 3

        int[] values = {10, 20, 5, 6, 12, 30, 7, 17};
        for (int val : values) {
            bTree.insert(val);
        }

        for (int val : values) {
            assertTrue(bTree.search(val), "Should find inserted value: " + val);
        }

        ArrayList<Integer> traversal = new ArrayList<>();
        bTree.traverse(traversal);
        assertEquals(Arrays.asList(5, 6, 7, 10, 12, 17, 20, 30), traversal);

        bTree.delete(6);
        assertFalse(bTree.search(6));
        traversal.clear();
        bTree.traverse(traversal);
        assertEquals(Arrays.asList(5, 7, 10, 12, 17, 20, 30), traversal);
    }
}

