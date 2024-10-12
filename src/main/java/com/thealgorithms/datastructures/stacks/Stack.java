package com.thealgorithms.datastructures.stacks;

/**
 * A generic interface for Stack data structures.
 *
 * @param <T> the type of elements in this stack
 */
public interface Stack<T> {

    /**
     * Adds an element to the top of the stack.
     *
     * @param value The element to add.
     */
    void push(T value);

    /**
     * Removes the element at the top of this stack and returns it.
     *
     * @return The element popped from the stack.
     * @throws IllegalStateException if the stack is empty.
     */
    T pop();

    /**
     * Returns the element at the top of this stack without removing it.
     *
     * @return The element at the top of this stack.
     * @throws IllegalStateException if the stack is empty.
     */
    T peek();

    /**
     * Tests if this stack is empty.
     *
     * @return {@code true} if this stack is empty; {@code false} otherwise.
     */
    boolean isEmpty();

    /**
     * Returns the size of this stack.
     *
     * @return The number of elements in this stack.
     */
    int size();

    /**
     * Removes all elements from this stack.
     */
    void makeEmpty();
}
public class LinkedListStack<T> implements Stack<T> {

    private Node<T> top = null;
    private int size = 0;

    private static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }

    @Override
    public void push(T value) {
        Node<T> newNode = new Node<>(value);
        newNode.next = top;
        top = newNode;
        size++;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        T value = top.data;
        top = top.next;
        size--;
        return value;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return top.data;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void makeEmpty() {
        top = null;
        size = 0;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
        } else {
            Node<T> temp = top;
            System.out.print("Stack: ");
            while (temp != null) {
                System.out.print(temp.data + " ");
                temp = temp.next;
            }
            System.out.println();
        }
    }

    // Main method
    public static void main(String[] args) {
        LinkedListStack<Integer> stack = new LinkedListStack<>();
        System.out.println("Pushing elements onto the stack:");
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.display();

        System.out.println("\nPopped an element from the stack:");
        System.out.println(stack.pop());
        stack.display();

        System.out.println("\nPeeking at the top element: " + stack.peek());
        System.out.println( stack.size());

        System.out.println("\nPushed element 100 onto the stack:");
        stack.push(100);
        stack.display();

        System.out.println("\nMaking the stack empty:");
        stack.makeEmpty();
        stack.display();
    }
}

