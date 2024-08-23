package com.thealgorithms.datastructures.queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<T> implements Iterable<T> {

    /**
     * Node class representing each element in the queue.
     */
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> front; // Front of the queue
    private Node<T> rear; // Rear of the queue
    private int size; // Size of the queue

    /**
     * Initializes an empty LinkedQueue.
     */
    public LinkedQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    /**
     * Checks if the queue is empty.
     *
     * @return true if the queue is empty, otherwise false.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Adds an element to the rear of the queue.
     *
     * @param data the element to insert.
     * @throws IllegalArgumentException if data is null.
     */
    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot enqueue null data");
        }

        Node<T> newNode = new Node<>(data);

        if (isEmpty()) {
            front = newNode;
        } else {
            rear.next = newNode;
        }
        rear = newNode;
        size++;
    }

    /**
     * Removes and returns the element at the front of the queue.
     *
     * @return the element at the front of the queue.
     * @throws NoSuchElementException if the queue is empty.
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }

        T retValue = front.data;
        front = front.next;
        size--;

        if (isEmpty()) {
            rear = null;
        }

        return retValue;
    }

    /**
     * Returns the element at the front of the queue without removing it.
     *
     * @return the element at the front of the queue.
     * @throws NoSuchElementException if the queue is empty.
     */
    public T peekFront() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return front.data;
    }

    /**
     * Returns the element at the rear of the queue without removing it.
     *
     * @return the element at the rear of the queue.
     * @throws NoSuchElementException if the queue is empty.
     */
    public T peekRear() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return rear.data;
    }

    /**
     * Returns the element at the specified position (1-based index).
     *
     * @param pos the position to peek at (1-based index).
     * @return the element at the specified position.
     * @throws IndexOutOfBoundsException if the position is out of range.
     */
    public T peek(int pos) {
        if (pos < 1 || pos > size) {
            throw new IndexOutOfBoundsException("Position " + pos + " out of range!");
        }

        Node<T> node = front;
        for (int i = 1; i < pos; i++) {
            node = node.next;
        }
        return node.data;
    }

    /**
     * Returns an iterator over the elements in the queue.
     *
     * @return an iterator over the elements in the queue.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node<T> current = front;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    /**
     * Returns the size of the queue.
     *
     * @return the size of the queue.
     */
    public int size() {
        return size;
    }

    /**
     * Clears all elements from the queue.
     */
    public void clear() {
        front = null;
        rear = null;
        size = 0;
    }

    /**
     * Returns a string representation of the queue.
     *
     * @return a string representation of the queue.
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        Node<T> current = front;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append(']');
        return sb.toString();
    }
}
