package com.thealgorithms.datastructures.stacks;

import java.util.NoSuchElementException;

/**
 * A stack implementation using a singly linked list.
 *
 * <p>This class provides methods to push, pop, and peek elements in a Last-In-First-Out (LIFO) manner.
 * It keeps track of the number of elements in the stack and allows checking if the stack is empty.
 *
 * <p>This implementation does not allow null elements to be pushed onto the stack.
 */
final class StackOfLinkedList {
    private StackOfLinkedList() {
    }
}

// A node class for the linked list
class Node {
    public int data;
    public Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

/**
 * A class that implements a stack using a linked list.
 *
 * <p>This stack supports basic operations:
 * <ul>
 * <li>push: Adds an element to the top of the stack</li>
 * <li>pop: Removes and returns the top element of the stack</li>
 * <li>peek: Returns the top element without removing it</li>
 * <li>isEmpty: Checks if the stack is empty</li>
 * <li>getSize: Returns the current size of the stack</li>
 * </ul>
 */
class LinkedListStack {

    private Node head; // Top of the stack
    private int size; // Number of elements in the stack

    /**
     * Initializes an empty stack.
     */
    LinkedListStack() {
        head = null;
        size = 0;
    }

    /**
     * Adds an element to the top of the stack.
     *
     * @param x the element to be added
     * @return <tt>true</tt> if the element is added successfully
     */
    public boolean push(int x) {
        Node newNode = new Node(x);
        newNode.next = head;
        head = newNode;
        size++;
        return true;
    }

    /**
     * Removes and returns the top element of the stack.
     *
     * @return the element at the top of the stack
     * @throws NoSuchElementException if the stack is empty
     */
    public int pop() {
        if (size == 0) {
            throw new NoSuchElementException("Empty stack. Nothing to pop");
        }
        Node destroy = head;
        head = head.next;
        int retValue = destroy.data;
        destroy = null; // Help garbage collection
        size--;
        return retValue;
    }

    /**
     * Returns the top element of the stack without removing it.
     *
     * @return the element at the top of the stack
     * @throws NoSuchElementException if the stack is empty
     */
    public int peek() {
        if (size == 0) {
            throw new NoSuchElementException("Empty stack. Nothing to peek");
        }
        return head.data;
    }

    @Override
    public String toString() {
        Node cur = head;
        StringBuilder builder = new StringBuilder();
        while (cur != null) {
            builder.append(cur.data).append("->");
            cur = cur.next;
        }
        return builder.replace(builder.length() - 2, builder.length(), "").toString(); // Remove the last "->"
    }

    /**
     * Checks if the stack is empty.
     *
     * @return <tt>true</tt> if the stack is empty, <tt>false</tt> otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the current size of the stack.
     *
     * @return the number of elements in the stack
     */
    public int getSize() {
        return size;
    }

    /**
     * Removes all elements from the stack.
     */
    public void makeEmpty() {
        head = null;
        size = 0;
    }
}
