package com.thealgorithms.datastructures.queues;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import java.util.Queue;
import org.junit.jupiter.api.Test;

class ReverseQueueRecursionTest {
    @Test
    void testReverseMultipleElements() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        ReverseQueueRecursion.reverseQueue(queue);
        assertEquals(4, queue.poll());
        assertEquals(3, queue.poll());
        assertEquals(2, queue.poll());
        assertEquals(1, queue.poll());
        assertTrue(queue.isEmpty());
    }

    @Test
    void testReverseSingleElement() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(42);
        ReverseQueueRecursion.reverseQueue(queue);
        assertEquals(42, queue.poll());
        assertTrue(queue.isEmpty());
    }

    @Test
    void testReverseEmptyQueue() {
        Queue<Integer> queue = new LinkedList<>();
        ReverseQueueRecursion.reverseQueue(queue);
        assertTrue(queue.isEmpty());
    }

    @Test
    void testReverseStringQueue() {
        Queue<String> queue = new LinkedList<>();
        queue.add("A");
        queue.add("B");
        queue.add("C");
        ReverseQueueRecursion.reverseQueue(queue);
        assertEquals("C", queue.poll());
        assertEquals("B", queue.poll());
        assertEquals("A", queue.poll());
        assertTrue(queue.isEmpty());
    }
}
