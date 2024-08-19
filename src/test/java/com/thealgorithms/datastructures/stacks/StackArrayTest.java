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

        Assertions.assertEquals(5, stack.pop()); // Stack follows LIFO, so 5 should be popped first
        Assertions.assertEquals(4, stack.pop()); // Next, 4 should be popped
        Assertions.assertEquals(3, stack.pop()); // Followed by 3
        Assertions.assertEquals(2, stack.pop()); // Then 2
        Assertions.assertEquals(1, stack.pop()); // Finally 1
    }

    @Test
    void testPeek() {
        stack.push(10);
        stack.push(20);
        stack.push(30);

        Assertions.assertEquals(30, stack.peek()); // Peek should return 30, the top of the stack
        Assertions.assertEquals(3, stack.size()); // Size should remain 3 after peek

        stack.pop();
        Assertions.assertEquals(20, stack.peek()); // After popping, peek should return 20
    }

    @Test
    void testIsEmpty() {
        Assertions.assertTrue(stack.isEmpty()); // Initially, the stack should be empty
        stack.push(42);
        Assertions.assertFalse(stack.isEmpty()); // After pushing an element, the stack should not be empty
        stack.pop();
        Assertions.assertTrue(stack.isEmpty()); // After popping the only element, the stack should be empty again
    }

    @Test
    void testResizeOnPush() {
        StackArray<Integer> smallStack = new StackArray<>(2); // Start with a small stack size
        smallStack.push(1);
        smallStack.push(2);
        Assertions.assertTrue(smallStack.isFull()); // Initially, the stack should be full

        smallStack.push(3); // This push should trigger a resize
        Assertions.assertFalse(smallStack.isFull()); // The stack should no longer be full after resize
        Assertions.assertEquals(3, smallStack.size()); // Size should be 3 after pushing 3 elements

        Assertions.assertEquals(3, smallStack.pop()); // LIFO behavior check
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

        stack.pop(); // Removing elements should trigger a resize when less than 1/4 of the stack is used
        stack.pop();
        stack.pop();
        Assertions.assertEquals(1, stack.size()); // After popping, only one element should remain

        stack.pop();
        Assertions.assertTrue(stack.isEmpty()); // The stack should be empty now
    }

    @Test
    void testMakeEmpty() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.makeEmpty();

        Assertions.assertTrue(stack.isEmpty()); // The stack should be empty after calling makeEmpty
        Assertions.assertThrows(IllegalStateException.class, stack::pop); // Popping from empty stack should throw exception
    }

    @Test
    void testPopEmptyStackThrowsException() {
        Assertions.assertThrows(IllegalStateException.class, stack::pop); // Popping from an empty stack should throw an exception
    }

    @Test
    void testPeekEmptyStackThrowsException() {
        Assertions.assertThrows(IllegalStateException.class, stack::peek); // Peeking into an empty stack should throw an exception
    }

    @Test
    void testConstructorWithInvalidSizeThrowsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new StackArray<>(0)); // Size 0 is invalid
        Assertions.assertThrows(IllegalArgumentException.class, () -> new StackArray<>(-5)); // Negative size is invalid
    }

    @Test
    void testDefaultConstructor() {
        StackArray<Integer> defaultStack = new StackArray<>(); // Using default constructor
        Assertions.assertEquals(0, defaultStack.size()); // Initially, size should be 0

        defaultStack.push(1);
        Assertions.assertEquals(1, defaultStack.size()); // After pushing, size should be 1
    }
}
