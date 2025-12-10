package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.EmptyStackException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QueueUsingTwoStacksTest {

    private QueueUsingTwoStacks queue;

    @BeforeEach
    void setUp() {
        queue = new QueueUsingTwoStacks();
    }

    @Test
    void testIsEmptyInitially() {
        assertTrue(queue.isEmpty(), "Queue should be empty initially");
    }

    @Test
    void testInsertSingleElement() {
        queue.insert(1);
        assertFalse(queue.isEmpty(), "Queue should not be empty after inserting an element");
        assertEquals(1, queue.peekFront(), "The front element should be the inserted element");
    }

    @Test
    void testRemoveSingleElement() {
        queue.insert(1);
        assertEquals(1, queue.remove(), "Removing should return the first inserted element");
        assertTrue(queue.isEmpty(), "Queue should be empty after removing the only element");
    }

    @Test
    void testRemoveMultipleElements() {
        queue.insert(1);
        queue.insert(2);
        queue.insert(3);
        assertEquals(1, queue.remove(), "First removed element should be the first inserted element");
        assertEquals(2, queue.remove(), "Second removed element should be the second inserted element");
        assertEquals(3, queue.remove(), "Third removed element should be the third inserted element");
        assertTrue(queue.isEmpty(), "Queue should be empty after removing all elements");
    }

    @Test
    void testPeekFrontWithMultipleElements() {
        queue.insert(1);
        queue.insert(2);
        queue.insert(3);
        assertEquals(1, queue.peekFront(), "The front element should be the first inserted element");
    }

    @Test
    void testPeekBackWithMultipleElements() {
        queue.insert(1);
        queue.insert(2);
        queue.insert(3);
        assertEquals(3, queue.peekBack(), "The back element should be the last inserted element");
    }

    @Test
    void testPeekFrontAfterRemovals() {
        queue.insert(1);
        queue.insert(2);
        queue.insert(3);
        queue.remove();
        assertEquals(2, queue.peekFront(), "After removing one element, the front should be the second element");
    }

    @Test
    void testIsEmptyAfterRemovals() {
        queue.insert(1);
        queue.insert(2);
        queue.remove();
        queue.remove();
        assertTrue(queue.isEmpty(), "Queue should be empty after removing all elements");
    }

    @Test
    void testRemoveFromEmptyQueue() {
        org.junit.jupiter.api.Assertions.assertThrows(EmptyStackException.class, queue::remove, "Removing from an empty queue should throw an exception");
    }

    @Test
    void testPeekFrontFromEmptyQueue() {
        org.junit.jupiter.api.Assertions.assertThrows(EmptyStackException.class, queue::peekFront, "Peeking front from an empty queue should throw an exception");
    }

    @Test
    void testPeekBackFromEmptyQueue() {
        org.junit.jupiter.api.Assertions.assertThrows(EmptyStackException.class, queue::peekBack, "Peeking back from an empty queue should throw an exception");
    }

    @Test
    void testIsInStackEmptyInitially() {
        assertTrue(queue.isInStackEmpty(), "inStack should be empty initially");
    }

    @Test
    void testIsOutStackEmptyInitially() {
        assertTrue(queue.isOutStackEmpty(), "outStack should be empty initially");
    }

    @Test
    void testIsInStackEmptyAfterInsertion() {
        queue.insert(1);
        assertFalse(queue.isInStackEmpty(), "inStack should not be empty after an insertion");
    }

    @Test
    void testIsOutStackEmptyAfterInsertion() {
        queue.insert(1);
        assertTrue(queue.isOutStackEmpty(), "outStack should still be empty after an insertion");
    }

    @Test
    void testIsOutStackEmptyAfterRemoval() {
        queue.insert(1);
        queue.remove();
        assertTrue(queue.isOutStackEmpty(), "outStack should be empty after removing the only element");
    }

    @Test
    void testIsInStackEmptyAfterMultipleRemovals() {
        queue.insert(1);
        queue.insert(2);
        queue.remove();
        queue.remove();
        assertTrue(queue.isInStackEmpty(), "inStack should be empty after removing all elements");
    }

    @Test
    void testIsOutStackEmptyAfterMultipleRemovals() {
        queue.insert(1);
        queue.insert(2);
        queue.remove();
        queue.remove();
        assertTrue(queue.isOutStackEmpty(), "outStack should be empty after removing all elements");
    }
}
