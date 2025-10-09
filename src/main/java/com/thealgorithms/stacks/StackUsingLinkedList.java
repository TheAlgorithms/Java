package com.thealgorithms.stacks;

/**
 * Stack implementation using a singly linked list.
 * 
 * @param <T> the type of elements stored in the stack
 * 
 * Operations:
 *  - push(T data): Insert an element onto the stack (O(1))
 *  - pop(): Remove and return the top element (O(1))
 *  - peek(): View the top element without removing it (O(1))
 *  - isEmpty(): Check if the stack is empty (O(1))
 *  - size(): Return the number of elements (O(1))
 */
public class StackUsingLinkedList<T> {

    /** Inner class representing a node in the linked list */
    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node top;   // top of the stack
    private int size;   // number of elements in the stack

    /** Constructor: Initializes an empty stack */
    public StackUsingLinkedList() {
        top = null;
        size = 0;
    }

    /** 
     * Pushes an element onto the top of the stack. 
     * @param data element to be pushed
     */
    public void push(T data) {
        Node newNode = new Node(data);
        newNode.next = top;
        top = newNode;
        size++;
    }

    /** 
     * Removes and returns the top element of the stack.
     * @return the popped element
     * @throws IllegalStateException if the stack is empty
     */
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty. Cannot pop.");
        }
        T data = top.data;
        top = top.next;
        size--;
        return data;
    }

    /** 
     * Returns the top element without removing it.
     * @return the top element
     * @throws IllegalStateException if the stack is empty
     */
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty. Cannot peek.");
        }
        return top.data;
    }

    /** 
     * Checks whether the stack is empty.
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return top == null;
    }

    /** 
     * Returns the number of elements in the stack.
     * @return the size of the stack
     */
    public int size() {
        return size;
    }

    /** 
     * Prints the stack elements from top to bottom.
     */
    public void printStack() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return;
        }
        Node current = top;
        System.out.print("Stack (top -> bottom): ");
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    /** 
     * Example usage 
     */
    public static void main(String[] args) {
        StackUsingLinkedList<Integer> stack = new StackUsingLinkedList<>();

        stack.push(10);
        stack.push(20);
        stack.push(30);

        stack.printStack();  // Output: 30 20 10

        System.out.println("Top element: " + stack.peek()); // 30
        System.out.println("Popped: " + stack.pop());       // 30
        stack.printStack();                                 // 20 10
        System.out.println("Size: " + stack.size());        // 2
    }
}
