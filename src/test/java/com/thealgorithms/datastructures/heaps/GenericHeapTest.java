package com.thealgorithms.datastructures.heaps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GenericHeapTest {

    private GenericHeap<Integer> heap;

    @BeforeEach
    void setUp() {
        heap = new GenericHeap<>();
    }

    @Test
    void testAddAndGet() {
        heap.add(10);
        heap.add(20);
        heap.add(5);

        assertEquals(20, heap.get());
    }

    @Test
    void testRemove() {
        heap.add(10);
        heap.add(20);
        heap.add(5);

        assertEquals(20, heap.remove());
        assertEquals(10, heap.get());
    }

    @Test
    void testIsEmpty() {
        assertTrue(heap.isEmpty());
        heap.add(1);
        assertFalse(heap.isEmpty());
    }

    @Test
    void testSize() {
        assertEquals(0, heap.size());
        heap.add(1);
        heap.add(2);
        assertEquals(2, heap.size());
    }

    @Test
    void testUpdatePriority() {
        heap.add(10);
        heap.add(20);
        heap.add(5);

        heap.updatePriority(10);
        assertEquals(20, heap.get());

        heap.add(30);
        heap.updatePriority(20); // 20 will be moved up
        assertEquals(30, heap.get());
    }

    @Test
    void testRemoveFromEmptyHeap() {
        Exception exception = assertThrows(IllegalStateException.class, () -> heap.remove());
        assertEquals("Heap is empty", exception.getMessage());
    }

    @Test
    void testGetFromEmptyHeap() {
        Exception exception = assertThrows(IllegalStateException.class, () -> heap.get());
        assertEquals("Heap is empty", exception.getMessage());
    }

    @Test
    void testUpdatePriorityForNonExistentItem() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> heap.updatePriority(100));
        assertEquals("Item not found in the heap", exception.getMessage());
    }
}
