package com.thealgorithms.datastructures.lists;

public final class FindMiddleOfSinglyLinkedList {

    static final class Node<E> {
        public Node<E> next;
        public E value;

        Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }

    /**
     * Finds the middle node of a singly linked list.
     *
     * @param head the head node of the singly linked list
     * @param <E>  the type of elements in the linked list
     * @return the middle node of the linked list; if the list is empty, returns null
     */
    public static <E> Node<E> findMiddle(Node<E> head) {
        if (head == null) {
            return null; // Empty list
        }

        Node<E> slowPointer = head;
        Node<E> fastPointer = head;

        // Move fastPointer two steps and slowPointer one step at a time
        while (fastPointer != null && fastPointer.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
        }

        // When fastPointer reaches the end, slowPointer will be at the middle
        return slowPointer;
    }
}
