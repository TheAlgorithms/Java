package com.thealgorithms.datastructures.stacks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NodeStackTest {

    private NodeStack<Integer> stack;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        stack = new NodeStack<>();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void testPushAndPeek() {
        stack.push(3);
        assertEquals(3, stack.peek(), "Peek should return the last pushed item");
        stack.push(4);
        stack.push(5);
        assertEquals(5, stack.peek(), "Peek should return the last pushed item");
    }

    @Test
    void testPop() {
        stack.push(5);
        stack.push(7);
        assertEquals(7, stack.pop(), "Pop should return the last pushed item");
        assertEquals(5, stack.pop(), "Pop should return the next item in stack");
        assertTrue(stack.isEmpty(), "Stack should be empty after popping all elements");
    }

    @Test
    void testIsEmpty() {
        assertTrue(stack.isEmpty(), "New stack should be empty");
        stack.push(1);
        assertFalse(stack.isEmpty(), "Stack should not be empty after push");
    }

    @Test
    void testSize() {
        assertEquals(0, stack.size(), "New stack should have size 0");
        stack.push(10);
        stack.push(20);
        assertEquals(2, stack.size(), "Stack size should be 2 after two pushes");
    }

    @Test
    void testPopOnEmptyStack() {
        assertThrows(IllegalStateException.class, () -> { stack.pop(); });
    }

    @Test
    void testPeekOnEmptyStack() {
        assertThrows(IllegalStateException.class, () -> { stack.peek(); });
    }

    @Test
    void testPrintEmptyStack() {
        stack.print();
        assertEquals("", outputStreamCaptor.toString().trim(), "The output of an empty stack should be an empty string.");
    }
}
