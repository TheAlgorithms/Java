package com.thealgorithms.datastructures.stacks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StackUsingLinkedListTest {
    
    @Test
    void testPushAndPop() {
        StackUsingLinkedList stack = new StackUsingLinkedList();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        
        assertEquals(30, stack.pop());
        assertEquals(20, stack.pop());
        assertEquals(10, stack.pop());
    }
    
    @Test
    void testPeek() {
        StackUsingLinkedList stack = new StackUsingLinkedList();
        stack.push(100);
        stack.push(200);
        
        assertEquals(200, stack.peek());
        assertEquals(200, stack.peek());
    }
    
    @Test
    void testIsEmpty() {
        StackUsingLinkedList stack = new StackUsingLinkedList();
        assertTrue(stack.isEmpty());
        
        stack.push(5);
        assertFalse(stack.isEmpty());
        
        stack.pop();
        assertTrue(stack.isEmpty());
    }
    
    @Test
    void testSize() {
        StackUsingLinkedList stack = new StackUsingLinkedList();
        assertEquals(0, stack.size());
        
        stack.push(1);
        assertEquals(1, stack.size());
        
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.size());
        
        stack.pop();
        assertEquals(2, stack.size());
    }
    
    @Test
    void testPopEmptyStack() {
        StackUsingLinkedList stack = new StackUsingLinkedList();
        assertThrows(RuntimeException.class, () -> stack.pop());
    }
    
    @Test
    void testPeekEmptyStack() {
        StackUsingLinkedList stack = new StackUsingLinkedList();
        assertThrows(RuntimeException.class, () -> stack.peek());
    }
}
