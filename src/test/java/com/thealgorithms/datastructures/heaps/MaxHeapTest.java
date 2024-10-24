package com.thealgorithms.datastructures.heaps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for MaxHeap implementation
 */
class MaxHeapTest {

    private MaxHeap heap;

    @BeforeEach
    void setUp() {
        // Create a fresh heap for each test
        List<HeapElement> elements = Arrays.asList(new HeapElement(5.0, "Five"), new HeapElement(2.0, "Two"), new HeapElement(8.0, "Eight"), new HeapElement(1.0, "One"), new HeapElement(9.0, "Nine"));
        heap = new MaxHeap(elements);
    }

    @Test
    void testConstructorWithNullList() {
        assertThrows(IllegalArgumentException.class, () -> new MaxHeap(null));
    }

    @Test
    void testConstructorWithEmptyList() {
        MaxHeap emptyHeap = new MaxHeap(new ArrayList<>());
        assertTrue(emptyHeap.isEmpty());
    }

    @Test
    void testConstructorWithNullElements() {
        List<HeapElement> elements = Arrays.asList(new HeapElement(1.0, "One"), null, new HeapElement(2.0, "Two"));
        MaxHeap heap = new MaxHeap(elements);
        assertEquals(2, heap.size());
    }

    @Test
    void testInsertElement() {
        heap.insertElement(new HeapElement(10.0, "Ten"));
        assertEquals(10.0, heap.getElement(1).getKey());
        assertEquals(6, heap.size());
    }

    @Test
    void testInsertNullElement() {
        assertThrows(IllegalArgumentException.class, () -> heap.insertElement(null));
    }

    @Test
    void testGetElementAtIndex() {
        HeapElement element = heap.getElement(1);
        assertEquals(9.0, element.getKey());
        assertEquals("Nine", element.getValue());
    }

    @Test
    void testGetElementAtInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> heap.getElement(0));
        assertThrows(IndexOutOfBoundsException.class, () -> heap.getElement(10));
    }

    @Test
    void testDeleteElement() throws EmptyHeapException {
        heap.deleteElement(1);
        assertEquals(8.0, heap.getElement(1).getKey());
        assertEquals(4, heap.size());
    }

    @Test
    void testDeleteElementAtInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> heap.deleteElement(0));
        assertThrows(IndexOutOfBoundsException.class, () -> heap.deleteElement(10));
    }

    @Test
    void testDeleteFromEmptyHeap() {
        MaxHeap emptyHeap = new MaxHeap(new ArrayList<>());
        assertThrows(EmptyHeapException.class, () -> emptyHeap.deleteElement(1));
    }

    @Test
    void testExtractMax() throws EmptyHeapException {
        HeapElement max = heap.getElement();
        assertEquals(9.0, max.getKey());
        assertEquals("Nine", max.getValue());
        assertEquals(4, heap.size());

        max = heap.getElement();
        assertEquals(8.0, max.getKey());
        assertEquals(3, heap.size());
    }

    @Test
    void testExtractMaxFromEmptyHeap() {
        MaxHeap emptyHeap = new MaxHeap(new ArrayList<>());
        assertThrows(EmptyHeapException.class, () -> emptyHeap.getElement());
    }

    @Test
    void testHeapOrder() {
        // Test that parent is always greater than or equal to children
        for (int i = 1; i <= heap.size() / 2; i++) {
            double parentKey = heap.getElement(i).getKey();

            // Check left child
            if (2 * i <= heap.size()) {
                assertTrue(parentKey >= heap.getElement(2 * i).getKey());
            }

            // Check right child
            if (2 * i + 1 <= heap.size()) {
                assertTrue(parentKey >= heap.getElement(2 * i + 1).getKey());
            }
        }
    }

    @Test
    void testSizeAndEmpty() {
        assertEquals(5, heap.size());
        assertFalse(heap.isEmpty());

        // Remove all elements
        while (!heap.isEmpty()) {
            try {
                heap.getElement();
            } catch (EmptyHeapException e) {
                Assertions.fail("Should not throw EmptyHeapException while heap is not empty");
            }
        }

        assertEquals(0, heap.size());
        assertTrue(heap.isEmpty());
    }
}
