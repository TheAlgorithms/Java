package com.thealgorithms.datastructures.stacks;

/**
 * This class implements a Stack using a regular array.
 *
 * @param <T> the type of elements in this stack
 */
public class StackArray<T> implements Stack<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private int maxSize;
    private T[] stackArray;
    private int top;

    @SuppressWarnings("unchecked")
    public StackArray() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public StackArray(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Stack size must be greater than 0");
        }
        this.maxSize = size;
        this.stackArray = (T[]) new Object[size];
        this.top = -1;
    }

    @Override
    public void push(T value) {
        if (isFull()) {
            resize(maxSize * 2);
        }
        stackArray[++top] = value;
    }

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

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty, cannot peek element");
        }
        return stackArray[top];
    }

    private void resize(int newSize) {
        @SuppressWarnings("unchecked") T[] newArray = (T[]) new Object[newSize];
        System.arraycopy(stackArray, 0, newArray, 0, top + 1);
        stackArray = newArray;
        maxSize = newSize;
    }

    public boolean isFull() {
        return top + 1 == maxSize;
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override public void makeEmpty() { // Doesn't delete elements in the array but if you call
        top = -1; // push method after calling makeEmpty it will overwrite previous values
    }

    @Override
    public int size() {
        return top + 1;
    }
}
