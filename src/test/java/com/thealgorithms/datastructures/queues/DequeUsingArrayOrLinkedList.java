package com.thealgorithms.queues;

import java.util.NoSuchElementException;

/**
 * Implementation of a Double-Ended Queue (Deque) using a doubly linked list.
 * <p>
 * A deque allows insertion and deletion from both the front and the rear.
 * It follows the FIFO principle at both ends.
 * </p>
 * 
 * Example:
 * DequeUsingArrayOrLinkedList<Integer> deque = new DequeUsingArrayOrLinkedList<>();
 * deque.addFront(1);
 * deque.addRear(2);
 * deque.removeFront(); // returns 1
 * deque.removeRear();  // returns 2
 * 
 * @param <T> the type of elements stored in the deque
 */
public final class DequeUsingArrayOrLinkedList<T> {

    private Node<T> front;
    private Node<T> rear;
    private int size;

    /** Node class for doubly linked list */
    private static class Node<T> {
        T data;
        Node<T> prev;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    /** Constructor to initialize an empty deque */
    public DequeUsingArrayOrLinkedList() {
        front = null;
        rear = null;
        size = 0;
    }

    /** Add an element at the front of the deque */
    public void addFront(T value) {
        Node<T> newNode = new Node<>(value);
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            newNode.next = front;
            front.prev = newNode;
            front = newNode;
        }
        size++;
    }

    /** Add an element at the rear of the deque */
    public void addRear(T value) {
        Node<T> newNode = new Node<>(value);
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            newNode.prev = rear;
            rear = newNode;
        }
        size++;
    }

    /** Remove and return the element from the front of the deque */
    public T removeFront() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        T value = front.data;
        front = front.next;
        if (front != null) {
            front.prev = null;
        } else {
            rear = null; // deque became empty
        }
        size--;
        return value;
    }

    /** Remove and return the element from the rear of the deque */
    public T removeRear() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        T value = rear.data;
        rear = rear.prev;
        if (rear != null) {
            rear.next = null;
        } else {
            front = null; // deque became empty
        }
        size--;
        return value;
    }

    /** Peek the front element without removing it */
    public T peekFront() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        return front.data;
    }

    /** Peek the rear element without removing it */
    public T peekRear() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        return rear.data;
    }

    /** Check if the deque is empty */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Return the number of elements in the deque */
    public int size() {
        return size;
    }
}
