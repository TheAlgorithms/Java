package com.thealgorithms.datastructures.stacks;

/**
 * A stack implementation using linked nodes, supporting unlimited size without an ArrayList.
 *
 * <p>Each node in the stack contains data of generic type {@code Item}, along with references
 * to the next and previous nodes, supporting typical stack operations.
 *
 * <p>The stack follows a Last-In-First-Out (LIFO) order where elements added last are
 * removed first. Supported operations include push, pop, and peek.
 *
 * @param <Item> the type of elements held in this stack
 */
public class NodeStack<Item> {

    /**
     * Node class representing each element in the stack.
     */
    private class Node {
        Item data;
        Node previous;

        Node(Item data) {
            this.data = data;
            this.previous = null;
        }
    }

    private Node head; // Top node in the stack
    private int size; // Number of elements in the stack

    /**
     * Constructs an empty NodeStack.
     */
    public NodeStack() {
        head = null;
        size = 0;
    }

    /**
     * Pushes an item onto the stack.
     *
     * @param item the item to be pushed onto the stack
     */
    public void push(Item item) {
        Node newNode = new Node(item);
        newNode.previous = head;
        head = newNode;
        size++;
    }

    /**
     * Removes and returns the item at the top of the stack.
     *
     * @return the item at the top of the stack, or {@code null} if the stack is empty
     * @throws IllegalStateException if the stack is empty
     */
    public Item pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot pop from an empty stack.");
        }
        Item data = head.data;
        head = head.previous;
        size--;
        return data;
    }

    /**
     * Returns the item at the top of the stack without removing it.
     *
     * @return the item at the top of the stack, or {@code null} if the stack is empty
     * @throws IllegalStateException if the stack is empty
     */
    public Item peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot peek from an empty stack.");
        }
        return head.data;
    }

    /**
     * Checks whether the stack is empty.
     *
     * @return {@code true} if the stack has no elements, {@code false} otherwise
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Returns the number of elements currently in the stack.
     *
     * @return the size of the stack
     */
    public int size() {
        return size;
    }
}
