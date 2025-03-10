package com.thealgorithms.datastructures.queues;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LinkedQueueTest {

    private LinkedQueue<Integer> queue;

    @BeforeEach
    void setUp() {
        queue = new LinkedQueue<>();
    }

    @Test
    void testIsEmptyOnNewQueue() {
        assertTrue(queue.isEmpty(), "Queue should be empty on initialization.");
    }

    @Test
    void testEnqueueAndSize() {
        queue.enqueue(10);
        assertFalse(queue.isEmpty(), "Queue should not be empty after enqueue.");
        assertEquals(1, queue.size(), "Queue size should be 1 after one enqueue.");

        queue.enqueue(20);
        queue.enqueue(30);
        assertEquals(3, queue.size(), "Queue size should be 3 after three enqueues.");
    }

    @Test
    void testDequeueOnSingleElementQueue() {
        queue.enqueue(10);
        assertEquals(10, queue.dequeue(), "Dequeued element should be the same as the enqueued one.");
        assertTrue(queue.isEmpty(), "Queue should be empty after dequeueing the only element.");
    }

    @Test
    void testDequeueMultipleElements() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        assertEquals(10, queue.dequeue(), "First dequeued element should be the first enqueued one.");
        assertEquals(20, queue.dequeue(), "Second dequeued element should be the second enqueued one.");
        assertEquals(30, queue.dequeue(), "Third dequeued element should be the third enqueued one.");
        assertTrue(queue.isEmpty(), "Queue should be empty after dequeueing all elements.");
    }

    @Test
    void testDequeueOnEmptyQueue() {
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchElementException.class, () -> queue.dequeue(), "Dequeueing from an empty queue should throw NoSuchElementException.");
    }

    @Test
    void testPeekFrontOnEmptyQueue() {
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchElementException.class, () -> queue.peekFront(), "Peeking front on an empty queue should throw NoSuchElementException.");
    }

    @Test
    void testPeekRearOnEmptyQueue() {
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchElementException.class, () -> queue.peekRear(), "Peeking rear on an empty queue should throw NoSuchElementException.");
    }

    @Test
    void testPeekFront() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        assertEquals(10, queue.peekFront(), "Peek front should return the first enqueued element.");
        assertEquals(10, queue.peekFront(), "Peek front should not remove the element.");
        assertEquals(3, queue.size(), "Queue size should remain unchanged after peek.");
    }

    @Test
    void testPeekRear() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        assertEquals(30, queue.peekRear(), "Peek rear should return the last enqueued element.");
        assertEquals(30, queue.peekRear(), "Peek rear should not remove the element.");
        assertEquals(3, queue.size(), "Queue size should remain unchanged after peek.");
    }

    @Test
    void testPeekAtPosition() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        assertEquals(10, queue.peek(1), "Peek at position 1 should return the first enqueued element.");
        assertEquals(20, queue.peek(2), "Peek at position 2 should return the second enqueued element.");
        assertEquals(30, queue.peek(3), "Peek at position 3 should return the third enqueued element.");
    }

    @Test
    void testPeekAtInvalidPosition() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        org.junit.jupiter.api.Assertions.assertThrows(IndexOutOfBoundsException.class, () -> queue.peek(4), "Peeking at a position greater than size should throw IndexOutOfBoundsException.");
        org.junit.jupiter.api.Assertions.assertThrows(IndexOutOfBoundsException.class, () -> queue.peek(0), "Peeking at position 0 should throw IndexOutOfBoundsException.");
    }

    @Test
    void testClearQueue() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        queue.clear();
        assertTrue(queue.isEmpty(), "Queue should be empty after clear.");
        assertEquals(0, queue.size(), "Queue size should be 0 after clear.");
    }

    @Test
    void testIterator() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        Iterator<Integer> it = queue.iterator();
        assertTrue(it.hasNext(), "Iterator should have next element.");
        assertEquals(10, it.next(), "First iterator value should be the first enqueued element.");
        assertEquals(20, it.next(), "Second iterator value should be the second enqueued element.");
        assertEquals(30, it.next(), "Third iterator value should be the third enqueued element.");
        assertFalse(it.hasNext(), "Iterator should not have next element after last element.");
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchElementException.class, it::next, "Calling next() on exhausted iterator should throw NoSuchElementException.");
    }

    @Test
    void testToString() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        assertEquals("[10, 20, 30]", queue.toString(), "toString should return a properly formatted string representation of the queue.");
    }

    @Test
    void testEnqueueAfterDequeue() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        assertEquals(10, queue.dequeue(), "Dequeued element should be 10.");
        assertEquals(20, queue.dequeue(), "Dequeued element should be 20.");

        queue.enqueue(40);
        assertEquals(30, queue.peekFront(), "Peek front should return 30 after dequeuing and enqueuing new elements.");
        assertEquals(40, queue.peekRear(), "Peek rear should return 40 after enqueuing new elements.");
    }

    @Test
    void testQueueMaintainsOrder() {
        for (int i = 1; i <= 100; i++) {
            queue.enqueue(i);
        }

        for (int i = 1; i <= 100; i++) {
            assertEquals(i, queue.dequeue(), "Queue should maintain the correct order of elements.");
        }

        assertTrue(queue.isEmpty(), "Queue should be empty after dequeuing all elements.");
    }

    @Test
    void testSizeAfterOperations() {
        assertEquals(0, queue.size(), "Initial queue size should be 0.");

        queue.enqueue(10);
        assertEquals(1, queue.size(), "Queue size should be 1 after one enqueue.");

        queue.enqueue(20);
        assertEquals(2, queue.size(), "Queue size should be 2 after two enqueues.");

        queue.dequeue();
        assertEquals(1, queue.size(), "Queue size should be 1 after one dequeue.");

        queue.clear();
        assertEquals(0, queue.size(), "Queue size should be 0 after clear.");
    }

    @Test
    void testQueueToStringOnEmptyQueue() {
        assertEquals("[]", queue.toString(), "toString on empty queue should return '[]'.");
    }

    @Test
    void testEnqueueNull() {
        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> queue.enqueue(null), "Cannot enqueue null data.");
    }

    @Test
    void testIteratorOnEmptyQueue() {
        Iterator<Integer> it = queue.iterator();
        assertFalse(it.hasNext(), "Iterator on empty queue should not have next element.");
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchElementException.class, it::next, "Calling next() on empty queue iterator should throw NoSuchElementException.");
    }
}
