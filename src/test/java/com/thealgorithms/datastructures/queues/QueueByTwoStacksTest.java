package com.thealgorithms.datastructures.queues;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QueueByTwoStacksTest {

    private QueueByTwoStacks<Integer> queue;

    @BeforeEach
    public void setUp() {
        queue = new QueueByTwoStacks<>();
    }

    @Test
    public void testEmptyQueue() {
        assertEquals(0, queue.size());
    }

    @Test
    public void testEnqueue() {
        queue.put(10);
        queue.put(20);
        assertEquals(2, queue.size());
    }

    @Test
    public void testDequeue() {
        queue.put(10);
        queue.put(20);
        queue.put(30);
        assertEquals(10, queue.get()); // First item out
        assertEquals(20, queue.get()); // Second item out
        assertEquals(30, queue.get()); // Third item out
    }

    @Test
    public void testInterleavedOperations() {
        queue.put(10);
        queue.put(20);
        assertEquals(10, queue.get()); // Dequeue first item
        queue.put(30);
        assertEquals(20, queue.get()); // Dequeue second item
        assertEquals(30, queue.get()); // Dequeue third item
    }

    @Test
    public void testQueueSize() {
        assertEquals(0, queue.size());
        queue.put(1);
        assertEquals(1, queue.size());
        queue.put(2);
        queue.put(3);
        assertEquals(3, queue.size());
        queue.get();
        assertEquals(2, queue.size());
    }

    @Test
    public void testEmptyQueueException() {
        assertThrows(NoSuchElementException.class, () -> {
            queue.get(); // Attempting to dequeue from empty queue
        });
    }
}
