package com.thealgorithms.datastructures.lists;

import java.util.Objects;

/**
 * Node structure for the generic linked list.
 *
 * @param <E> the type of element held in this node
 */
class LinkedList<E> {
    E value;
    LinkedList<E> next;

    LinkedList(E value) {
        this.value = value;
        this.next = null;
    }
}

/**
 * A Self-Organizing Linked List implementation using the Move-To-Front (MTF) strategy.
 * When an element is searched, it is automatically moved to the head of the list
 * to optimize subsequent lookups.
 *
 * @param <E> the type of elements held in this list
 */
public class SelfOrganizingLinkedList<E> {
    private LinkedList<E> head;
    private int size;

    public SelfOrganizingLinkedList() {
        this.size = 0;
        this.head = null;
    }

    /** Inserts a new value at the end of the list. */
    public void insert(E value) {
        LinkedList<E> newNode = new LinkedList<>(value);
        if (head == null) {
            head = newNode;
        } else {
            LinkedList<E> temp = head;
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
        if (Objects.equals(head.value, key)) {
            return true;
        }

        LinkedList<E> prev = head;
        LinkedList<E> curr = head.next;

        while (curr != null && !Objects.equals(curr.value, key)) {
            prev = curr;
            curr = curr.next;
        }

        if (curr == null) {
            return false;
        }

        prev.next = curr.next;
        curr.next = head;
        head = curr;
        return true;
    }

    /** Gets the current head of the list. */
    public E getHeadValue() {
        return head != null ? head.value : null;
    }

    /** Returns the size of the list. */
    public int getSize() {
        return size;
    }

    /** Returns true if the list contains no elements. */
    public boolean isEmpty() {
        return head == null;
    }
}
