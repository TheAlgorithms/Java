package com.thealgorithms.datastructures.stacks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NodeStackTest {

    private NodeStack<Integer> intStack;
    private NodeStack<String> stringStack;

    @BeforeEach
    void setUp() {
        intStack = new NodeStack<>();
        stringStack = new NodeStack<>();
    }

    @Test
    @DisplayName("Test push operation")
    void testPush() {
        NodeStack<Integer> stack = new NodeStack<>();
        stack.push(10);
        stack.push(20);
        assertEquals(20, stack.peek(), "Top element should be 20 after pushing 10 and 20.");
    }

    @Test
    @DisplayName("Test pop operation")
    void testPop() {
        NodeStack<String> stack = new NodeStack<>();
        stack.push("First");
        stack.push("Second");
        assertEquals("Second", stack.pop(), "Pop should return 'Second', the last pushed element.");
        assertEquals("First", stack.pop(), "Pop should return 'First' after 'Second' is removed.");
    }

    @Test
    @DisplayName("Test pop on empty stack throws exception")
    void testPopOnEmptyStack() {
        NodeStack<Double> stack = new NodeStack<>();
        assertThrows(IllegalStateException.class, stack::pop, "Popping an empty stack should throw IllegalStateException.");
    }

    @Test
    @DisplayName("Test peek operation")
    void testPeek() {
        NodeStack<Integer> stack = new NodeStack<>();
        stack.push(5);
        stack.push(15);
        assertEquals(15, stack.peek(), "Peek should return 15, the top element.");
        stack.pop();
        assertEquals(5, stack.peek(), "Peek should return 5 after 15 is popped.");
    }

    @Test
    @DisplayName("Test peek on empty stack throws exception")
    void testPeekOnEmptyStack() {
        NodeStack<String> stack = new NodeStack<>();
        assertThrows(IllegalStateException.class, stack::peek, "Peeking an empty stack should throw IllegalStateException.");
    }

    @Test
    @DisplayName("Test isEmpty method")
    void testIsEmpty() {
        NodeStack<Character> stack = new NodeStack<>();
        assertTrue(stack.isEmpty(), "Newly initialized stack should be empty.");
        stack.push('A');
        org.junit.jupiter.api.Assertions.assertFalse(stack.isEmpty(), "Stack should not be empty after a push operation.");
        stack.pop();
        assertTrue(stack.isEmpty(), "Stack should be empty after popping the only element.");
    }

    @Test
    @DisplayName("Test size method")
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

    @Test
    @DisplayName("Test push and pop with null values")
    void testPushPopWithNull() {
        stringStack.push(null);
        stringStack.push("not null");
        stringStack.push(null);

        assertEquals(3, stringStack.size(), "Stack should contain 3 elements including nulls");
        org.junit.jupiter.api.Assertions.assertNull(stringStack.pop(), "Should pop null value");
        assertEquals("not null", stringStack.pop(), "Should pop 'not null' value");
        org.junit.jupiter.api.Assertions.assertNull(stringStack.pop(), "Should pop null value");
        assertTrue(stringStack.isEmpty(), "Stack should be empty after popping all elements");
    }

    @Test
    @DisplayName("Test LIFO (Last In First Out) behavior")
    void testLifoBehavior() {
        int[] values = {1, 2, 3, 4, 5};

        // Push values in order
        for (int value : values) {
            intStack.push(value);
        }

        // Pop values should be in reverse order
        for (int i = values.length - 1; i >= 0; i--) {
            assertEquals(values[i], intStack.pop(), "Elements should be popped in LIFO order");
        }
    }

    @Test
    @DisplayName("Test peek doesn't modify stack")
    void testPeekDoesNotModifyStack() {
        intStack.push(1);
        intStack.push(2);
        intStack.push(3);

        int originalSize = intStack.size();
        int peekedValue = intStack.peek();

        assertEquals(3, peekedValue, "Peek should return top element");
        assertEquals(originalSize, intStack.size(), "Peek should not change stack size");
        assertEquals(3, intStack.peek(), "Multiple peeks should return same value");
        org.junit.jupiter.api.Assertions.assertFalse(intStack.isEmpty(), "Peek should not make stack empty");
    }

    @Test
    @DisplayName("Test mixed push and pop operations")
    void testMixedOperations() {
        // Test interleaved push/pop operations
        intStack.push(1);
        assertEquals(1, intStack.pop());
        assertTrue(intStack.isEmpty());

        intStack.push(2);
        intStack.push(3);
        assertEquals(3, intStack.pop());
        intStack.push(4);
        assertEquals(4, intStack.peek());
        assertEquals(2, intStack.size());

        assertEquals(4, intStack.pop());
        assertEquals(2, intStack.pop());
        assertTrue(intStack.isEmpty());
    }

    @Test
    @DisplayName("Test stack with duplicate values")
    void testStackWithDuplicates() {
        intStack.push(1);
        intStack.push(1);
        intStack.push(1);

        assertEquals(3, intStack.size(), "Stack should handle duplicate values");
        assertEquals(1, intStack.peek(), "Peek should return duplicate value");

        assertEquals(1, intStack.pop(), "Should pop first duplicate");
        assertEquals(1, intStack.pop(), "Should pop second duplicate");
        assertEquals(1, intStack.pop(), "Should pop third duplicate");
        assertTrue(intStack.isEmpty(), "Stack should be empty after popping all duplicates");
    }

    @Test
    @DisplayName("Test stack with different data types")
    void testDifferentDataTypes() {
        NodeStack<Character> charStack = new NodeStack<>();
        NodeStack<Boolean> booleanStack = new NodeStack<>();

        // Test with Character
        charStack.push('A');
        charStack.push('Z');
        assertEquals('Z', charStack.peek(), "Should handle Character values");

        // Test with Boolean
        booleanStack.push(Boolean.TRUE);
        booleanStack.push(Boolean.FALSE);
        assertEquals(Boolean.FALSE, booleanStack.peek(), "Should handle Boolean values");
    }

    @Test
    @DisplayName("Test stack state consistency after exceptions")
    void testStateConsistencyAfterExceptions() {
        // Stack should remain consistent after exception-throwing operations
        intStack.push(1);
        intStack.push(2);

        // Try to peek and pop normally first
        assertEquals(2, intStack.peek());
        assertEquals(2, intStack.pop());
        assertEquals(1, intStack.size());

        // Pop remaining element
        assertEquals(1, intStack.pop());
        assertTrue(intStack.isEmpty());

        // Now stack is empty, operations should throw exceptions
        assertThrows(IllegalStateException.class, intStack::peek);
        assertThrows(IllegalStateException.class, intStack::pop);

        // Stack should still be in valid empty state
        assertTrue(intStack.isEmpty());
        assertEquals(0, intStack.size());

        // Should be able to push after exceptions
        intStack.push(3);
        org.junit.jupiter.api.Assertions.assertFalse(intStack.isEmpty());
        assertEquals(1, intStack.size());
        assertEquals(3, intStack.peek());
    }

    @Test
    @DisplayName("Test single element stack operations")
    void testSingleElementStack() {
        intStack.push(2);

        org.junit.jupiter.api.Assertions.assertFalse(intStack.isEmpty(), "Stack with one element should not be empty");
        assertEquals(1, intStack.size(), "Size should be 1");
        assertEquals(2, intStack.peek(), "Peek should return the single element");
        assertEquals(1, intStack.size(), "Peek should not change size");

        assertEquals(2, intStack.pop(), "Pop should return the single element");
        assertTrue(intStack.isEmpty(), "Stack should be empty after popping single element");
        assertEquals(0, intStack.size(), "Size should be 0 after popping single element");
    }

    @Test
    @DisplayName("Test toString method if implemented")
    void testToString() {
        // This test assumes NodeStack has a toString method
        // If not implemented, this test can be removed or NodeStack can be enhanced
        intStack.push(1);
        intStack.push(2);
        intStack.push(3);

        String stackString = intStack.toString();
        // Basic check that toString doesn't throw exception and returns something
        assertTrue(stackString != null, "toString should not return null");
        assertTrue(stackString.length() > 0, "toString should return non-empty string");
    }
}
