package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TreapTest {

    @Test
    void arrayBuild() {
        int[] arr = {5, 9, 6, 2, 3, 8, 1};
        Treap treap = new Treap(arr);
        assertEquals("{1,2,3,5,6,8,9,}", treap.inOrder());
    }

    @Test
    void build() {
        Treap treap = new Treap();
        treap.insert(4);
        treap.insert(5);
        treap.insert(9);
        treap.insert(2);
        assertEquals("{2,4,5,9,}", treap.inOrder());
    }

    @Test
    void delete() {
        int[] arr = {5, 9, 6, 2, 3, 8, 1};
        Treap treap = new Treap(arr);
        treap.delete(5);
        assertEquals("{1,2,3,6,8,9,}", treap.inOrder());
    }

    @Test
    void searchAndFound() {
        int[] arr = {5, 9, 6, 2, 3, 8, 1};
        Treap treap = new Treap(arr);
        assertEquals(5, treap.search(5).value());
    }

    @Test
    void searchAndNotFound() {
        int[] arr = {5, 9, 6, 2, 3, 8, 1};
        Treap treap = new Treap(arr);
        assertEquals(null, treap.search(4));
    }

    @Test
    void lowerBound() {
        int[] arr = {5, 9, 6, 2, 3, 8, 1};
        Treap treap = new Treap(arr);
        assertEquals(5, treap.lowerBound(4));
    }

    @Test 
    void upperBound() {
        int[] arr = {5, 9, 6, 2, 3, 8, 1};
        Treap treap = new Treap(arr);
        assertEquals(6, treap.upperBound(5));
    }

    @Test 
    void misc() {
        int[] arr = {5, 9, 6, 2, 3, 8, 1};
        Treap treap = new Treap(arr);
        assertEquals(7, treap.size());
        assertEquals(false, treap.isEmpty());
    }
}
