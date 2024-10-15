package com.thealgorithms.stacks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinStackUsingTwoStacksTest {

    @Test
    public void testMinStackOperations() {
        MinStackUsingTwoStacks minStack = new MinStackUsingTwoStacks();
        minStack.push(3);
        minStack.push(5);
        assertEquals(3, minStack.getMin());

        minStack.push(2);
        minStack.push(1);
        assertEquals(1, minStack.getMin());

        minStack.pop();
        assertEquals(2, minStack.getMin());
    }

    @Test
    public void testMinStackOperations2() {
        MinStackUsingTwoStacks minStack = new MinStackUsingTwoStacks();
        minStack.push(3);
        minStack.push(5);
        assertEquals(3, minStack.getMin());

        minStack.push(2);
        minStack.push(1);
        assertEquals(1, minStack.getMin());

        minStack.pop();
        assertEquals(2, minStack.getMin());
    }
}
