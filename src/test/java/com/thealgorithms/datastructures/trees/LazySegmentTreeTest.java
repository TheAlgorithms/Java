package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class LazySegmentTreeTest {

    @Test
    void build() {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        LazySegmentTree lazySegmentTree = new LazySegmentTree(arr);
        assertEquals(55, lazySegmentTree.getRoot().getValue());
        assertEquals(15, lazySegmentTree.getRoot().getLeft().getValue());
        assertEquals(40, lazySegmentTree.getRoot().getRight().getValue());
    }

    @Test
    void update() {
        int[] arr = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
        LazySegmentTree lazySegmentTree = new LazySegmentTree(arr);
        assertEquals(10, lazySegmentTree.getRoot().getValue());

        lazySegmentTree.updateRange(0, 2, 1);
        assertEquals(12, lazySegmentTree.getRoot().getValue());

        lazySegmentTree.updateRange(1, 3, 1);
        assertEquals(14, lazySegmentTree.getRoot().getValue());

        lazySegmentTree.updateRange(6, 8, 1);
        assertEquals(16, lazySegmentTree.getRoot().getValue());

        lazySegmentTree.updateRange(3, 9, 1);
        assertEquals(22, lazySegmentTree.getRoot().getValue());
    }

    @Test
    void get() {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        LazySegmentTree lazySegmentTree = new LazySegmentTree(arr);
        assertEquals(55, lazySegmentTree.getRange(0, 10));
        assertEquals(3, lazySegmentTree.getRange(0, 2));
        assertEquals(19, lazySegmentTree.getRange(8, 10));
        assertEquals(44, lazySegmentTree.getRange(1, 9));
    }

    @Test
    void updateAndGet() {
        int[] arr = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        LazySegmentTree lazySegmentTree = new LazySegmentTree(arr);

        for (int i = 0; i < 10; i++) for (int j = i + 1; j < 10; j++) {
            lazySegmentTree.updateRange(i, j, 1);
            assertEquals(j - i, lazySegmentTree.getRange(i, j));
            lazySegmentTree.updateRange(i, j, -1);
            assertEquals(0, lazySegmentTree.getRange(i, j));
        }
    }
}
