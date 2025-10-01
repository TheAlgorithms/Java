package com.thealgorithms.datastructures.lists;

/**
 * This class is a circular doubly linked list implementation. In a circular
 * doubly linked list,
 * the last node points back to the first node and the first node points back to
 * the last node,
 * creating a circular chain in both directions.
 *
 * This implementation includes basic operations such as appending elements to
 * the end,
 * removing elements from a specified position, and converting the list to a
 * string representation.
 *
 * @param <E> the type of elements held in this list
 */
public class CircularDoublyLinkedList<E> {
    static final class Node<E> {
        Node<E> next;
        Node<E> prev;
        E value;

        private Node(E value, Node<E> next, Node<E> prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }

    private int size;
    Node<E> head = null;

    /**
     * Initializes a new circular doubly linked list. A dummy head node is used for
     * simplicity,
     * pointing initially to itself to ensure the list is never empty.
     */
    public CircularDoublyLinkedList() {
        head = new Node<>(null, null, null);
        head.next = head;
        head.prev = head;
        size = 0;
    }

    /**
     * Returns the current size of the list.
     *
     * @return the number of elements in the list
     */
    public int getSize() {
        return size;
    }

    /**
     * Appends a new element to the end of the list. Throws a NullPointerException
     * if
     * a null value is provided.
     *
     * @param value the value to append to the list
     * @throws NullPointerException if the value is null
     */
    public void append(E value) {
        if (value == null) {
            throw new NullPointerException("Cannot add null element to the list");
        }
        Node<E> newNode = new Node<>(value, head, head.prev);
        head.prev.next = newNode;
        head.prev = newNode;
        size++;
    }

    /**
     * Returns a string representation of the list in the format "[ element1,
     * element2, ... ]".
     * An empty list is represented as "[]".
     *
     * @return the string representation of the list
     */
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[ ");
        Node<E> current = head.next;
        while (current != head) {
            sb.append(current.value);
            if (current.next != head) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append(" ]");
        return sb.toString();
    }

    /**
     * Removes and returns the element at the specified position in the list.
     * Throws an IndexOutOfBoundsException if the position is invalid.
     *
     * @param pos the position of the element to remove
     * @return the value of the removed element - pop operation
     * @throws IndexOutOfBoundsException if the position is out of range
     */
    public E remove(int pos) {
        if (pos >= size || pos < 0) {
            throw new IndexOutOfBoundsException("Position out of bounds");
        }
        Node<E> current = head.next;
        for (int i = 0; i < pos; i++) {
            current = current.next;
        }
        current.prev.next = current.next;
        current.next.prev = current.prev;
        E removedValue = current.value;
        size--;
        return removedValue;
    }
}
