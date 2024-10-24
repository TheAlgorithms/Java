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

class MinHeapTest {

    private MinHeap heap;

    @BeforeEach
    void setUp() {
        // Create a fresh heap for each test
        List<HeapElement> elements = Arrays.asList(new HeapElement(5.0, "Five"), new HeapElement(2.0, "Two"), new HeapElement(8.0, "Eight"), new HeapElement(1.0, "One"), new HeapElement(9.0, "Nine"));
        heap = new MinHeap(elements);
    }

    @Test
    void testConstructorWithNullList() {
        assertThrows(IllegalArgumentException.class, () -> new MinHeap(null));
    }

    @Test
    void testConstructorWithEmptyList() {
        MinHeap emptyHeap = new MinHeap(new ArrayList<>());
        assertTrue(emptyHeap.isEmpty());
    }

    @Test
    void testConstructorWithNullElements() {
        List<HeapElement> elements = Arrays.asList(new HeapElement(1.0, "One"), null, new HeapElement(2.0, "Two"));
        MinHeap heap = new MinHeap(elements);
        assertEquals(2, heap.size());
    }

    @Test
    void testInsertElement() {
        heap.insertElement(new HeapElement(0.5, "Half"));
        assertEquals(0.5, heap.getElement(1).getKey());
        assertEquals(6, heap.size());
    }

    @Test
    void testInsertNullElement() {
        assertThrows(IllegalArgumentException.class, () -> heap.insertElement(null));
    }

    @Test
    void testGetElementAtIndex() {
        HeapElement element = heap.getElement(1);
        assertEquals(1.0, element.getKey());
        assertEquals("One", element.getValue());
    }

    @Test
    void testGetElementAtInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> heap.getElement(0));
        assertThrows(IndexOutOfBoundsException.class, () -> heap.getElement(10));
    }

    @Test
    void testDeleteElement() throws EmptyHeapException {
        heap.deleteElement(1);
        assertEquals(2.0, heap.getElement(1).getKey());
        assertEquals(4, heap.size());
    }

    @Test
    void testDeleteElementAtInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> heap.deleteElement(0));
        assertThrows(IndexOutOfBoundsException.class, () -> heap.deleteElement(10));
    }

    @Test
    void testDeleteFromEmptyHeap() {
        MinHeap emptyHeap = new MinHeap(new ArrayList<>());
        assertThrows(EmptyHeapException.class, () -> emptyHeap.deleteElement(1));
    }

    @Test
    void testExtractMin() throws EmptyHeapException {
        HeapElement min = heap.getElement();
        assertEquals(1.0, min.getKey());
        assertEquals("One", min.getValue());
        assertEquals(4, heap.size());

        min = heap.getElement();
        assertEquals(2.0, min.getKey());
        assertEquals(3, heap.size());
    }

    @Test
    void testExtractMinFromEmptyHeap() {
        MinHeap emptyHeap = new MinHeap(new ArrayList<>());
        assertThrows(EmptyHeapException.class, () -> emptyHeap.getElement());
    }

    @Test
    void testHeapOrder() {
        // Test that parent is always smaller than or equal to children
        for (int i = 1; i <= heap.size() / 2; i++) {
            double parentKey = heap.getElement(i).getKey();

            // Check left child
            if (2 * i <= heap.size()) {
                assertTrue(parentKey <= heap.getElement(2 * i).getKey());
            }

            // Check right child
            if (2 * i + 1 <= heap.size()) {
                assertTrue(parentKey <= heap.getElement(2 * i + 1).getKey());
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
