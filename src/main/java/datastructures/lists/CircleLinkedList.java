package datastructures.lists;

public class CircleLinkedList<E> {
    private static class Node<E> {
        Node<E> next;
        E value;

        private Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }

    //For better O.O design this should be private allows for better black box design
    private int size;
    //this will point to dummy node;
    private Node<E> head;

    //Make a node for circly linked list implementation with reduced error catching as our list will never be empty;
    public CircleLinkedList() {
        head = new Node<>(null, head);
        size = 0;
    }

    public int getSize() {
        return size;
    }

    // for the sake of simplistiy this class will only contain the append function or addLast
    // other add functions can be implemented however this is the basses of them all really.
    public void append(E value) {
        if (value == null) {
            throw new NullPointerException("Cannot add null element to the list");
        }
        //head.next points to the last element;
        head.next = new Node<>(value, head);
        size++;
    }

    public E remove(int pos) {
        if (pos > size || pos < 0) {
            throw new IndexOutOfBoundsException("position cannot be greater than size or negative"); //catching errors
        }
        Node<E> iterator = head.next;
        //we need to keep track of the element before the element we want to remove we can see why bellow.
        Node<E> before = head;
        for (int i = 1; i <= pos; i++) {
            iterator = iterator.next;
            before = before.next;
        }
        E saved = iterator.value;
        // assigning the next referance to the the element following the element we want to remove...
        // the last element will be assigned to the head.
        before.next = iterator.next;
        // scrubbing
        iterator.next = null;
        iterator.value = null;
        return saved;
    }
}

