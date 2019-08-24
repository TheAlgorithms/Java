package com.dataStructures;

import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

public class StackTest {

    @Test
    public void testEmpty() {

        Stack<Integer> myStack = new Stack<>();
        boolean isEmpty = myStack.empty();
        assertTrue(isEmpty);

        myStack.push(10);
        isEmpty = myStack.empty();
        assertFalse(isEmpty);
    }

    @Test
    public void testPeekWithoutElements() {
        Stack<Integer> myStack = new Stack<>();
        assertThrows(EmptyStackException.class, () -> myStack.peek());
    }

    @Test
    public void testPeekWithElements() {

        Stack<Integer> myStack = new Stack<>();
        myStack.push(10);
        myStack.push(20);
        myStack.push(30);
        myStack.push(40);

        assertEquals(40, myStack.peek());
    }

    @Test
    public void testPopWithoutElements() {
        Stack<Integer> myStack = new Stack<>();
        assertThrows(EmptyStackException.class, () -> myStack.pop());
    }

    @Test
    public void testPopWithElements() {

        Stack<Integer> myStack = new Stack<>();
        myStack.push(10);
        myStack.push(20);
        myStack.push(30);
        myStack.push(40);
        myStack.push(50);

        assertEquals(50, myStack.pop());

    }

    @Test
    public void testPushWithinInitialCapacity() {

        Stack<Integer> myStack = new Stack<>();
        myStack.push(10);
        myStack.push(20);
        myStack.push(30);
        myStack.push(40);
        myStack.push(50);
        myStack.push(60);
        myStack.push(70);
        myStack.push(80);
        myStack.push(90);
        myStack.push(100);
        assertEquals(10, myStack.size());
    }

    @Test
    public void testPushOutsideInitialCapacity() {

        Stack<Integer> myStack = new Stack<>();
        myStack.push(10);
        myStack.push(20);
        myStack.push(30);
        myStack.push(40);
        myStack.push(50);
        myStack.push(60);
        myStack.push(70);
        myStack.push(80);
        myStack.push(90);
        myStack.push(100);
        myStack.push(110);
        assertEquals(11, myStack.size());
    }

    @Test
    public void testSearchWithObjectUnavailable() {

        Stack<Integer> myStack = new Stack<>();
        myStack.push(10);
        myStack.push(20);
        myStack.push(30);
        assertEquals(-1,myStack.search(50));
    }

    @Test
    public void testSearchWithObjectAvailable() {

        Stack<Integer> myStack = new Stack<>();
        myStack.push(10);
        myStack.push(20);
        myStack.push(30);
        assertEquals(3,myStack.search(10));

    }
}
