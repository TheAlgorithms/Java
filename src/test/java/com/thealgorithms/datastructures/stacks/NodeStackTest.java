package com.thealgorithms.datastructures.stacks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class NodeStackTest {

    @Test
    void testPush() {
        NodeStack<Integer> stack = new NodeStack<>();
        stack.push(10);
        stack.push(20);
        assertEquals(20, stack.peek(), "Top element should be 20 after pushing 10 and 20.");
    }

    @Test
    void testPop() {
        NodeStack<String> stack = new NodeStack<>();
        stack.push("First");
        stack.push("Second");
        assertEquals("Second", stack.pop(), "Pop should return 'Second', the last pushed element.");
        assertEquals("First", stack.pop(), "Pop should return 'First' after 'Second' is removed.");
    }

    @Test
    void testPopOnEmptyStack() {
        NodeStack<Double> stack = new NodeStack<>();
        assertThrows(IllegalStateException.class, stack::pop, "Popping an empty stack should throw IllegalStateException.");
    }

    @Test
    void testPeek() {
        NodeStack<Integer> stack = new NodeStack<>();
        stack.push(5);
        stack.push(15);
        assertEquals(15, stack.peek(), "Peek should return 15, the top element.");
        stack.pop();
        assertEquals(5, stack.peek(), "Peek should return 5 after 15 is popped.");
    }

    @Test
    void testPeekOnEmptyStack() {
        NodeStack<String> stack = new NodeStack<>();
        assertThrows(IllegalStateException.class, stack::peek, "Peeking an empty stack should throw IllegalStateException.");
    }

    @Test
    void testIsEmpty() {
        NodeStack<Character> stack = new NodeStack<>();
        assertTrue(stack.isEmpty(), "Newly initialized stack should be empty.");
        stack.push('A');
        assertFalse(stack.isEmpty(), "Stack should not be empty after a push operation.");
        stack.pop();
        assertTrue(stack.isEmpty(), "Stack should be empty after popping the only element.");
    }

    @Test
    void testSize() {
        NodeStack<Integer> stack = new NodeStack<>();
        assertEquals(0, stack.size(), "Size of empty stack should be 0.");
        stack.push(3);
        stack.push(6);
        assertEquals(2, stack.size(), "Size should be 2 after pushing two elements.");
        stack.pop();
        assertEquals(1, stack.size(), "Size should be 1 after popping one element.");
        stack.pop();
        assertEquals(0, stack.size(), "Size should be 0 after popping all elements.");
    }
}
