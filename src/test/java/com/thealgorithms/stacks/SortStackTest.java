package com.thealgorithms.stacks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Stack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SortStackTest {

    private Stack<Integer> stack;

    @BeforeEach
    public void setUp() {
        stack = new Stack<>();
    }

    @Test
    public void testSortEmptyStack() {
        SortStack.sortStack(stack);
        assertTrue(stack.isEmpty()); // An empty stack should remain empty
    }

    @Test
    public void testSortSingleElementStack() {
        stack.push(10);
        SortStack.sortStack(stack);
        assertEquals(1, stack.size());
        assertEquals(10, (int) stack.peek()); // Single element should remain unchanged
    }

    @Test
    public void testSortAlreadySortedStack() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        SortStack.sortStack(stack);

        assertEquals(4, stack.size());
        assertEquals(4, (int) stack.pop());
        assertEquals(3, (int) stack.pop());
        assertEquals(2, (int) stack.pop());
        assertEquals(1, (int) stack.pop());
    }

    @Test
    public void testSortUnsortedStack() {
        stack.push(3);
        stack.push(1);
        stack.push(4);
        stack.push(2);
        SortStack.sortStack(stack);

        assertEquals(4, stack.size());
        assertEquals(4, (int) stack.pop());
        assertEquals(3, (int) stack.pop());
        assertEquals(2, (int) stack.pop());
        assertEquals(1, (int) stack.pop());
    }

    @Test
    public void testSortWithDuplicateElements() {
        stack.push(3);
        stack.push(1);
        stack.push(3);
        stack.push(2);
        SortStack.sortStack(stack);

        assertEquals(4, stack.size());
        assertEquals(3, (int) stack.pop());
        assertEquals(3, (int) stack.pop());
        assertEquals(2, (int) stack.pop());
        assertEquals(1, (int) stack.pop());
    }
}
