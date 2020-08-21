package com.datastructures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

class StackTest {

    @Test
    void testEmpty() {

        Stack<Integer> myStack = new Stack<>();
        boolean isEmpty = myStack.empty();
        Assertions.assertTrue(isEmpty);

        myStack.push(10);
        isEmpty = myStack.empty();
        Assertions.assertFalse(isEmpty);
    }

    @Test
    void testPeekWithoutElements() {
        Assertions.assertThrows(EmptyStackException.class, () -> {
            Stack<Integer> myStack = new Stack<>();
            myStack.peek();
        });
    }

    @Test
    void testPeekWithElements() {

        Stack<Integer> myStack = new Stack<>();
        myStack.push(10);
        myStack.push(20);
        myStack.push(30);
        myStack.push(40);

        Assertions.assertEquals(40, (int) myStack.peek());
    }

    @Test
    void testPopWithoutElements() {
        Assertions.assertThrows(EmptyStackException.class, () -> {
            Stack<Integer> myStack = new Stack<>();
            myStack.pop();
        });
    }

    @Test
    void testPopWithElements() {

        Stack<Integer> myStack = new Stack<>();
        myStack.push(10);
        myStack.push(20);
        myStack.push(30);
        myStack.push(40);
        myStack.push(50);

        Assertions.assertEquals(50, (int) myStack.pop());

    }

    @Test
    void testPushWithinInitialCapacity() {

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
        Assertions.assertEquals(10, myStack.size());
    }

    @Test
    void testPushOutsideInitialCapacity() {

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
        Assertions.assertEquals(11, myStack.size());
    }

    @Test
    void testSearchWithObjectUnavailable() {

        Stack<Integer> myStack = new Stack<>();
        myStack.push(10);
        myStack.push(20);
        myStack.push(30);
        Assertions.assertEquals(-1, myStack.search(50));
    }

    @Test
    void testSearchWithObjectAvailable() {

        Stack<Integer> myStack = new Stack<>();
        myStack.push(10);
        myStack.push(20);
        myStack.push(30);
        Assertions.assertEquals(3, myStack.search(10));

    }
}
