package com.thealgorithms.stacks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StackUsingTwoQueuesTest {

    private StackUsingTwoQueues stack;

    @BeforeEach
    public void setUp() {
        stack = new StackUsingTwoQueues();
    }

    @Test
    public void testPushAndPeek() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.peek());
    }

    @Test
    public void testPop() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }

    @Test
    public void testPeek() {
        stack.push(10);
        stack.push(20);
        assertEquals(20, stack.peek());
        stack.pop();
        assertEquals(10, stack.peek());
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
    public void testSize() {
        assertEquals(0, stack.size());
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.size());
        stack.pop();
        assertEquals(1, stack.size());
    }

    @Test
    public void testPeekEmptyStack() {
        assertNull(stack.peek());
    }
}
