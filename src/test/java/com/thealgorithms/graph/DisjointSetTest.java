package com.thealgorithms.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DisjointSetTest {

    @Test
    @DisplayName("Initially all elements are in separate sets")
    void initialState() {
        DisjointSet ds = new DisjointSet(5);
        assertEquals(5, ds.getNumSets());
        for (int i = 0; i < 5; i++) {
            assertEquals(i, ds.find(i));
            assertEquals(1, ds.getSetSize(i));
        }
    }

    @Test
    @DisplayName("Union of elements creates correct sets")
    void basicUnion() {
        DisjointSet ds = new DisjointSet(4);
        assertTrue(ds.union(0, 1)); // Should succeed
        assertTrue(ds.union(2, 3)); // Should succeed
        assertFalse(ds.union(0, 1)); // Should fail (already united)
        assertEquals(2, ds.getNumSets());
        assertTrue(ds.connected(0, 1));
        assertTrue(ds.connected(2, 3));
        assertFalse(ds.connected(0, 2));
    }

    @Test
    @DisplayName("Path compression optimizes tree structure")
    void pathCompression() {
        DisjointSet ds = new DisjointSet(4);
        ds.union(0, 1);
        ds.union(1, 2);
        ds.union(2, 3);
        // After these unions, we should have one set
        assertEquals(1, ds.getNumSets());
        // After path compression, all elements should point to the same root
        int root = ds.find(0);
        for (int i = 1; i < 4; i++) {
            assertEquals(root, ds.find(i));
        }
    }

    @Test
    @DisplayName("Set size tracking works correctly")
    void setSizeTracking() {
        DisjointSet ds = new DisjointSet(6);
        ds.union(0, 1); // Set of size 2
        ds.union(2, 3); // Another set of size 2
        ds.union(0, 2); // Merge into set of size 4
        assertEquals(4, ds.getSetSize(0));
        assertEquals(4, ds.getSetSize(1));
        assertEquals(4, ds.getSetSize(2));
        assertEquals(4, ds.getSetSize(3));
        assertEquals(1, ds.getSetSize(4));
        assertEquals(1, ds.getSetSize(5));
    }

    @Test
    @DisplayName("Get set members returns correct lists")
    void getSetMembers() {
        DisjointSet ds = new DisjointSet(4);
        ds.union(0, 1);
        ds.union(2, 3);

        List<Integer> set1 = ds.getSetMembers(0);
        List<Integer> set2 = ds.getSetMembers(2);

        assertEquals(2, set1.size());
        assertTrue(set1.contains(0));
        assertTrue(set1.contains(1));

        assertEquals(2, set2.size());
        assertTrue(set2.contains(2));
        assertTrue(set2.contains(3));
    }

    @Test
    @DisplayName("Out of bounds access throws exception")
    void outOfBounds() {
        DisjointSet ds = new DisjointSet(3);
        assertThrows(IllegalArgumentException.class, () -> ds.find(-1));
        assertThrows(IllegalArgumentException.class, () -> ds.find(3));
        assertThrows(IllegalArgumentException.class, () -> ds.union(-1, 0));
        assertThrows(IllegalArgumentException.class, () -> ds.union(0, 3));
    }

    @Test
    @DisplayName("Negative size throws exception")
    void negativeSize() {
        assertThrows(IllegalArgumentException.class, () -> new DisjointSet(-1));
    }

    @Test
    @DisplayName("Connected components example")
    void connectedComponents() {
        DisjointSet ds = new DisjointSet(6);
        // Create two connected components: 0-1-2 and 3-4-5
        ds.union(0, 1);
        ds.union(1, 2);
        ds.union(3, 4);
        ds.union(4, 5);

        assertEquals(2, ds.getNumSets());

        // Check connectivity within components
        assertTrue(ds.connected(0, 2));
        assertTrue(ds.connected(3, 5));

        // Check separation between components
        assertFalse(ds.connected(0, 3));
        assertFalse(ds.connected(2, 4));
    }
}