/**
 * A node in a doubly circular linked list.
 * URL: https://www.geeksforgeeks.org/introduction-to-circular-doubly-linked-list/
 *
 * @param <T> the type of data stored in the node
 */

package com.thealgorithms.datastructures;
class Node<T> {
    T data;
    Node<T> prev;
    Node<T> next;

    /**
     * Constructs a new node with the given data.
     *
     * @param data the data to store in the node
     */
    public Node(T data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

/**
 * A doubly circular linked list.
 *
 * @param <T> the type of data stored in the list
 */
public class DoubleCircularLinkedList<T> {
    private Node<T> head;
    private int size;

    /**
     * Constructs a new empty list.
     */
    public DoubleCircularLinkedList() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return the number of elements in the list
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if the list is empty, false otherwise.
     *
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Adds the given element to the end of the list.
     *
     * @param element the element to add to the list
     */
    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        if (isEmpty()) {
            head = newNode;
            head.prev = head;
            head.next = head;
        } else {
            Node<T> tail = head.prev;
            tail.next = newNode;
            newNode.prev = tail;
            newNode.next = head;
            head.prev = newNode;
        }
        size++;
    }

    /**
     * Removes the element at the given index from the list.
     * Throws an IndexOutOfBoundsException if the index is out of range.
     *
     * @param index the index of the element to remove
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }
        if (size == 1) {
            head = null;
        } else {
            Node<T> nodeToRemove = getNode(index);
            Node<T> prevNode = nodeToRemove.prev;
            Node<T> nextNode = nodeToRemove.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
            if (nodeToRemove == head) {
                head = nextNode;
            }
        }
        size--;
    }

    /**
     * Returns the element at the given index in the list.
     * Throws an IndexOutOfBoundsException if the index is out of range.
     *
     * @param index the index of the element to get
     * @return the element at the given index in the list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }
        return getNode(index).data;
    }

    /**
     * Returns the node at the given index in the list.
     *
     * @param index the index of the node to get
     * @return the node at the given index in the list
     */
    private Node<T> getNode(int index) {
        Node<T> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }
}
