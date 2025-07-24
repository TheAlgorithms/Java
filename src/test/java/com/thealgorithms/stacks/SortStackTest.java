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

    @Test
    public void testSortReverseSortedStack() {
        // Test worst case scenario - completely reverse sorted
        stack.push(5);
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);
        SortStack.sortStack(stack);

        assertEquals(5, stack.size());
        assertEquals(5, (int) stack.pop());
        assertEquals(4, (int) stack.pop());
        assertEquals(3, (int) stack.pop());
        assertEquals(2, (int) stack.pop());
        assertEquals(1, (int) stack.pop());
    }

    @Test
    public void testSortWithAllSameElements() {
        // Test stack with all identical elements
        stack.push(7);
        stack.push(7);
        stack.push(7);
        stack.push(7);
        SortStack.sortStack(stack);

        assertEquals(4, stack.size());
        assertEquals(7, (int) stack.pop());
        assertEquals(7, (int) stack.pop());
        assertEquals(7, (int) stack.pop());
        assertEquals(7, (int) stack.pop());
    }

    @Test
    public void testSortWithNegativeNumbers() {
        // Test with negative numbers
        stack.push(-3);
        stack.push(1);
        stack.push(-5);
        stack.push(2);
        stack.push(-1);
        SortStack.sortStack(stack);

        assertEquals(5, stack.size());
        assertEquals(2, (int) stack.pop());
        assertEquals(1, (int) stack.pop());
        assertEquals(-1, (int) stack.pop());
        assertEquals(-3, (int) stack.pop());
        assertEquals(-5, (int) stack.pop());
    }

    @Test
    public void testSortWithAllNegativeNumbers() {
        // Test with only negative numbers
        stack.push(-10);
        stack.push(-5);
        stack.push(-15);
        stack.push(-1);
        SortStack.sortStack(stack);

        assertEquals(4, stack.size());
        assertEquals(-1, (int) stack.pop());
        assertEquals(-5, (int) stack.pop());
        assertEquals(-10, (int) stack.pop());
        assertEquals(-15, (int) stack.pop());
    }

    @Test
    public void testSortWithZero() {
        // Test with zero included
        stack.push(3);
        stack.push(0);
        stack.push(-2);
        stack.push(1);
        SortStack.sortStack(stack);

        assertEquals(4, stack.size());
        assertEquals(3, (int) stack.pop());
        assertEquals(1, (int) stack.pop());
        assertEquals(0, (int) stack.pop());
        assertEquals(-2, (int) stack.pop());
    }

    @Test
    public void testSortLargerStack() {
        // Test with a larger number of elements
        int[] values = {15, 3, 9, 1, 12, 6, 18, 4, 11, 8};
        for (int value : values) {
            stack.push(value);
        }

        SortStack.sortStack(stack);

        assertEquals(10, stack.size());

        // Verify sorted order (largest to smallest when popping)
        int[] expectedOrder = {18, 15, 12, 11, 9, 8, 6, 4, 3, 1};
        for (int expected : expectedOrder) {
            assertEquals(expected, (int) stack.pop());
        }
    }

    @Test
    public void testSortTwoElements() {
        // Test edge case with exactly two elements
        stack.push(5);
        stack.push(2);
        SortStack.sortStack(stack);

        assertEquals(2, stack.size());
        assertEquals(5, (int) stack.pop());
        assertEquals(2, (int) stack.pop());
    }

    @Test
    public void testSortTwoElementsAlreadySorted() {
        // Test two elements already in correct order
        stack.push(2);
        stack.push(5);
        SortStack.sortStack(stack);

        assertEquals(2, stack.size());
        assertEquals(5, (int) stack.pop());
        assertEquals(2, (int) stack.pop());
    }

    @Test
    public void testSortStackWithMinAndMaxValues() {
        // Test with Integer.MAX_VALUE and Integer.MIN_VALUE
        stack.push(0);
        stack.push(Integer.MAX_VALUE);
        stack.push(Integer.MIN_VALUE);
        stack.push(100);
        SortStack.sortStack(stack);

        assertEquals(4, stack.size());
        assertEquals(Integer.MAX_VALUE, (int) stack.pop());
        assertEquals(100, (int) stack.pop());
        assertEquals(0, (int) stack.pop());
        assertEquals(Integer.MIN_VALUE, (int) stack.pop());
    }

    @Test
    public void testSortWithManyDuplicates() {
        // Test with multiple sets of duplicates
        stack.push(3);
        stack.push(1);
        stack.push(3);
        stack.push(1);
        stack.push(2);
        stack.push(2);
        stack.push(3);
        SortStack.sortStack(stack);

        assertEquals(7, stack.size());
        assertEquals(3, (int) stack.pop());
        assertEquals(3, (int) stack.pop());
        assertEquals(3, (int) stack.pop());
        assertEquals(2, (int) stack.pop());
        assertEquals(2, (int) stack.pop());
        assertEquals(1, (int) stack.pop());
        assertEquals(1, (int) stack.pop());
    }

    @Test
    public void testOriginalStackIsModified() {
        // Verify that the original stack is modified, not a copy
        Stack<Integer> originalReference = stack;
        stack.push(3);
        stack.push(1);
        stack.push(2);

        SortStack.sortStack(stack);

        // Verify it's the same object reference
        assertTrue(stack == originalReference);
        assertEquals(3, stack.size());
        assertEquals(3, (int) stack.pop());
        assertEquals(2, (int) stack.pop());
        assertEquals(1, (int) stack.pop());
    }
}
