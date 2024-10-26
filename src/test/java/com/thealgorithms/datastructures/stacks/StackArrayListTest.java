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

        Assertions.assertEquals(20, stack.peek()); // Peek should return the top element
        stack.pop(); // Remove top element
        Assertions.assertEquals(10, stack.peek()); // Peek should now return the new top element
    }

    @Test
    void testIsEmpty() {
        Assertions.assertTrue(stack.isEmpty()); // Stack should initially be empty
        stack.push(1);
        Assertions.assertFalse(stack.isEmpty()); // After pushing, stack should not be empty
        stack.pop();
        Assertions.assertTrue(stack.isEmpty()); // After popping, stack should be empty again
    }

    @Test
    void testMakeEmpty() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.makeEmpty();
        Assertions.assertTrue(stack.isEmpty()); // Stack should be empty after makeEmpty is called
        Assertions.assertEquals(0, stack.size()); // Size should be 0 after makeEmpty
    }

    @Test
    void testSize() {
        Assertions.assertEquals(0, stack.size()); // Initial size should be 0
        stack.push(1);
        stack.push(2);
        Assertions.assertEquals(2, stack.size()); // Size should reflect number of elements added
        stack.pop();
        Assertions.assertEquals(1, stack.size()); // Size should decrease with elements removed
    }

    @Test
    void testPopEmptyStackThrowsException() {
        Assertions.assertThrows(EmptyStackException.class, stack::pop); // Popping from an empty stack should throw an exception
    }

    @Test
    void testPeekEmptyStackThrowsException() {
        Assertions.assertThrows(EmptyStackException.class, stack::peek); // Peeking into an empty stack should throw an exception
    }

    @Test
    void testMixedOperations() {
        // Testing a mix of push, pop, peek, and size operations in sequence
        stack.push(5);
        stack.push(10);
        stack.push(15);

        Assertions.assertEquals(3, stack.size()); // Size should reflect number of elements
        Assertions.assertEquals(15, stack.peek()); // Peek should show last element added

        stack.pop(); // Remove top element
        Assertions.assertEquals(10, stack.peek()); // New top should be 10
        Assertions.assertEquals(2, stack.size()); // Size should reflect removal

        stack.push(20); // Add a new element
        Assertions.assertEquals(20, stack.peek()); // Top should be the last added element
    }

    @Test
    void testMultipleMakeEmptyCalls() {
        // Ensures calling makeEmpty multiple times does not throw errors or misbehave
        stack.push(1);
        stack.push(2);
        stack.makeEmpty();
        Assertions.assertTrue(stack.isEmpty());

        stack.makeEmpty();
        Assertions.assertTrue(stack.isEmpty());
        Assertions.assertEquals(0, stack.size());
    }
}
