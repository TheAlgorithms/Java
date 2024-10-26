package com.thealgorithms.datastructures.buffers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CircularBufferTest {

    @Test
    void testInitialization() {
        CircularBuffer<Integer> buffer = new CircularBuffer<>(5);
        assertTrue(buffer.isEmpty());
        assertEquals(Boolean.FALSE, buffer.isFull());
    }

    @Test
    void testPutAndGet() {
        CircularBuffer<String> buffer = new CircularBuffer<>(3);

        assertTrue(buffer.put("A"));
        assertEquals(Boolean.FALSE, buffer.isEmpty());
        assertEquals(Boolean.FALSE, buffer.isFull());

        buffer.put("B");
        buffer.put("C");
        assertTrue(buffer.isFull());

        assertEquals("A", buffer.get());
        assertEquals("B", buffer.get());
        assertEquals("C", buffer.get());
        assertTrue(buffer.isEmpty());
    }

    @Test
    void testOverwrite() {
        CircularBuffer<Integer> buffer = new CircularBuffer<>(3);

        buffer.put(1);
        buffer.put(2);
        buffer.put(3);
        assertEquals(Boolean.FALSE, buffer.put(4)); // This should overwrite 1

        assertEquals(2, buffer.get());
        assertEquals(3, buffer.get());
        assertEquals(4, buffer.get());
        assertNull(buffer.get());
    }

    @Test
    void testEmptyBuffer() {
        CircularBuffer<Double> buffer = new CircularBuffer<>(2);
        assertNull(buffer.get());
    }

    @Test
    void testFullBuffer() {
        CircularBuffer<Character> buffer = new CircularBuffer<>(2);
        buffer.put('A');
        buffer.put('B');
        assertTrue(buffer.isFull());
        assertEquals(Boolean.FALSE, buffer.put('C')); // This should overwrite 'A'
        assertEquals('B', buffer.get());
        assertEquals('C', buffer.get());
    }

    @Test
    void testIllegalArguments() {
        assertThrows(IllegalArgumentException.class, () -> new CircularBuffer<>(0));
        assertThrows(IllegalArgumentException.class, () -> new CircularBuffer<>(-1));

        CircularBuffer<String> buffer = new CircularBuffer<>(1);
        assertThrows(IllegalArgumentException.class, () -> buffer.put(null));
    }

    @Test
    void testLargeBuffer() {
        CircularBuffer<Integer> buffer = new CircularBuffer<>(1000);
        for (int i = 0; i < 1000; i++) {
            buffer.put(i);
        }
        assertTrue(buffer.isFull());
        buffer.put(1000); // This should overwrite 0
        assertEquals(1, buffer.get());
    }
}
