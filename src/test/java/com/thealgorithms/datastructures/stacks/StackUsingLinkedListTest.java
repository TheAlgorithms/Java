package com.thealgorithms.datastructures.stacks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class StackUsingLinkedListTest {
    @Test
    void testPushAndPop() {
        StackUsingLinkedList<Integer> stack = new StackUsingLinkedList<>();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        assertEquals(30, stack.pop());
        assertEquals(20, stack.pop());
        assertEquals(10, stack.pop());
    }

    @Test
    void testPeek() {
        StackUsingLinkedList<Integer> stack = new StackUsingLinkedList<>();
        stack.push(100);
        stack.push(200);
        assertEquals(200, stack.peek());
        assertEquals(200, stack.peek());
        assertEquals(2, stack.size());
    }

    @Test
    void testIsEmpty() {
        StackUsingLinkedList<Integer> stack = new StackUsingLinkedList<>();
        assertTrue(stack.isEmpty());
        stack.push(5);
        assertFalse(stack.isEmpty());
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test
    void testSize() {
        StackUsingLinkedList<Integer> stack = new StackUsingLinkedList<>();
        assertEquals(0, stack.size());
        stack.push(1);
        assertEquals(1, stack.size());
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.size());
        stack.pop();
        assertEquals(2, stack.size());
    }

    @Test
    void testPopEmptyStack() {
        StackUsingLinkedList<Integer> stack = new StackUsingLinkedList<>();
        assertThrows(RuntimeException.class, stack::pop);
    }

    @Test
    void testPeekEmptyStack() {
        StackUsingLinkedList<Integer> stack = new StackUsingLinkedList<>();
        assertThrows(RuntimeException.class, stack::peek);
    }

    @Test
    void testMultipleOperations() {
        StackUsingLinkedList<Integer> stack = new StackUsingLinkedList<>();
        assertTrue(stack.isEmpty());
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.pop());
        stack.push(3);
        stack.push(4);
        assertEquals(4, stack.peek());
        assertEquals(3, stack.size());
        assertFalse(stack.isEmpty());
    }

    @Test
    void testSingleElement() {
        StackUsingLinkedList<Integer> stack = new StackUsingLinkedList<>();
        stack.push(42);
        assertEquals(42, stack.peek());
        assertEquals(1, stack.size());
        assertEquals(42, stack.pop());
        assertTrue(stack.isEmpty());
    }
}