package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    public void testEmptyTreeSearch() {
        BTree bTree = new BTree(3);
        assertFalse(bTree.search(42), "Search in empty tree should return false.");
    }

    @Test
    public void testDuplicateInsertions() {
        BTree bTree = new BTree(3);
        bTree.insert(15);
        bTree.insert(15); // Attempt duplicate
        bTree.insert(15); // Another duplicate

        ArrayList<Integer> traversal = new ArrayList<>();
        bTree.traverse(traversal);

        // Should contain only one 15
        long count = traversal.stream().filter(x -> x == 15).count();
        assertEquals(1, count, "Duplicate keys should not be inserted.");
    }

    @Test
    public void testDeleteNonExistentKey() {
        BTree bTree = new BTree(3);
        bTree.insert(10);
        bTree.insert(20);
        bTree.delete(99); // Doesn't exist
        assertTrue(bTree.search(10));
        assertTrue(bTree.search(20));
    }

    @Test
    public void testComplexInsertDelete() {
        BTree bTree = new BTree(2); // Smaller degree to trigger splits more easily
        int[] values = {1, 3, 7, 10, 11, 13, 14, 15, 18, 16, 19, 24, 25, 26, 21, 4, 5, 20, 22, 2, 17, 12, 6};

        for (int val : values) {
            bTree.insert(val);
        }

        for (int val : values) {
            assertTrue(bTree.search(val));
        }

        int[] toDelete = {6, 13, 7, 4, 2, 16};
        for (int val : toDelete) {
            bTree.delete(val);
            assertFalse(bTree.search(val));
        }

        ArrayList<Integer> remaining = new ArrayList<>();
        bTree.traverse(remaining);

        for (int val : toDelete) {
            assertFalse(remaining.contains(val));
        }
    }
}
