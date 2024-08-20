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
}
