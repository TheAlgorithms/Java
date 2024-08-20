package com.thealgorithms.datastructures.stacks;

import java.util.EmptyStackException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StackArrayListTest {

    private StackArrayList<Integer> stack;

    @BeforeEach
    void setUp() {
        stack = new StackArrayList<>();
    }

    @Test
    void testPushAndPop() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        Assertions.assertEquals(3, stack.pop());
        Assertions.assertEquals(2, stack.pop());
        Assertions.assertEquals(1, stack.pop());
    }

    @Test
    void testPeek() {
        stack.push(10);
        stack.push(20);

        Assertions.assertEquals(20, stack.peek());
        stack.pop(); // Remove 20
        Assertions.assertEquals(10, stack.peek());
    }

    @Test
    void testIsEmpty() {
        Assertions.assertTrue(stack.isEmpty());
        stack.push(1);
        Assertions.assertFalse(stack.isEmpty());
        stack.pop();
        Assertions.assertTrue(stack.isEmpty());
    }

    @Test
    void testMakeEmpty() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.makeEmpty();
        Assertions.assertTrue(stack.isEmpty());
        Assertions.assertEquals(0, stack.size());
    }

    @Test
    void testSize() {
        Assertions.assertEquals(0, stack.size());
        stack.push(1);
        stack.push(2);
        Assertions.assertEquals(2, stack.size());
        stack.pop();
        Assertions.assertEquals(1, stack.size());
    }

    @Test
    void testPopEmptyStackThrowsException() {
        Assertions.assertThrows(EmptyStackException.class, stack::pop);
    }

    @Test
    void testPeekEmptyStackThrowsException() {
        Assertions.assertThrows(EmptyStackException.class, stack::peek);
    }
}
