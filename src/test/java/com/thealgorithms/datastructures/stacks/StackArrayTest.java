package com.thealgorithms.datastructures.stacks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StackArrayTest {

    private Stack<Integer> stack;

    @BeforeEach
    void setUp() {
        stack = new StackArray<>(5); // Initialize a stack with capacity of 5
    }

    @Test
    void testPushAndPop() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        Assertions.assertEquals(5, stack.pop());
        Assertions.assertEquals(4, stack.pop());
        Assertions.assertEquals(3, stack.pop());
        Assertions.assertEquals(2, stack.pop());
        Assertions.assertEquals(1, stack.pop());
    }

    @Test
    void testPeek() {
        stack.push(10);
        stack.push(20);
        stack.push(30);

        Assertions.assertEquals(30, stack.peek());
        Assertions.assertEquals(3, stack.size());

        stack.pop();
        Assertions.assertEquals(20, stack.peek());
    }

    @Test
    void testIsEmpty() {
        Assertions.assertTrue(stack.isEmpty());
        stack.push(42);
        Assertions.assertFalse(stack.isEmpty());
        stack.pop();
        Assertions.assertTrue(stack.isEmpty());
    }

    @Test
    void testResizeOnPush() {
        StackArray<Integer> smallStack = new StackArray<>(2);
        smallStack.push(1);
        smallStack.push(2);
        Assertions.assertTrue(smallStack.isFull());

        smallStack.push(3);
        Assertions.assertFalse(smallStack.isFull());
        Assertions.assertEquals(3, smallStack.size());

        Assertions.assertEquals(3, smallStack.pop());
        Assertions.assertEquals(2, smallStack.pop());
        Assertions.assertEquals(1, smallStack.pop());
    }

    @Test
    void testResizeOnPop() {
        StackArray<Integer> stack = new StackArray<>(4);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        stack.pop();
        stack.pop();
        stack.pop();
        Assertions.assertEquals(1, stack.size());

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
        Assertions.assertThrows(IllegalStateException.class, stack::pop);
    }

    @Test
    void testPopEmptyStackThrowsException() {
        Assertions.assertThrows(IllegalStateException.class, stack::pop);
    }

    @Test
    void testPeekEmptyStackThrowsException() {
        Assertions.assertThrows(IllegalStateException.class, stack::peek);
    }

    @Test
    void testConstructorWithInvalidSizeThrowsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new StackArray<>(0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new StackArray<>(-5));
    }

    @Test
    void testDefaultConstructor() {
        StackArray<Integer> defaultStack = new StackArray<>();
        Assertions.assertEquals(0, defaultStack.size());

        defaultStack.push(1);
        Assertions.assertEquals(1, defaultStack.size());
    }

    @Test
    void testToString() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        Assertions.assertEquals("StackArray [1, 2, 3]", stack.toString());
    }
}
