package com.thealgorithms.searches;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UnionFindTest {
    private UnionFind uf;

    @BeforeEach
    void setUp() {
        uf = new UnionFind(10); // Initialize with 10 elements
    }

    @Test
    void testInitialState() {
        // Verify that each element is its own parent and rank is 0
        assertEquals("p [0, 1, 2, 3, 4, 5, 6, 7, 8, 9] r [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]\n", uf.toString());
        assertEquals(10, uf.count(), "Initial count of disjoint sets should be 10.");
    }

    @Test
    void testUnionOperation() {
        uf.union(0, 1);
        uf.union(1, 2);
        assertEquals(8, uf.count(), "Count should decrease after unions.");
        assertEquals(0, uf.find(2), "Element 2 should point to root 0 after unions.");
    }

    @Test
    void testUnionWithRank() {
        uf.union(0, 1);
        uf.union(1, 2); // Make 0 the root of 2
        uf.union(3, 4);
        uf.union(4, 5); // Make 3 the root of 5
        uf.union(0, 3); // Union two trees

        assertEquals(5, uf.count(), "Count should decrease after unions.");
        assertEquals(0, uf.find(5), "Element 5 should point to root 0 after unions.");
    }

    @Test
    void testFindOperation() {
        uf.union(2, 3);
        uf.union(4, 5);
        uf.union(3, 5); // Connect 2-3 and 4-5

        assertEquals(2, uf.find(3), "Find operation should return the root of the set.");
        assertEquals(2, uf.find(5), "Find operation should return the root of the set.");
    }

    @Test
    void testCountAfterMultipleUnions() {
        uf.union(0, 1);
        uf.union(2, 3);
        uf.union(4, 5);
        uf.union(1, 3); // Connect 0-1-2-3
        uf.union(5, 6);

        assertEquals(5, uf.count(), "Count should reflect the number of disjoint sets after multiple unions.");
    }

    @Test
    void testNoUnion() {
        assertEquals(10, uf.count(), "Count should remain 10 if no unions are made.");
    }

    @Test
    void testUnionSameSet() {
        uf.union(1, 2);
        uf.union(1, 2); // Union same elements again

        assertEquals(9, uf.count(), "Count should not decrease if union is called on the same set.");
    }

    @Test
    void testFindOnSingleElement() {
        assertEquals(7, uf.find(7), "Find on a single element should return itself.");
    }
}
