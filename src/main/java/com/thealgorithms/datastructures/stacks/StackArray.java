package com.thealgorithms.datastructures.stacks;

/**
 * Implements a generic stack using an array.
 *
 * <p>This stack automatically resizes when necessary, growing to accommodate additional elements and
 * shrinking to conserve memory when its size significantly decreases.
 *
 * <p>Elements are pushed and popped in LIFO (last-in, first-out) order, where the last element added
 * is the first to be removed.
 *
 * @param <T> the type of elements in this stack
 */
public class StackArray<T> implements Stack<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private int maxSize;
    private T[] stackArray;
    private int top;

    /**
     * Creates a stack with a default capacity.
     */
    @SuppressWarnings("unchecked")
    public StackArray() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Creates a stack with a specified initial capacity.
     *
     * @param size the initial capacity of the stack, must be greater than 0
     * @throws IllegalArgumentException if size is less than or equal to 0
     */
    @SuppressWarnings("unchecked")
    public StackArray(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Stack size must be greater than 0");
        }
        this.maxSize = size;
        this.stackArray = (T[]) new Object[size];
        this.top = -1;
    }

    /**
     * Pushes an element onto the top of the stack. Resizes the stack if it is full.
     *
     * @param value the element to push
     */
    @Override
    public void push(T value) {
        if (isFull()) {
            resize(maxSize * 2);
        }
        stackArray[++top] = value;
    }

    /**
     * Removes and returns the element from the top of the stack. Shrinks the stack if
     * its size is below a quarter of its capacity, but not below the default capacity.
     *
     * @return the element removed from the top of the stack
     * @throws IllegalStateException if the stack is empty
     */
    @Override
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty, cannot pop element");
        }
        T value = stackArray[top--];
        if (top + 1 < maxSize / 4 && maxSize > DEFAULT_CAPACITY) {
            resize(maxSize / 2);
        }
        return value;
    }

    /**
     * Returns the element at the top of the stack without removing it.
     *
     * @return the top element of the stack
     * @throws IllegalStateException if the stack is empty
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty, cannot peek element");
        }
        return stackArray[top];
    }

    /**
     * Resizes the internal array to a new capacity.
     *
     * @param newSize the new size of the stack array
     */
    private void resize(int newSize) {
        @SuppressWarnings("unchecked") T[] newArray = (T[]) new Object[newSize];
        System.arraycopy(stackArray, 0, newArray, 0, top + 1);
        stackArray = newArray;
        maxSize = newSize;
    }

    /**
     * Checks if the stack is full.
     *
     * @return true if the stack is full, false otherwise
     */
    public boolean isFull() {
        return top + 1 == maxSize;
    }

    /**
     * Checks if the stack is empty.
     *
     * @return true if the stack is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * Empties the stack, marking it as empty without deleting elements. Elements are
     * overwritten on subsequent pushes.
     */
    @Override
    public void makeEmpty() {
        top = -1;
    }

    /**
     * Returns the number of elements currently in the stack.
     *
     * @return the size of the stack
     */
    @Override
    public int size() {
        return top + 1;
    }

    /**
     * Returns a string representation of the stack.
     *
     * @return a string representation of the stack
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("StackArray [");
        for (int i = 0; i <= top; i++) {
            sb.append(stackArray[i]);
            if (i < top) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
