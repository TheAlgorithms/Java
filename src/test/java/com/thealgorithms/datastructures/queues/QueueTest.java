package com.thealgorithms.datastructures.queues;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QueueTest {

    private static final int INITIAL_CAPACITY = 3;
    private Queue<Integer> queue;

    @BeforeEach
    void setUp() {
        queue = new Queue<>(INITIAL_CAPACITY);
    }

    @Test
    void testQueueInsertion() {
        Assertions.assertTrue(queue.insert(1));
        Assertions.assertTrue(queue.insert(2));
        Assertions.assertTrue(queue.insert(3));
        Assertions.assertFalse(queue.insert(4)); // Queue is full

        Assertions.assertEquals(1, queue.peekFront());
        Assertions.assertEquals(3, queue.peekRear());
        Assertions.assertEquals(3, queue.getSize());
    }

    @Test
    void testQueueRemoval() {
        queue.insert(1);
        queue.insert(2);
        queue.insert(3);

        Assertions.assertEquals(1, queue.remove());
        Assertions.assertEquals(2, queue.peekFront());
        Assertions.assertEquals(2, queue.getSize());

        Assertions.assertEquals(2, queue.remove());
        Assertions.assertEquals(3, queue.peekFront());
        Assertions.assertEquals(1, queue.getSize());

        Assertions.assertEquals(3, queue.remove());
        Assertions.assertTrue(queue.isEmpty());

        Assertions.assertThrows(IllegalStateException.class, queue::remove); // Queue is empty
    }

    @Test
    void testPeekFrontAndRear() {
        queue.insert(1);
        queue.insert(2);

        Assertions.assertEquals(1, queue.peekFront());
        Assertions.assertEquals(2, queue.peekRear());

        queue.insert(3);
        Assertions.assertEquals(1, queue.peekFront());
        Assertions.assertEquals(3, queue.peekRear());
    }

    @Test
    void testQueueIsEmptyAndIsFull() {
        Assertions.assertTrue(queue.isEmpty());
        Assertions.assertFalse(queue.isFull());

        queue.insert(1);
        queue.insert(2);
        queue.insert(3);

        Assertions.assertFalse(queue.isEmpty());
        Assertions.assertTrue(queue.isFull());

        queue.remove();
        Assertions.assertFalse(queue.isFull());
        Assertions.assertFalse(queue.isEmpty());
    }

    @Test
    void testQueueSize() {
        Assertions.assertEquals(0, queue.getSize());
        queue.insert(1);
        Assertions.assertEquals(1, queue.getSize());
        queue.insert(2);
        Assertions.assertEquals(2, queue.getSize());
        queue.insert(3);
        Assertions.assertEquals(3, queue.getSize());
        queue.remove();
        Assertions.assertEquals(2, queue.getSize());
    }

    @Test
    void testQueueToString() {
        Assertions.assertEquals("[]", queue.toString());

        queue.insert(1);
        queue.insert(2);
        Assertions.assertEquals("[1, 2]", queue.toString());

        queue.insert(3);
        Assertions.assertEquals("[1, 2, 3]", queue.toString());

        queue.remove();
        Assertions.assertEquals("[2, 3]", queue.toString());

        queue.remove();
        queue.remove();
        Assertions.assertEquals("[]", queue.toString());
    }

    @Test
    void testQueueThrowsExceptionOnEmptyPeek() {
        Assertions.assertThrows(IllegalStateException.class, queue::peekFront);
        Assertions.assertThrows(IllegalStateException.class, queue::peekRear);
    }

    @Test
    void testQueueThrowsExceptionOnRemoveFromEmptyQueue() {
        Assertions.assertThrows(IllegalStateException.class, queue::remove);
    }

    @Test
    void testQueueCapacityException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Queue<>(0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Queue<>(-5));
    }
}
