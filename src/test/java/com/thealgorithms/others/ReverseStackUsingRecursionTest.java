package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Stack;
import org.junit.jupiter.api.Test;

public class ReverseStackUsingRecursionTest {

    @Test
    void testReverseWithMultipleElements() {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
        }

        ReverseStackUsingRecursion.reverse(stack);

        for (int i = 0; i < 5; i++) {
            assertEquals(i, stack.pop());
        }
        assertTrue(stack.isEmpty());
    }

    @Test
    void testReverseWithSingleElement() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);

        ReverseStackUsingRecursion.reverse(stack);

        assertEquals(1, stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    void testReverseWithEmptyStack() {
        Stack<Integer> stack = new Stack<>();

        ReverseStackUsingRecursion.reverse(stack);

        assertTrue(stack.isEmpty());
    }

    @Test
    void testReverseWithNullStack() {
        Stack<Integer> stack = null;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> ReverseStackUsingRecursion.reverse(stack));

        String expectedMessage = "Stack cannot be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
