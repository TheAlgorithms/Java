package com.thealgorithms.datastructures.queues;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

class DequeTest {

    @Test
    void testAddFirst() {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(10);
        assertEquals(10, deque.peekFirst());
        assertEquals(10, deque.peekLast());
        assertEquals(1, deque.size());
    }

    @Test
    void testAddLast() {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(20);
        assertEquals(20, deque.peekFirst());
        assertEquals(20, deque.peekLast());
        assertEquals(1, deque.size());
    }

    @Test
    void testPollFirst() {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(10);
        deque.addLast(20);
        assertEquals(10, deque.pollFirst());
        assertEquals(20, deque.peekFirst());
        assertEquals(1, deque.size());
    }

    @Test
    void testPollLast() {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(10);
        deque.addLast(20);
        assertEquals(20, deque.pollLast());
        assertEquals(10, deque.peekLast());
        assertEquals(1, deque.size());
    }

    @Test
    void testIsEmpty() {
        Deque<Integer> deque = new Deque<>();
        org.junit.jupiter.api.Assertions.assertTrue(deque.isEmpty());
        deque.addFirst(10);
        assertFalse(deque.isEmpty());
    }

    @Test
    void testPeekFirstEmpty() {
        Deque<Integer> deque = new Deque<>();
        assertNull(deque.peekFirst());
    }

    @Test
    void testPeekLastEmpty() {
        Deque<Integer> deque = new Deque<>();
        assertNull(deque.peekLast());
    }

    @Test
    void testPollFirstEmpty() {
        Deque<Integer> deque = new Deque<>();
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchElementException.class, deque::pollFirst);
    }

    @Test
    void testPollLastEmpty() {
        Deque<Integer> deque = new Deque<>();
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchElementException.class, deque::pollLast);
    }

    @Test
    void testToString() {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(10);
        deque.addLast(20);
        deque.addFirst(5);
        assertEquals("Head -> 5 <-> 10 <-> 20 <- Tail", deque.toString());
    }

    @Test
    void testAlternatingAddRemove() {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(0);
        assertEquals(0, deque.pollFirst());
        assertEquals(2, deque.pollLast());
        assertEquals(1, deque.pollFirst());
        org.junit.jupiter.api.Assertions.assertTrue(deque.isEmpty());
    }

    @Test
    void testSizeAfterOperations() {
        Deque<Integer> deque = new Deque<>();
        assertEquals(0, deque.size());
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(3);
        assertEquals(3, deque.size());
        deque.pollFirst();
        deque.pollLast();
        assertEquals(1, deque.size());
    }

    @Test
    void testNullValues() {
        Deque<String> deque = new Deque<>();
        deque.addFirst(null);
        assertNull(deque.peekFirst());
        assertNull(deque.pollFirst());
        org.junit.jupiter.api.Assertions.assertTrue(deque.isEmpty());
    }

    @Test
    void testMultipleAddFirst() {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);

        assertEquals(3, deque.peekFirst(), "First element should be the last added to front");
        assertEquals(1, deque.peekLast(), "Last element should be the first added to front");
        assertEquals(3, deque.size(), "Size should reflect all additions");
    }

    @Test
    void testMultipleAddLast() {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);

        assertEquals(1, deque.peekFirst(), "First element should be the first added to back");
        assertEquals(3, deque.peekLast(), "Last element should be the last added to back");
        assertEquals(3, deque.size(), "Size should reflect all additions");
    }

    @Test
    void testSingleElementOperations() {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);

        assertEquals(1, deque.peekFirst(), "Single element should be both first and last");
        assertEquals(1, deque.peekLast(), "Single element should be both first and last");
        assertEquals(1, deque.size());

        assertEquals(1, deque.pollFirst(), "Should be able to poll single element from front");
        org.junit.jupiter.api.Assertions.assertTrue(deque.isEmpty(), "Deque should be empty after polling single element");
    }

    @Test
    void testSingleElementPollLast() {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(1);

        assertEquals(1, deque.pollLast(), "Should be able to poll single element from back");
        org.junit.jupiter.api.Assertions.assertTrue(deque.isEmpty(), "Deque should be empty after polling single element");
    }

    @Test
    void testMixedNullAndValues() {
        Deque<String> deque = new Deque<>();
        deque.addFirst("first");
        deque.addLast(null);
        deque.addFirst(null);
        deque.addLast("last");

        assertEquals(4, deque.size(), "Should handle mixed null and non-null values");
        assertNull(deque.pollFirst(), "Should poll null from front");
        assertEquals("first", deque.pollFirst(), "Should poll non-null value");
        assertNull(deque.pollLast().equals("last") ? null : deque.peekLast(), "Should handle null correctly");
    }

    @Test
    void testSymmetricOperations() {
        Deque<Integer> deque = new Deque<>();

        // Test that addFirst/pollFirst and addLast/pollLast are symmetric
        deque.addFirst(1);
        deque.addLast(2);

        assertEquals(1, deque.pollFirst(), "addFirst/pollFirst should be symmetric");
        assertEquals(2, deque.pollLast(), "addLast/pollLast should be symmetric");
        org.junit.jupiter.api.Assertions.assertTrue(deque.isEmpty(), "Deque should be empty after symmetric operations");
    }
}
