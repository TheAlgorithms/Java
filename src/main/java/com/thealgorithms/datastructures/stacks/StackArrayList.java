package com.thealgorithms.datastructures.stacks;

import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 * A stack implementation backed by an {@link ArrayList}, offering dynamic resizing
 * and LIFO (Last-In-First-Out) behavior.
 *
 * <p>The stack grows dynamically as elements are added, and elements are removed
 * in reverse order of their addition.
 *
 * @param <T> the type of elements stored in this stack
 */
public class StackArrayList<T> implements Stack<T> {

    private final ArrayList<T> stack;

    /**
     * Constructs an empty stack.
     */
    public StackArrayList() {
        stack = new ArrayList<>();
    }

    /**
     * Adds an element to the top of the stack.
     *
     * @param value the element to be added
     */
    @Override
    public void push(T value) {
        stack.add(value);
    }

    /**
     * Removes and returns the element from the top of the stack.
     *
     * @return the element removed from the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    @Override
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stack.removeLast();
    }

    /**
     * Returns the element at the top of the stack without removing it.
     *
     * @return the top element of the stack
     * @throws EmptyStackException if the stack is empty
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stack.getLast();
    }

    /**
     * Checks if the stack is empty.
     *
     * @return {@code true} if the stack is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    /**
     * Empties the stack, removing all elements.
     */
    @Override
    public void makeEmpty() {
        stack.clear();
    }

    /**
     * Returns the number of elements in the stack.
     *
     * @return the current size of the stack
     */
    @Override
    public int size() {
        return stack.size();
    }
}
