package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SegmentTree2DTest {

    @Test
    void testInitialEmptyQueries() {
        SegmentTree2D segmentTree = new SegmentTree2D(4, 4);

        // Initial tree should return 0 for any query
        assertEquals(0, segmentTree.query(0, 4, 0, 4));
        assertEquals(0, segmentTree.query(1, 3, 1, 3));
    }

    @Test
    void testUpdateAndPointQuery() {
        SegmentTree2D segmentTree = new SegmentTree2D(5, 5);

        segmentTree.update(2, 3, 10);
        segmentTree.update(0, 0, 5);

        // Querying single points [row, row+1) x [col, col+1)
        assertEquals(10, segmentTree.query(2, 3, 3, 4));
        assertEquals(5, segmentTree.query(0, 1, 0, 1));

        // Empty point should be 0
        assertEquals(0, segmentTree.query(1, 2, 1, 2));
    }

    @Test
    void testSubmatrixQuery() {
        SegmentTree2D segmentTree = new SegmentTree2D(4, 4);

        // Matrix simulation:
        // [1, 2, 0, 0]
        // [3, 4, 0, 0]
        // [0, 0, 0, 0]
        // [0, 0, 0, 0]
        segmentTree.update(0, 0, 1);
        segmentTree.update(0, 1, 2);
        segmentTree.update(1, 0, 3);
        segmentTree.update(1, 1, 4);

        // Top-left 2x2 sum: 1+2+3+4 = 10
        assertEquals(10, segmentTree.query(0, 2, 0, 2));

        // First row sum: 1+2 = 3
        assertEquals(3, segmentTree.query(0, 1, 0, 4));

        // Second column sum: 2+4 = 6
        assertEquals(6, segmentTree.query(0, 4, 1, 2));
    }

    @Test
    void testUpdateOverwriting() {
        SegmentTree2D segmentTree = new SegmentTree2D(3, 3);

        segmentTree.update(1, 1, 5);
        assertEquals(5, segmentTree.query(1, 2, 1, 2));

        // Overwrite the same point
        segmentTree.update(1, 1, 20);
        assertEquals(20, segmentTree.query(1, 2, 1, 2));

        // Full matrix sum should just be this point
        assertEquals(20, segmentTree.query(0, 3, 0, 3));
    }
}
