package com.thealgorithms.datastructures.lists;

public class DoubleCircularLinkedList<E> {

    private static final class Node<E> {
        Node<E> next;
        Node<E> previous;
        E value;

        private Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }

    private int size;
    private Node<E> head = null;
    private Node<E> tail = null; // keeping a tail pointer to keep track of the end of the list

    public DoubleCircularLinkedList() {
        // creation of the dummy node
        head = new Node<>(null, null);
        tail = head; // Initially, head and tail point to the dummy node
        head.next = head; // Circular reference to itself
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public void append(E value) {
        if (value == null) {
            throw new NullPointerException("Cannot add null element to the list");
        }
       
        Node<E> newNode = new Node<>(value, head); // Create new node pointing to head
        if (size == 0) {
            head.next = newNode; // Update head's next to point to the new node
            newNode.previous = head; // Update the new node's previous to point to head
            tail = newNode; // Update tail to the new node
        } else {
            tail.next = newNode; // Previous tail points to the new node
            newNode.previous = tail;
            tail = newNode;
            head.previous = tail;
        }
        size++;
    }
    @Override
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
    return sb.toString();}

    public E remove(int pos) {
        if (pos >= size || pos < 0) {
            throw new IndexOutOfBoundsException("Position cannot be greater than size or negative");
        }

        Node<E> before = head;
        for (int i = 0; i < pos; i++) {
            before = before.next; // Move to the node before the one to be removed
        }
       
        Node<E> destroy = before.next; // Node to be removed
        E saved = destroy.value; // Save the value to return

        before.next = destroy.next; // Bypass the node to be removed
        destroy.next.previous = before;

        if (destroy == tail) { // If the tail is being removed
            tail = before; // Update the tail
        }

        size--;
        return saved; // Return the removed value
    }

    public static void main(String[] args)
    {
        DoubleCircularLinkedList<String> k = new DoubleCircularLinkedList<>();
        k.append("khalid");
        k.append("fahad");
        k.append("nora");
        System.out.println(k.getSize());
        System.out.println(k.toString());
     }
}