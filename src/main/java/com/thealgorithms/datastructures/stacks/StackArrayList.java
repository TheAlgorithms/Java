package com.thealgorithms.datastructures.stacks;

import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 * This class implements a Stack using an ArrayList.
 *
 * @param <T> the type of elements in this stack
 */
public class StackArrayList<T> implements Stack<T> {

    private final ArrayList<T> stack;

    public StackArrayList() {
        stack = new ArrayList<>();
    }

    @Override
    public void push(T value) {
        stack.add(value);
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stack.removeLast();
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stack.getLast();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public void makeEmpty() {
        stack.clear();
    }

    @Override
    public int size() {
        return stack.size();
    }
}
