package com.thealgorithms.stacks;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class StackUsingLinkedListTest {
    private StackUsingLinkedList<Integer> stack;

    @BeforeEach
    public void setUp() {
        stack = new StackUsingLinkedList<>();
    }

    @Test
    public void testPushAndPeek() {
        stack.push(10);
        stack.push(20);

        assertEquals(20, stack.peek());
    }

    @Test
    public void testPop() {
        stack.push(5);
        stack.push(15);
        int popped = stack.pop();
        assertEquals(15, popped);
        assertEquals(5, stack.peek());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(stack.isEmpty());
        stack.push(1);
        assertFalse(stack.isEmpty());
    }

    @Test
    public void testSize() {
        assertEquals(0, stack.size());
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.size());
    }

    @Test
    public void testPopOnEmptyStack() {
        assertThrows(RuntimeException.class, () -> stack.pop());
    }
    @Test
    public void testPeekOnEmptyStackThrowsException() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> stack.peek());
        assertEquals("Stack is empty", exception.getMessage());
    }
}
