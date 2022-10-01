package com.thealgorithms.datastructures.lists;

public class CircleDoublyLinkedList<E> {

    private class Node<T> {
        Node<T> next, previous;
        T element;

        public Node(T element) {
            this.next = null;
            this.previous = null;
            this.element = element;
        }
    }

    private Node<E> head, tail;
    private int size;

    public int getSize() {
        return size;
    }


    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e);

        if (head == null) {
            head = tail = newNode;
            head.previous = tail;
            tail.next = head;
        } else {
            // (reconnection)
            head.previous = newNode;
            newNode.next = head;

            head = newNode;
            // update tail and head's next and previous nodes respectively
            tail.next = head;
            head.previous = tail;
        }

        size++;
    }


    public void addLast(E e) {
        Node<E> newNode = new Node<>(e);

        if (tail == null) {
            head = tail = newNode;
            tail.next = head;
        } else {
            tail.next = newNode;
            newNode.previous = tail;

            tail = newNode;
            // update tail and head's next and previous nodes respectively
            tail.next = head;
            head.previous = tail;
        }

        size++;
    }


    public void add(int index, E e) {
        if (index < 0)
            throw new IndexOutOfBoundsException("Negative index is not possible!");
        else if (index == 0)
            addFirst(e);
        else if (index >= size - 1)
            addLast(e);
        else if (index <= size / 2) {
            Node<E> newNode = new Node<>(e);

            // Add element at the left part of the list (starting from head)
            Node<E> current = head;

            for (int i = 0; i < index - 1; i++) // traverse and stop before the requested index
                current = current.next;

            Node<E> temp = current.next; // temporary for disconnection

            current.next = newNode;
            newNode.next = temp;
            newNode.previous = current;
            size++;
        } else {
            Node<E> newNode = new Node<>(e);

            // Add element at the right part of the list (starting from the tail)
            Node<E> current = tail;

            for (int i = 0; i < size - index; i++)
                current = current.previous;

            Node<E> temp = current.next;

            current.next = newNode;
            newNode.next = temp;
            newNode.previous = current;
            size++;
        }
    }


    public void add(E e) {
        if (size == 0)
            addFirst(e);
        else
            addLast(e);
    }


    public E removeFirst() {
        if (size == 0)
            return null;
        else {
            Node<E> temp = head;

            head = head.next;
            head.previous = null;

            // update tail and head's next and previous nodes respectively
            tail.next = head;
            head.previous = tail;
            size--;

            return temp.element;
        }
    }


    public E removeLast() {
        if (size == 0)
            return null;
        else if (size == 1) {
            Node<E> temp = head;
            head = tail = null;
            size = 0;
            return temp.element;
        } else {
            Node<E> temp = tail;
            tail.previous.next = null;
            tail = tail.previous;

            // update tail and head's next and previous nodes respectively
            tail.next = head;
            head.previous = tail;
            size--;
            return temp.element;
        }
    }


    public E remove(int index) {
        if (index < 0)
            throw new IndexOutOfBoundsException("Negative index is not possible!");
        else if (index == 0)
            removeFirst();
        else if (index >= size - 1)
            removeLast();
        else if (index <= size / 2) {
            // Remove node at the left side (start from head)
            Node<E> current = head;

            for (int i = 0; i < index - 1; i++)
                current = current.next;

            Node<E> temp = current.next;
            current.next = temp.next;
            current.next.previous = current;
            size--;

            return temp.element;
        } else {
            // Remove node at the right side (start from tail)
            Node<E> current = tail;

            for (int i = 0; i < size - index - 1; i++) // traverse until the node before index
                current = current.previous;

            Node<E> temp = current.next;
            current.next = temp.next;
            current.next.previous = current;
            size--;

            return temp.element;
        }
        return null;
    }


    public boolean contains(E e) {
        if (size != 0) {
            Node<E> current = head;

            if (tail.element.equals(e)) // the loop will skip when current node reaches the tail node. (need improvement
                // here if possible)
                return true;
            while (current != tail) {
                if (current.element.equals(e))
                    return true;

                current = current.next;
            }
        }
        return false;
    }


    public E get(int index) {
        if (size == 0 || index < 0 || index >= size) {
            return null;
        } else if (index <= size / 2) {
            // get the node at the left side of the list (starting from head)
            Node<E> current = head;

            for (int i = 0; i < index; i++)
                current = current.next;

            return current.element;
        } else {
            // get the node at the right side of the list (starting from tail)
            Node<E> current = tail;

            for (int i = 0; i < size - index - 1; i++)
                current = current.previous;

            return current.element;
        }
    }


    public E getFirst() {
        return head.element;
    }


    public E getMiddle() {
        return get(size / 2);
    }


    public E getLast() {
        return tail.element;
    }


    public int indexOf(E e) {
        Node<E> current = head;

        for (int i = 0; i < size; i++) {
            if (current.element.equals(e))
                return i;

            current = current.next;
        }

        return -1;
    }


    public int lastIndexOf(E e) {
        Node<E> current = tail; // starting from tail

        for (int i = size - 1; i >= 0; i--) {
            if (current.element.equals(e))
                return i;

            current = current.previous;
        }

        return -1;
    }


    public E set(int index, E e) {
        if (index < 0 || index >= size)
            return null;
        else if (index <= size / 2) {
            // set the node at the left part (starting from head)
            Node<E> current = head;

            for (int i = 0; i < index; i++)
                current = current.next;

            E temp = current.element;
            current.element = e;

            return temp;
        } else {
            // set the node at the right part (starting from tail)
            Node<E> current = tail;

            for (int i = 0; i < size - index - 1; i++)
                current = current.previous;

            E temp = current.element;
            current.element = e;

            return temp;
        }
    }


    public void clear() {
        head = tail = null;
        size = 0;
    }


    public void print() {
        if (head == null) {
            System.out.println("List is empty!");
            return;
        }

        Node<E> current = head;

        System.out.print("<-> ");
        while (current != tail) {
            System.out.print(current.element + " <-> ");
            current = current.next;
        }
        System.out.println(tail.element + " <-> ");

        /*System.out.println("Head: " + head.element + "\nTail: " + tail.element);

        System.out.println("Element after tail: " + tail.next.element);
        System.out.println("Element before head: " + head.previous.element);*/
    }


    public void reverse() {
        Node<E> previous;
        Node<E> current = head;

        do {
            previous = current.previous;

            // swap previous and next nodes of the current node
            current.previous = current.next;
            current.next = previous;

            current = current.previous; // go to the 'next' node (which is now the previous node)
        } while (current != head);

        tail = head;
        head = previous.previous;

        // update tail and head's next and previous node respectively
        tail.next = head;
        head.previous = tail;
    }
}