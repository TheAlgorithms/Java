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
        assertEquals(10, queue.get());
        assertEquals(20, queue.get());
        assertEquals(30, queue.get());
    }

    @Test
    public void testInterleavedOperations() {
        queue.put(10);
        queue.put(20);
        assertEquals(10, queue.get());
        queue.put(30);
        assertEquals(20, queue.get());
        assertEquals(30, queue.get());
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
        assertThrows(NoSuchElementException.class, () -> queue.get());
    }

    @Test
    public void testDequeueAllElements() {
        for (int i = 1; i <= 5; i++) {
            queue.put(i);
        }
        for (int i = 1; i <= 5; i++) {
            assertEquals(i, queue.get());
        }
        assertEquals(0, queue.size());
    }

    @Test
    public void testLargeNumberOfOperations() {
        int n = 1000;
        for (int i = 0; i < n; i++) {
            queue.put(i);
        }
        for (int i = 0; i < n; i++) {
            assertEquals(i, queue.get());
        }
        assertEquals(0, queue.size());
    }

    @Test
    public void testRefillDuringDequeue() {
        queue.put(1);
        queue.put(2);
        assertEquals(1, queue.get());
        queue.put(3);
        queue.put(4);
        assertEquals(2, queue.get());
        assertEquals(3, queue.get());
        assertEquals(4, queue.get());
    }

    @Test
    public void testAlternatingPutAndGet() {
        queue.put(1);
        assertEquals(1, queue.get());
        queue.put(2);
        queue.put(3);
        assertEquals(2, queue.get());
        queue.put(4);
        assertEquals(3, queue.get());
        assertEquals(4, queue.get());
    }

    @Test
    public void testSizeStability() {
        queue.put(100);
        int size1 = queue.size();
        int size2 = queue.size();
        assertEquals(size1, size2);
    }

    @Test
    public void testMultipleEmptyDequeues() {
        assertThrows(NoSuchElementException.class, () -> queue.get());
        assertThrows(NoSuchElementException.class, () -> queue.get());
    }

    @Test
    public void testQueueWithStrings() {
        QueueByTwoStacks<String> stringQueue = new QueueByTwoStacks<>();
        stringQueue.put("a");
        stringQueue.put("b");
        assertEquals("a", stringQueue.get());
        assertEquals("b", stringQueue.get());
    }
}
