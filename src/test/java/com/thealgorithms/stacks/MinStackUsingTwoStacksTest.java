package com.thealgorithms.stacks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.EmptyStackException;
import org.junit.jupiter.api.Test;

public class MinStackUsingTwoStacksTest {

    @Test
    public void testBasicOperations() {
        MinStackUsingTwoStacks minStack = new MinStackUsingTwoStacks();
        minStack.push(3);
        minStack.push(5);
        assertEquals(3, minStack.getMin(), "Min should be 3");

        minStack.push(2);
        minStack.push(1);
        assertEquals(1, minStack.getMin(), "Min should be 1");

        minStack.pop();
        assertEquals(2, minStack.getMin(), "Min should be 2 after popping 1");

        assertEquals(2, minStack.top(), "Top should be 2");
    }

    @Test
    public void testPushDuplicateMins() {
        MinStackUsingTwoStacks minStack = new MinStackUsingTwoStacks();
        minStack.push(2);
        minStack.push(2);
        minStack.push(1);
        minStack.push(1);
        assertEquals(1, minStack.getMin(), "Min should be 1");

        minStack.pop();
        assertEquals(1, minStack.getMin(), "Min should still be 1 after popping one 1");

        minStack.pop();
        assertEquals(2, minStack.getMin(), "Min should be 2 after popping both 1s");

        minStack.pop();
        assertEquals(2, minStack.getMin(), "Min should still be 2 after popping one 2");

        minStack.pop();
        // Now stack is empty, expect exception on getMin
        assertThrows(EmptyStackException.class, minStack::getMin);
    }

    @Test
    public void testPopOnEmptyStack() {
        MinStackUsingTwoStacks minStack = new MinStackUsingTwoStacks();
        assertThrows(EmptyStackException.class, minStack::pop);
    }

    @Test
    public void testTopOnEmptyStack() {
        MinStackUsingTwoStacks minStack = new MinStackUsingTwoStacks();
        assertThrows(EmptyStackException.class, minStack::top);
    }

    @Test
    public void testGetMinOnEmptyStack() {
        MinStackUsingTwoStacks minStack = new MinStackUsingTwoStacks();
        assertThrows(EmptyStackException.class, minStack::getMin);
    }

    @Test
    public void testSingleElementStack() {
        MinStackUsingTwoStacks minStack = new MinStackUsingTwoStacks();
        minStack.push(10);
        assertEquals(10, minStack.getMin());
        assertEquals(10, minStack.top());

        minStack.pop();
        assertThrows(EmptyStackException.class, minStack::getMin);
    }

    @Test
    public void testIncreasingSequence() {
        MinStackUsingTwoStacks minStack = new MinStackUsingTwoStacks();
        minStack.push(1);
        minStack.push(2);
        minStack.push(3);
        minStack.push(4);

        assertEquals(1, minStack.getMin());
        assertEquals(4, minStack.top());

        minStack.pop();
        minStack.pop();
        assertEquals(1, minStack.getMin());
        assertEquals(2, minStack.top());
    }

    @Test
    public void testDecreasingSequence() {
        MinStackUsingTwoStacks minStack = new MinStackUsingTwoStacks();
        minStack.push(4);
        minStack.push(3);
        minStack.push(2);
        minStack.push(1);

        assertEquals(1, minStack.getMin());
        assertEquals(1, minStack.top());

        minStack.pop();
        assertEquals(2, minStack.getMin());
        assertEquals(2, minStack.top());
    }
}
