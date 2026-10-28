package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Random;
import org.junit.jupiter.api.Test;

class FenwickTreeTest {

    @Test
    void queryOnFreshTreeReturnsZero() {
        FenwickTree tree = new FenwickTree(5);
        for (int i = 0; i < 5; i++) {
            assertEquals(0, tree.query(i));
        }
    }

    @Test
    void singleElementTree() {
        FenwickTree tree = new FenwickTree(1);
        tree.update(0, 7);
        assertEquals(7, tree.query(0));
    }

    @Test
    void prefixSumsAfterUpdates() {
        FenwickTree tree = new FenwickTree(5);
        int[] values = {3, 2, -1, 6, 5};
        for (int i = 0; i < values.length; i++) {
            tree.update(i, values[i]);
        }
        assertEquals(3, tree.query(0));
        assertEquals(5, tree.query(1));
        assertEquals(4, tree.query(2));
        assertEquals(10, tree.query(3));
        assertEquals(15, tree.query(4));
    }

    @Test
    void repeatedUpdatesOnSameIndexAccumulate() {
        FenwickTree tree = new FenwickTree(4);
        tree.update(2, 5);
        tree.update(2, 3);
        tree.update(2, -2);
        assertEquals(0, tree.query(1));
        assertEquals(6, tree.query(2));
        assertEquals(6, tree.query(3));
    }

    @Test
    void negativeValues() {
        FenwickTree tree = new FenwickTree(3);
        tree.update(0, -4);
        tree.update(1, -6);
        tree.update(2, 10);
        assertEquals(-4, tree.query(0));
        assertEquals(-10, tree.query(1));
        assertEquals(0, tree.query(2));
    }

    @Test
    void nonPowerOfTwoSize() {
        int size = 13;
        FenwickTree tree = new FenwickTree(size);
        for (int i = 0; i < size; i++) {
            tree.update(i, 1);
        }
        for (int i = 0; i < size; i++) {
            assertEquals(i + 1, tree.query(i));
        }
    }

    @Test
    void matchesNaivePrefixSumOnRandomData() {
        Random random = new Random(42);
        int size = 100;
        FenwickTree tree = new FenwickTree(size);
        int[] naive = new int[size];

        for (int step = 0; step < 500; step++) {
            int index = random.nextInt(size);
            int delta = random.nextInt(21) - 10;
            tree.update(index, delta);
            naive[index] += delta;
        }

        int running = 0;
        for (int i = 0; i < size; i++) {
            running += naive[i];
            assertEquals(running, tree.query(i), "Mismatch at index " + i);
        }
    }

    @Test
    void zeroSizeTreeRejectsAnyIndex() {
        FenwickTree tree = new FenwickTree(0);
        assertThrows(IndexOutOfBoundsException.class, () -> tree.update(0, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> tree.query(0));
    }

    @Test
    void negativeSizeThrows() {
        assertThrows(IllegalArgumentException.class, () -> new FenwickTree(-1));
    }

    @Test
    void outOfBoundsIndicesThrow() {
        FenwickTree tree = new FenwickTree(5);
        assertThrows(IndexOutOfBoundsException.class, () -> tree.update(-1, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> tree.update(5, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> tree.query(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> tree.query(5));
    }
}
