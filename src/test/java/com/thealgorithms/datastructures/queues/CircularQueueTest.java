package com.thealgorithms.datastructures.queues;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CircularQueueTest {

    @Test
    void testEnQueue() {
        CircularQueue<Integer> cq = new CircularQueue<>(3);
        cq.enQueue(1);
        cq.enQueue(2);
        cq.enQueue(3);

        assertEquals(1, cq.peek());
        assertTrue(cq.isFull());
    }

    @Test
    void testDeQueue() {
        CircularQueue<Integer> cq = new CircularQueue<>(3);
        cq.enQueue(1);
        cq.enQueue(2);
        cq.enQueue(3);

        assertEquals(1, cq.deQueue());
        assertEquals(2, cq.peek());
        assertFalse(cq.isFull());
    }

    @Test
    void testIsEmpty() {
        CircularQueue<Integer> cq = new CircularQueue<>(3);
        assertTrue(cq.isEmpty());

        cq.enQueue(1);
        assertFalse(cq.isEmpty());
    }

    @Test
    void testIsFull() {
        CircularQueue<Integer> cq = new CircularQueue<>(2);
        cq.enQueue(1);
        cq.enQueue(2);
        assertTrue(cq.isFull());

        cq.deQueue();
        assertFalse(cq.isFull());
    }

    @Test
    void testPeek() {
        CircularQueue<Integer> cq = new CircularQueue<>(3);
        cq.enQueue(1);
        cq.enQueue(2);

        assertEquals(1, cq.peek());
        assertEquals(1, cq.peek()); // Ensure peek doesn't remove the element
    }

    @Test
    void testDeleteQueue() {
        CircularQueue<Integer> cq = new CircularQueue<>(3);
        cq.enQueue(1);
        cq.enQueue(2);
        cq.deleteQueue();

        org.junit.jupiter.api.Assertions.assertThrows(IllegalStateException.class, cq::peek);
    }

    @Test
    void testEnQueueOnFull() {
        CircularQueue<Integer> cq = new CircularQueue<>(2);
        cq.enQueue(1);
        cq.enQueue(2);

        org.junit.jupiter.api.Assertions.assertThrows(IllegalStateException.class, () -> cq.enQueue(3));
    }

    @Test
    void testDeQueueOnEmpty() {
        CircularQueue<Integer> cq = new CircularQueue<>(2);
        org.junit.jupiter.api.Assertions.assertThrows(IllegalStateException.class, cq::deQueue);
    }

    @Test
    void testPeekOnEmpty() {
        CircularQueue<Integer> cq = new CircularQueue<>(2);
        org.junit.jupiter.api.Assertions.assertThrows(IllegalStateException.class, cq::peek);
    }

    @Test
    void testSize() {
        CircularQueue<Integer> cq = new CircularQueue<>(3);
        cq.enQueue(1);
        cq.enQueue(2);

        assertEquals(2, cq.size());

        cq.deQueue();
        assertEquals(1, cq.size());
    }

    @Test
    void testCircularWrapAround() {
        CircularQueue<Integer> cq = new CircularQueue<>(3);
        cq.enQueue(1);
        cq.enQueue(2);
        cq.enQueue(3);

        cq.deQueue();
        cq.enQueue(4);

        assertEquals(2, cq.deQueue());
        assertEquals(3, cq.deQueue());
        assertEquals(4, cq.deQueue());
        assertTrue(cq.isEmpty());
    }

    @Test
    void testEnQueueDeQueueMultipleTimes() {
        CircularQueue<Integer> cq = new CircularQueue<>(3);
        cq.enQueue(1);
        cq.enQueue(2);
        cq.deQueue();
        cq.enQueue(3);
        cq.enQueue(4);

        assertTrue(cq.isFull());
        assertEquals(2, cq.deQueue());
        assertEquals(3, cq.deQueue());
        assertEquals(4, cq.deQueue());
        assertTrue(cq.isEmpty());
    }

    @Test
    void testMultipleWrapArounds() {
        CircularQueue<Integer> cq = new CircularQueue<>(3);
        cq.enQueue(1);
        cq.deQueue();
        cq.enQueue(2);
        cq.deQueue();
        cq.enQueue(3);
        cq.deQueue();
        cq.enQueue(4);

        assertEquals(4, cq.peek());
    }

    @Test
    void testSizeDuringOperations() {
        CircularQueue<Integer> cq = new CircularQueue<>(3);
        assertEquals(0, cq.size());

        cq.enQueue(1);
        cq.enQueue(2);
        assertEquals(2, cq.size());

        cq.deQueue();
        assertEquals(1, cq.size());

        cq.enQueue(3);
        cq.enQueue(4);
        assertEquals(3, cq.size());
        cq.deQueue();
        cq.deQueue();
        assertEquals(1, cq.size());
    }
}
