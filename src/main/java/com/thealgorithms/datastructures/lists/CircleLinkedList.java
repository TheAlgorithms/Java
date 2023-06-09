package com.thealgorithms.datastructures.lists;

public class CircleLinkedList<E> {

    private static class Node<E> {

        Node<E> next;
        E value;

        private Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }

    // For better O.O design this should be private allows for better black box design
    private int size;
    // this will point to dummy node;
    private Node<E> head = null;
    private Node<E> tail = null; // keeping a tail pointer to keep track of the end of list

    // constructor for class.. here we will make a dummy node for circly linked list implementation
    // with reduced error catching as our list will never be empty;
    public CircleLinkedList() {
        // creation of the dummy node
        head = new Node<E>(null, head);
        tail = head;
        size = 0;
    }

    // getter for the size... needed because size is private.
    public int getSize() {
        return size;
    }

    // for the sake of simplistiy this class will only contain the append function or addLast other
    // add functions can be implemented however this is the basses of them all really.
    public void append(E value) {
        if (value == null) {
            // we do not want to add null elements to the list.
            throw new NullPointerException("Cannot add null element to the list");
        }
        // head.next points to the last element;
        if (tail == null) {
            tail = new Node<E>(value, head);
            head.next = tail;
        } else {
            tail.next = new Node<E>(value, head);
            tail = tail.next;
        }
        size++;
    }

    // utility function for traversing the list
    public String toString() {
        Node p = head.next;
        String s = "[ ";
        while (p != head) {
            s += p.value;
            if (p != tail) {
                s += " , ";
            }
            p = p.next;
        }
        return s + " ]";
    }

    public E remove(int pos) {
        if (pos > size || pos < 0) {
            // catching errors
            throw new IndexOutOfBoundsException("position cannot be greater than size or negative");
        }
        // we need to keep track of the element before the element we want to remove we can see why
        // bellow.
        Node<E> before = head;
        for (int i = 1; i <= pos; i++) {
            before = before.next;
        }
        Node<E> destroy = before.next;
        E saved = destroy.value;
        // assigning the next reference to the element following the element we want to remove...
        // the last element will be assigned to the head.
        before.next = before.next.next;
        // scrubbing
        if (destroy == tail) {
            tail = before;
        }
        destroy = null;
        size--;
        return saved;
    }

    public static void main(String[] args) {
        CircleLinkedList cl = new CircleLinkedList<String>();
        cl.append(12);
        System.out.println(cl);
        cl.append(23);
        System.out.println(cl);
        cl.append(34);
        System.out.println(cl);
        cl.append(56);
        System.out.println(cl);
        cl.remove(3);
        System.out.println(cl);
    }
}
