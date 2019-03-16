package src.main.java.com.dataStructures;

import java.io.Serializable;
import java.util.EmptyStackException;

public class Stack<E> implements Serializable {

    /**
     * Initial capacity allocated to stack on object creation
     */
    private final int INITIAL_CAPACITY = 10;

    /**
     * Increment in memory space once stack is out of space
     */
    private final int EXTENDED_CAPACITY = 10;


    /**
     * Position of tail in stack
     */

    private int tail = -1;

    /**
     * Size of stack at any given time
     */

    private int size;

    /**
     * Uninitialized array to hold stack elements.
     * WIll be initialized with initial capacity once the object is created
     */
    private Object[] elements;

    /**
     * No argument to create stack object with initial capacity
     */
    public Stack() {
        elements = new Object[INITIAL_CAPACITY];
    }

    /**
     * Method to check if the given stack is empty or not
     */

    public boolean empty() {
        return elements == null || size == 0;
    }


    /**
     * Method to check the element on head without removing it
     */

    public Object peek() {
        if (empty()) {
            throw new EmptyStackException();
        }

        return elements[tail];
    }

    /**
     * Method to remove the top element from stack
     */

    public Object pop() {
        if (empty()) {
            throw new EmptyStackException();
        }

        Object removedElement = elements[tail];
        tail--;
        size--;
        return removedElement;
    }

    /**
     * Method to add element to stack
     */
    public Object push(Object e) {

        boolean isSuccess = false;
        if (tail < (INITIAL_CAPACITY - 1)) {
            tail++;
            elements[tail] = e;
        } else {
            Object[] extendedElements = new Object[INITIAL_CAPACITY + EXTENDED_CAPACITY];
            System.arraycopy(elements, 0, extendedElements, 0, (tail + 1));
            elements = extendedElements;
            tail++;
            elements[tail] = e;
        }
        size++;
        return e;

    }

    /**
     * Method to search for an element in stack
     */

    public int search(Object o) {

        int index = -1;
        boolean found = false;
        if (empty()) {
            return -1;
        }

        for (int i = 0; i < size(); i++) {
            if (elements[i] == o) {
                index = i;
                found = true;
                break;
            }
        }

        if (found) {
            index = tail - index + 1;
        }

        return index;
    }

    /**
     * Method to get size of stack
     */
    public int size() {
        return size;
    }
}
