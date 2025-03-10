package com.thealgorithms.datastructures.queues;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class GenericArrayListQueueTest {

    @Test
    void testAdd() {
        GenericArrayListQueue<Integer> queue = new GenericArrayListQueue<>();
        assertTrue(queue.add(10));
        assertTrue(queue.add(20));
        assertEquals(10, queue.peek()); // Ensure the first added element is at the front
    }

    @Test
    void testPeek() {
        GenericArrayListQueue<Integer> queue = new GenericArrayListQueue<>();
        assertNull(queue.peek(), "Peek should return null for an empty queue");

        queue.add(10);
        queue.add(20);

        assertEquals(10, queue.peek(), "Peek should return the first element (10)");
        queue.poll();
        assertEquals(20, queue.peek(), "Peek should return the next element (20) after poll");
    }

    @Test
    void testPoll() {
        GenericArrayListQueue<Integer> queue = new GenericArrayListQueue<>();
        assertNull(queue.poll(), "Poll should return null for an empty queue");

        queue.add(10);
        queue.add(20);

        assertEquals(10, queue.poll(), "Poll should return and remove the first element (10)");
        assertEquals(20, queue.poll(), "Poll should return and remove the next element (20)");
        assertNull(queue.poll(), "Poll should return null when queue is empty after removals");
    }

    @Test
    void testIsEmpty() {
        GenericArrayListQueue<Integer> queue = new GenericArrayListQueue<>();
        assertTrue(queue.isEmpty(), "Queue should initially be empty");

        queue.add(30);
        assertFalse(queue.isEmpty(), "Queue should not be empty after adding an element");
        queue.poll();
        assertTrue(queue.isEmpty(), "Queue should be empty after removing the only element");
    }

    @Test
    void testClearQueueAndReuse() {
        GenericArrayListQueue<Integer> queue = new GenericArrayListQueue<>();
        queue.add(5);
        queue.add(10);
        queue.poll();
        queue.poll(); // Remove all elements

        assertTrue(queue.isEmpty(), "Queue should be empty after all elements are removed");
        assertNull(queue.peek(), "Peek should return null on an empty queue after clear");
        assertTrue(queue.add(15), "Queue should be reusable after being emptied");
        assertEquals(15, queue.peek(), "Newly added element should be accessible in the empty queue");
    }

    @Test
    void testOrderMaintained() {
        GenericArrayListQueue<String> queue = new GenericArrayListQueue<>();
        queue.add("First");
        queue.add("Second");
        queue.add("Third");

        assertEquals("First", queue.poll(), "Order should be maintained; expected 'First'");
        assertEquals("Second", queue.poll(), "Order should be maintained; expected 'Second'");
        assertEquals("Third", queue.poll(), "Order should be maintained; expected 'Third'");
    }

    @Test
    void testVariousDataTypes() {
        GenericArrayListQueue<Double> queue = new GenericArrayListQueue<>();
        queue.add(1.1);
        queue.add(2.2);

        assertEquals(1.1, queue.peek(), "Queue should handle Double data type correctly");
        assertEquals(1.1, queue.poll(), "Poll should return correct Double value");
        assertEquals(2.2, queue.peek(), "Peek should show next Double value in the queue");
    }

    @Test
    void testEmptyPollAndPeekBehavior() {
        GenericArrayListQueue<Integer> queue = new GenericArrayListQueue<>();
        assertNull(queue.peek(), "Peek on an empty queue should return null");
        assertNull(queue.poll(), "Poll on an empty queue should return null");
    }
}
