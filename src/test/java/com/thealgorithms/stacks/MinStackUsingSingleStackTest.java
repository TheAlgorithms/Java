package com.thealgorithms.stacks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.EmptyStackException;
import org.junit.jupiter.api.Test;

public class MinStackUsingSingleStackTest {

    @Test
    public void testBasicOperations() {
        MinStackUsingSingleStack minStack = new MinStackUsingSingleStack();

        minStack.push(3);
        minStack.push(5);
        assertEquals(3, minStack.getMin(), "Minimum should be 3");

        minStack.push(2);
        minStack.push(1);
        assertEquals(1, minStack.getMin(), "Minimum should be 1");

        minStack.pop();
        assertEquals(2, minStack.getMin(), "Minimum should be 2");

        minStack.pop();
        assertEquals(3, minStack.getMin(), "Minimum should be 3");
    }

    @Test
    public void testTopElement() {
        MinStackUsingSingleStack minStack = new MinStackUsingSingleStack();

        minStack.push(8);
        minStack.push(10);
        assertEquals(10, minStack.top(), "Top element should be 10");

        minStack.pop();
        assertEquals(8, minStack.top(), "Top element should be 8");
    }

    @Test
    public void testGetMinAfterPops() {
        MinStackUsingSingleStack minStack = new MinStackUsingSingleStack();

        minStack.push(5);
        minStack.push(3);
        minStack.push(7);

        assertEquals(3, minStack.getMin(), "Minimum should be 3");

        minStack.pop(); // Popping 7
        assertEquals(3, minStack.getMin(), "Minimum should still be 3");

        minStack.pop(); // Popping 3
        assertEquals(5, minStack.getMin(), "Minimum should now be 5");
    }

    @Test
    public void testEmptyStack() {
        MinStackUsingSingleStack minStack = new MinStackUsingSingleStack();

        assertThrows(EmptyStackException.class, minStack::top, "Should throw exception on top()");
        assertThrows(EmptyStackException.class, minStack::getMin, "Should throw exception on getMin()");
    }
}
