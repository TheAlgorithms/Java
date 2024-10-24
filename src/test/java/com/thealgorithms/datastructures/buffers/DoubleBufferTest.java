package com.thealgorithms.datastructures.buffers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DoubleBufferTest {

    private DoubleBuffer<Integer> buffer;

    @BeforeEach
    void setUp() {
        buffer = new DoubleBuffer<>(5);
    }

    @Test
    void testInitialization() {
        assertTrue(buffer.isPrimaryActive());
        assertTrue(buffer.isEmpty());
    }

    @Test
    void testPutAndGetFromPrimary() {
        buffer.put(1);
        buffer.put(2);
        buffer.put(3);

        assertEquals(3, buffer.get());
        assertEquals(2, buffer.get());
        assertEquals(1, buffer.get());
        assertNull(buffer.get());
    }

    @Test
    void testSwitchBuffers() {
        buffer.put(1);
        buffer.put(2);
        buffer.switchBuffer();

        // Now the buffer should be empty as we switched to the secondary buffer
        assertTrue(buffer.isEmpty());

        buffer.put(3);
        assertEquals(3, buffer.get());

        // Switch back to primary
        buffer.switchBuffer();
        assertEquals(2, buffer.get());
        assertEquals(1, buffer.get());
    }

    @Test
    void testEmptyBuffer() {
        assertNull(buffer.get());
        buffer.switchBuffer();
        assertNull(buffer.get());
    }

    @Test
    void testIllegalArguments() {
        assertThrows(IllegalArgumentException.class, () -> new DoubleBuffer<>(0));
        assertThrows(IllegalArgumentException.class, () -> new DoubleBuffer<>(-1));

        DoubleBuffer<String> strBuffer = new DoubleBuffer<>(1);
        assertThrows(IllegalArgumentException.class, () -> strBuffer.put(null));
    }
}
