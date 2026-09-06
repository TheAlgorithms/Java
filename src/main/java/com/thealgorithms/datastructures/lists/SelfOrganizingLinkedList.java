package com.thealgorithms.datastructures.lists;

import java.util.Objects;

/**
 * A Self-Organizing Linked List implementation using the Move-To-Front (MTF) strategy.
 * When an element is searched, it is automatically moved to the head of the list
 * to optimize subsequent lookups.
 *
 * @param <E> the type of elements held in this list
 */
public class SelfOrganizingLinkedList<E> {

    /**
     * Node structure for the self-organizing linked list.
     *
     * @param <E> the type of element held in this node
     */
    private static class Node<E> {
        E value;
        Node<E> next;

        Node(E value) {
            this.value = value;
            this.next = null;
        }
    }

    private Node<E> head;
    private int size;

    public SelfOrganizingLinkedList() {
        this.size = 0;
        this.head = null;
    }

    /**
     * Inserts a new value at the end of the list.
     *
     * @param value the element to add
     */
    public void insert(E value) {
        Node<E> newNode = new Node<>(value);
        if (head == null) {
            head = newNode;
        } else {
            Node<E> temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        size++;
    }

    /**
     * Searches for a value in the list.
     * If found, moves the node to the front (head) of the list.
     *
     * @param key the value to search for
     * @return true if the element is present, false otherwise
     */
    public boolean search(E key) {
        if (head == null) {
            return false;
        }
        // If the key is already at the head, no pointers need to be rewired
        if (Objects.equals(head.value, key)) {
            return true;
        }

        Node<E> prev = head;
        Node<E> curr = head.next;

        while (curr != null && !Objects.equals(curr.value, key)) {
            prev = curr;
            curr = curr.next;
        }

        if (curr == null) {
            return false;
        }

        // Unlink curr from its current position and move it to head
        prev.next = curr.next;
        curr.next = head;
        head = curr;
        return true;
    }

    /** Gets the current head value of the list. */
    public E getHeadValue() {
        return head != null ? head.value : null;
    }

    /** Returns the size of the list. */
    public int getSize() {
        return size;
    }

    /** Returns true if the list contains no elements. */
    public boolean isEmpty() {
        return size == 0;
    }
}
