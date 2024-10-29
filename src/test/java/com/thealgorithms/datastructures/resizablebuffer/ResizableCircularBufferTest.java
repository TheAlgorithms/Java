package com.thealgorithms.datastructures.resizablebuffer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ResizableCircularBufferTest {

    @Test
    void testInitialization() {
        ResizableCircularBuffer<Integer> buffer = new ResizableCircularBuffer<>(5);
        assertTrue(buffer.isEmpty());
        assertFalse(buffer.isFull());
    }

    @Test
    void testPutAndGet() {
        ResizableCircularBuffer<String> buffer = new ResizableCircularBuffer<>(3);

        buffer.put("A");
        assertFalse(buffer.isEmpty());
        assertFalse(buffer.isFull());

        buffer.put("B");
        buffer.put("C");
        assertTrue(buffer.isFull());

        assertEquals("A", buffer.get());
        assertEquals("B", buffer.get());
        assertEquals("C", buffer.get());
        assertTrue(buffer.isEmpty());
    }

    @Test
    void testResizeOnFullBuffer() {
        ResizableCircularBuffer<Integer> buffer = new ResizableCircularBuffer<>(2);

        buffer.put(1);
        buffer.put(2);
        buffer.put(3); // Should trigger resizing

        assertEquals(1, buffer.get());
        assertEquals(2, buffer.get());
        assertEquals(3, buffer.get());
    }

    @Test
    void testOverwrite() {
        ResizableCircularBuffer<Integer> buffer = new ResizableCircularBuffer<>(2);

        buffer.put(1);
        buffer.put(2);
        buffer.put(3); // Should resize instead of overwrite
        assertEquals(1, buffer.get());
        assertEquals(2, buffer.get());
        assertEquals(3, buffer.get());
    }

    @Test
    void testEmptyBuffer() {
        ResizableCircularBuffer<Double> buffer = new ResizableCircularBuffer<>(2);
        assertNull(buffer.get());
    }

    @Test
    void testFullBufferAndResize() {
        ResizableCircularBuffer<Character> buffer = new ResizableCircularBuffer<>(2);

        buffer.put('A');
        buffer.put('B');
        assertTrue(buffer.isFull());

        buffer.put('C'); // This should resize the buffer instead of overwriting 'A'
        assertEquals('A', buffer.get());
        assertEquals('B', buffer.get());
        assertEquals('C', buffer.get());
    }

    @Test
    void testIllegalArguments() {
        assertThrows(IllegalArgumentException.class, () -> new ResizableCircularBuffer<>(0));
        assertThrows(IllegalArgumentException.class, () -> new ResizableCircularBuffer<>(-1));

        ResizableCircularBuffer<String> buffer = new ResizableCircularBuffer<>(1);
        assertThrows(IllegalArgumentException.class, () -> buffer.put(null));
    }

    @Test
    void testLargeBufferWithResize() {
        ResizableCircularBuffer<Integer> buffer = new ResizableCircularBuffer<>(10);

        // Fill buffer to trigger multiple resizes
        for (int i = 0; i < 100; i++) {
            buffer.put(i);
        }

        assertEquals(10, buffer.get()); // Check that resizing kept order
    }
}