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
    public void setUp() {
        heap = new GenericHeap<>();
    }

    @Test
    public void testGenericHeapAddAndGet() {
        heap.add(19);
        heap.add(36);
        heap.add(100);
        heap.add(-17);
        heap.add(3);

        // Check that the largest element (100) is at the top of the heap
        assertEquals(100, heap.get());
    }

    @Test
    public void testGenericHeapRemove() {
        heap.add(19);
        heap.add(36);
        heap.add(100);
        heap.add(-17);
        heap.add(3);

        // Verify that the largest element is removed correctly
        assertEquals(100, heap.remove());

        // The new element at the top should be 36
        assertEquals(36, heap.get());

        // Check that the size is correct after removal
        assertEquals(4, heap.size());
    }

    @Test
    public void testGenericHeapSize() {
        assertTrue(heap.isEmpty());

        heap.add(10);
        heap.add(20);

        // Check that the size is correct
        assertEquals(2, heap.size());

        heap.remove();

        // After removal, the size should be 1
        assertEquals(1, heap.size());
    }

    @Test
    public void testGenericHeapIsEmpty() {
        // Verify that the heap is initially empty
        assertTrue(heap.isEmpty());

        heap.add(15);

        // Now the heap should not be empty
        assertFalse(heap.isEmpty());

        heap.remove();

        // After removing the one element, it should be empty again
        assertTrue(heap.isEmpty());
    }

    @Test
    public void testGenericHeapUpdatePriority() {
        heap.add(19);
        heap.add(36);
        heap.add(100);
        heap.add(-17);
        heap.add(3);

        // Verify that the largest element initially is 100
        assertEquals(100, heap.get());

        heap.remove();

        // Simulates a change in priority by increasing the value of 100 to 44
        heap.add(44);

        // Now, the new high should be 25
        assertEquals(44, heap.get());
    }

    @Test
    public void testGenericHeapRemoveUntilEmpty() {
        heap.add(5);
        heap.add(3);
        heap.add(4);
        heap.add(1);
        heap.add(2);

        // Remove all items and check that they are removed in descending order
        assertEquals(5, heap.remove());
        assertEquals(4, heap.remove());
        assertEquals(3, heap.remove());
        assertEquals(2, heap.remove());
        assertEquals(1, heap.remove());

        // Empty heap
        assertTrue(heap.isEmpty());
    }

    @Test
    public void testGenericHeapAddNullItem() {
        // Check null item
        assertThrows(IllegalArgumentException.class, () -> {
            heap.add(null);
        });
    }
}
