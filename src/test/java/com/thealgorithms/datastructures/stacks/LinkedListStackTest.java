package com.thealgorithms.datastructures.stacks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LinkedListStackTest {

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

        assertEquals(3, stack.peek());
        assertEquals(3, stack.getSize());
    }

    @Test
    public void testPop() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testPopEmptyStack() {
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchElementException.class, () -> stack.pop());
    }

    @Test
    public void testPeekEmptyStack() {
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchElementException.class, () -> stack.peek());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(stack.isEmpty());

        stack.push(1);
        assertFalse(stack.isEmpty());

        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testToString() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals("3->2->1", stack.toString());
    }
}
