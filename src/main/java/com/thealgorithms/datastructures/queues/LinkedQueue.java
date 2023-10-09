package com.thealgorithms.datastructures.queues;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class LinkedQueue<T> implements Iterable<T> {

    static class Node<T> {

        T data;
        Node<T> next;

        public Node() {
            this(null);
        }

        public Node(T data) {
            this(data, null);
        }

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }

    /**
     * Front of Queue
     */
    private Node<T> front;

    /**
     * Rear of Queue
     */
    private Node<T> rear;

    /**
     * Size of Queue
     */
    private int size;

    /**
     * Init LinkedQueue
     */
    public LinkedQueue() {
        front = rear = new Node<>();
    }

    /**
     * Check if queue is empty
     *
     * @return true if queue is empty, otherwise false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Add element to rear of queue
     *
     * @param data insert value
     */
    public void enqueue(T data) {
        Node<T> newNode = new Node<>(data);

        rear.next = newNode;
        rear = newNode;
        /* make rear point at last node */
        size++;
    }

    /**
     * Remove element at the front of queue
     *
     * @return element at the front of queue
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("queue is empty");
        }
        Node<T> destroy = front.next;
        T retValue = destroy.data;
        front.next = front.next.next;
        /* clear let GC do it's work */
        size--;

        if (isEmpty()) {
            front = rear;
        }

        return retValue;
    }

    /**
     * Peek element at the front of queue without removing
     *
     * @return element at the front
     */
    public T peekFront() {
        if (isEmpty()) {
            throw new NoSuchElementException("queue is empty");
        }
        return front.next.data;
    }

    /**
     * Peek element at the rear of queue without removing
     *
     * @return element at the front
     */
    public T peekRear() {
        if (isEmpty()) {
            throw new NoSuchElementException("queue is empty");
        }
        return rear.data;
    }

    /**
     * Peeks the element at the index and
     *          returns the value
     * @param pos at which to peek
     */

    public T peek(int pos) {
        if (pos > size) throw new IndexOutOfBoundsException("Position %s out of range!".formatted(pos));
        Node<T> node = front;
        while (pos-- > 0) node = node.next;
        return node.data;
    }

    /**
     * Node iterator, allows to travel through
     * the nodes using for() loop or forEach(Consumer)
     */

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> node = front;

            @Override
            public boolean hasNext() {
                return node.next != null;
            }

            @Override
            public T next() {
                return (node = node.next).data;
            }
        };
    }

    /**
     * Return size of queue
     *
     * @return size of queue
     */
    public int size() {
        return size;
    }

    /**
     * Clear all nodes in queue
     */
    public void clear() {
        while (size > 0) dequeue();
    }

    @Override
    public String toString() {
        StringJoiner join = new StringJoiner(", "); // separator of ', '
        Node<T> travel = front;
        while ((travel = travel.next) != null) join.add(String.valueOf(travel.data));
        return '[' + join.toString() + ']';
    }

    /* Driver Code */
    public static void main(String[] args) {
        LinkedQueue<Integer> queue = new LinkedQueue<>();
        assert queue.isEmpty();

        queue.enqueue(1);
        /* 1 */
        queue.enqueue(2);
        /* 1 2 */
        queue.enqueue(3);
        /* 1 2 3 */
        System.out.println(queue);
        /* [1, 2, 3] */

        assert queue.size() == 3;
        assert queue.dequeue() == 1;
        assert queue.peekFront() == 2;
        assert queue.peekRear() == 3;

        queue.clear();
        assert queue.isEmpty();

        System.out.println(queue);
        /* [] */
    }
}
