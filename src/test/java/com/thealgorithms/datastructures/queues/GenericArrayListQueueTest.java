package com.thealgorithms.datastructures.queues;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class GenericArrayListQueueTest {

    @Test
    void testAdd() {
        GenericArrayListQueue<Integer> queue = new GenericArrayListQueue<>();
        assertTrue(queue.add(10));
        assertTrue(queue.add(20));
    }

    @Test
    void testPeek() {
        GenericArrayListQueue<Integer> queue = new GenericArrayListQueue<>();
        assertNull(queue.peek());

        queue.add(10);
        queue.add(20);

        assertEquals(10, queue.peek());
        queue.poll();
        assertEquals(20, queue.peek());
    }

    @Test
    void testPoll() {
        GenericArrayListQueue<Integer> queue = new GenericArrayListQueue<>();
        assertNull(queue.poll());

        queue.add(10);
        queue.add(20);

        assertEquals(10, queue.poll());
        assertEquals(20, queue.poll());
        assertNull(queue.poll());
    }

    @Test
    void testIsEmpty() {
        GenericArrayListQueue<Integer> queue = new GenericArrayListQueue<>();
        assertNull(queue.peek());
        assertNull(queue.poll());

        queue.add(30);
        assertEquals(30, queue.peek());
        assertEquals(30, queue.poll());
        assertNull(queue.peek());
    }
}
