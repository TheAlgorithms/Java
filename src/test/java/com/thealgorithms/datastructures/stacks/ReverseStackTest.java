package com.thealgorithms.datastructures.stacks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Stack;
import org.junit.jupiter.api.Test;

class ReverseStackTest {

    @Test
    void testReverseEmptyStack() {
        Stack<Integer> stack = new Stack<>();
        ReverseStack.reverseStack(stack);
        assertTrue(stack.isEmpty(), "Reversing an empty stack should result in an empty stack.");
    }

    @Test
    void testReverseSingleElementStack() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        ReverseStack.reverseStack(stack);
        assertEquals(1, stack.peek(), "Reversing a single-element stack should have the same element on top.");
    }

    @Test
    void testReverseTwoElementStack() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        ReverseStack.reverseStack(stack);

        assertEquals(1, stack.pop(), "After reversal, the stack's top element should be the first inserted element.");
        assertEquals(2, stack.pop(), "After reversal, the stack's next element should be the second inserted element.");
    }

    @Test
    void testReverseMultipleElementsStack() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        ReverseStack.reverseStack(stack);

        assertEquals(1, stack.pop(), "Stack order after reversal should match the initial push order.");
        assertEquals(2, stack.pop());
        assertEquals(3, stack.pop());
        assertEquals(4, stack.pop());
    }

    @Test
    void testReverseStackAndVerifySize() {
        Stack<Integer> stack = new Stack<>();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        int originalSize = stack.size();

        ReverseStack.reverseStack(stack);

        assertEquals(originalSize, stack.size(), "Stack size should remain unchanged after reversal.");
        assertEquals(10, stack.pop(), "Reversal should place the first inserted element on top.");
        assertEquals(20, stack.pop());
        assertEquals(30, stack.pop());
        assertEquals(40, stack.pop());
    }
}
