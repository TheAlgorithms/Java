package com.thealgorithms.datastructures.stacks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StackOfLinkedListTest {

    private LinkedListStack stack;

    @BeforeEach
    public void setUp() {
        stack = new LinkedListStack();
    }

    @Test
    public void testPushAndPeek() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(3, stack.peek(), "Peek should return the last pushed value");
        assertEquals(3, stack.getSize(), "Size should reflect the number of elements");
    }

    @Test
    public void testPop() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(3, stack.pop(), "Pop should return the last pushed value");
        assertEquals(2, stack.pop(), "Pop should return the next last pushed value");
        assertEquals(1, stack.pop(), "Pop should return the first pushed value");
        assertTrue(stack.isEmpty(), "Stack should be empty after popping all elements");
    }

    @Test
    public void testPopEmptyStack() {
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchElementException.class, () -> stack.pop(), "Popping from an empty stack should throw NoSuchElementException");
    }

    @Test
    public void testPeekEmptyStack() {
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchElementException.class, () -> stack.peek(), "Peeking into an empty stack should throw NoSuchElementException");
    }

    @Test
    public void testIsEmpty() {
        assertTrue(stack.isEmpty(), "Newly created stack should be empty");

        stack.push(1);
        assertFalse(stack.isEmpty(), "Stack should not be empty after pushing an element");

        stack.pop();
        assertTrue(stack.isEmpty(), "Stack should be empty after popping the only element");
    }

    @Test
    public void testToString() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals("3->2->1", stack.toString(), "String representation of stack should match the expected format");
    }

    @Test
    public void testMultiplePushesAndPops() {
        stack.push(5);
        stack.push(10);
        stack.push(15);

        assertEquals(15, stack.pop(), "Pop should return the last pushed value");
        assertEquals(10, stack.peek(), "Peek should return the new top value after popping");
        assertEquals(10, stack.pop(), "Pop should return the next last pushed value");
        assertEquals(5, stack.pop(), "Pop should return the first pushed value");
        assertTrue(stack.isEmpty(), "Stack should be empty after popping all elements");
    }

    @Test
    public void testGetSize() {
        assertEquals(0, stack.getSize(), "Size of an empty stack should be zero");
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.getSize(), "Size should reflect the number of elements");
        stack.pop();
        assertEquals(1, stack.getSize(), "Size should decrease with each pop");
    }

    @Test
    public void testSizeAfterClearingStack() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        // Manually clear the stack
        while (!stack.isEmpty()) {
            stack.pop();
        }
        assertTrue(stack.isEmpty(), "Stack should be empty after clearing");
        assertEquals(0, stack.getSize(), "Size should be zero after clearing the stack");
    }

    @Test
    public void testSequentialPushAndPop() {
        for (int i = 1; i <= 100; i++) {
            stack.push(i);
        }
        assertEquals(100, stack.getSize(), "Size should be 100 after pushing 100 elements");

        for (int i = 100; i >= 1; i--) {
            assertEquals(i, stack.pop(), "Popping should return values in LIFO order");
        }
        assertTrue(stack.isEmpty(), "Stack should be empty after popping all elements");
    }
}
