package com.thealgorithms.datastructures.buffers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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
        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> new CircularBuffer<>(0));
        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> new CircularBuffer<>(-1));

        CircularBuffer<String> buffer = new CircularBuffer<>(1);
        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> buffer.put(null));
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

    @Test
    void testPutAfterGet() {
        CircularBuffer<Integer> buffer = new CircularBuffer<>(2);
        buffer.put(10);
        buffer.put(20);
        assertEquals(10, buffer.get());
        buffer.put(30);
        assertEquals(20, buffer.get());
        assertEquals(30, buffer.get());
        assertNull(buffer.get());
    }

    @Test
    void testMultipleWrapArounds() {
        CircularBuffer<Integer> buffer = new CircularBuffer<>(3);
        for (int i = 1; i <= 6; i++) {
            buffer.put(i);
            buffer.get(); // add and immediately remove
        }
        assertTrue(buffer.isEmpty());
        assertNull(buffer.get());
    }

    @Test
    void testOverwriteMultipleTimes() {
        CircularBuffer<String> buffer = new CircularBuffer<>(2);
        buffer.put("X");
        buffer.put("Y");
        buffer.put("Z"); // overwrites "X"
        buffer.put("W"); // overwrites "Y"
        assertEquals("Z", buffer.get());
        assertEquals("W", buffer.get());
        assertNull(buffer.get());
    }

    @Test
    void testIsEmptyAndIsFullTransitions() {
        CircularBuffer<Integer> buffer = new CircularBuffer<>(2);
        assertTrue(buffer.isEmpty());
        org.junit.jupiter.api.Assertions.assertFalse(buffer.isFull());

        buffer.put(1);
        org.junit.jupiter.api.Assertions.assertFalse(buffer.isEmpty());
        org.junit.jupiter.api.Assertions.assertFalse(buffer.isFull());

        buffer.put(2);
        assertTrue(buffer.isFull());

        buffer.get();
        org.junit.jupiter.api.Assertions.assertFalse(buffer.isFull());

        buffer.get();
        assertTrue(buffer.isEmpty());
    }

    @Test
    void testInterleavedPutAndGet() {
        CircularBuffer<String> buffer = new CircularBuffer<>(3);
        buffer.put("A");
        buffer.put("B");
        assertEquals("A", buffer.get());
        buffer.put("C");
        assertEquals("B", buffer.get());
        assertEquals("C", buffer.get());
        assertNull(buffer.get());
    }

    @Test
    void testRepeatedNullInsertionThrows() {
        CircularBuffer<Object> buffer = new CircularBuffer<>(5);
        for (int i = 0; i < 3; i++) {
            int finalI = i;
            org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> buffer.put(null), "Iteration: " + finalI);
        }
    }
    @Test
    void testFillThenEmptyThenReuseBuffer() {
        CircularBuffer<Integer> buffer = new CircularBuffer<>(3);

        buffer.put(1);
        buffer.put(2);
        buffer.put(3);
        assertTrue(buffer.isFull());

        assertEquals(1, buffer.get());
        assertEquals(2, buffer.get());
        assertEquals(3, buffer.get());

        assertTrue(buffer.isEmpty());

        buffer.put(4);
        buffer.put(5);
        assertEquals(4, buffer.get());
        assertEquals(5, buffer.get());
        assertTrue(buffer.isEmpty());
    }

    @Test
    void testPutReturnsTrueOnlyIfPreviouslyEmpty() {
        CircularBuffer<String> buffer = new CircularBuffer<>(2);

        assertTrue(buffer.put("one")); // was empty
        org.junit.jupiter.api.Assertions.assertFalse(buffer.put("two")); // not empty
        org.junit.jupiter.api.Assertions.assertFalse(buffer.put("three")); // overwrite
    }

    @Test
    void testOverwriteAndGetAllElementsCorrectly() {
        CircularBuffer<Integer> buffer = new CircularBuffer<>(3);

        buffer.put(1);
        buffer.put(2);
        buffer.put(3);
        buffer.put(4); // Overwrites 1
        buffer.put(5); // Overwrites 2

        assertEquals(3, buffer.get());
        assertEquals(4, buffer.get());
        assertEquals(5, buffer.get());
        assertNull(buffer.get());
    }

    @Test
    void testBufferWithOneElementCapacity() {
        CircularBuffer<String> buffer = new CircularBuffer<>(1);

        assertTrue(buffer.put("first"));
        assertEquals("first", buffer.get());
        assertNull(buffer.get());

        assertTrue(buffer.put("second"));
        assertEquals("second", buffer.get());
    }

    @Test
    void testPointerWraparoundWithExactMultipleOfCapacity() {
        CircularBuffer<Integer> buffer = new CircularBuffer<>(3);
        for (int i = 0; i < 6; i++) {
            buffer.put(i);
            buffer.get(); // keep buffer size at 0
        }
        assertTrue(buffer.isEmpty());
        assertNull(buffer.get());
    }
}
