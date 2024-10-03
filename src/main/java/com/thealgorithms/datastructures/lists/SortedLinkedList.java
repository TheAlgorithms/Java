package com.thealgorithms.datastructures.lists;

import java.util.ArrayList;
import java.util.List;

/**
 * A SortedLinkedList is a data structure that maintains a sorted list of elements.
 * Elements are ordered based on their natural ordering or by a Comparator provided at the time of creation.
 * This implementation uses a singly linked list to store the elements.
 * Further details can be found on this link
 * https://runestone.academy/ns/books/published/cppds/LinearLinked/ImplementinganOrderedList.html
 */
public class SortedLinkedList {
    private Node head;
    private Node tail;

    public SortedLinkedList() {
        this.head = null;
        this.tail = null;
    }

    /**
     * Inserts a new element into the sorted linked list.
     * @param value the value to be inserted
     */
    public void insert(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            this.head = newNode;
            this.tail = newNode;
        } else if (value < head.value) {
            newNode.next = this.head;
            this.head = newNode;
        } else if (value > tail.value) {
            this.tail.next = newNode;
            this.tail = newNode;
        } else {
            Node temp = head;
            while (temp.next != null && temp.next.value < value) {
                temp = temp.next;
            }
            newNode.next = temp.next;
            temp.next = newNode;
            if (newNode.next == null) {
                this.tail = newNode;
            }
        }
    }

    /**
     * Displays the elements of the sorted linked list.
     */
    public void display() {
        System.out.println(this.toString());
    }

    /**
     * Deletes the first occurrence of the specified element in the sorted linked list.
     * @param value the value to be deleted
     * @return true if the element is found and deleted, false otherwise
     */
    public boolean delete(int value) {
        if (this.head == null) {
            return false;
        } else if (this.head.value == value) {
            if (this.head.next == null) {
                this.head = null;
                this.tail = null;
            } else {
                this.head = this.head.next;
            }
            return true;
        } else {
            Node temp = this.head;
            while (temp.next != null) {
                if (temp.next.value == value) {
                    if (temp.next == this.tail) {
                        this.tail = temp;
                    }
                    temp.next = temp.next.next;
                    return true;
                }
                temp = temp.next;
            }
            return false;
        }
    }

    /**
     * Searches for the specified element in the sorted linked list.
     * @param value the value to be searched
     * @return true if the element is found, false otherwise
     */
    public boolean search(int value) {
        Node temp = this.head;
        while (temp != null) {
            if (temp.value == value) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    /**
     * Checks if the sorted linked list is empty.
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return head == null;
    }
    /**
     * Returns a string representation of the sorted linked list.
     * @return a string representation of the sorted linked list
     */
    @Override
    public String toString() {
        if (this.head != null) {
            List<String> elements = new ArrayList<>();
            Node temp = this.head;
            while (temp != null) {
                elements.add(String.valueOf(temp.value));
                temp = temp.next;
            }
            return "[" + String.join(", ", elements) + "]";

        } else {
            return "[]";
        }
    }

    public final class Node {
        public final int value;
        public Node next;

        public Node(int value) {
            this.value = value;
            this.next = null;
        }
    }
}
