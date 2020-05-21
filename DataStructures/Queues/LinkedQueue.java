package DataStructures.Queues;

import java.util.NoSuchElementException;

public class LinkedQueue {
    class Node {
        int data;
        Node next;

        public Node() {
            this(0);
        }

        public Node(int data) {
            this(data, null);
        }

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    /**
     * Front of Queue
     */
    private Node front;

    /**
     * Rear of Queue
     */
    private Node rear;

    /**
     * Size of Queue
     */
    private int size;

    /**
     * Init LinkedQueue
     */
    public LinkedQueue() {
        front = rear = new Node();
    }

    /**
     * Check if queue is empty
     *
     * @return <tt>true</tt> if queue is empty, otherwise <tt>false</tt>
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Add element to rear of queue
     *
     * @param data insert value
     * @return <tt>true</tt> if add successfully
     */
    public boolean enqueue(int data) {
        Node newNode = new Node(data);
        rear.next = newNode;
        rear = newNode; /* make rear point at last node */
        size++;
        return true;
    }

    /**
     * Remove element at the front of queue
     *
     * @return element at the front of queue
     */
    public int dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("queue is empty");
        }
        Node destroy = front.next;
        int retValue = destroy.data;
        front.next = front.next.next;
        destroy = null; /* clear let GC do it's work */
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
    public int peekFront() {
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
    public int peekRear() {
        if (isEmpty()) {
            throw new NoSuchElementException("queue is empty");
        }
        return rear.data;
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
        while (!isEmpty()) {
            dequeue();
        }
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder builder = new StringBuilder();
        Node cur = front.next;
        builder.append("[");
        while (cur != null) {
            builder.append(cur.data).append(", ");
            cur = cur.next;
        }
        builder.replace(builder.length() - 2, builder.length(), "]");
        return builder.toString();
    }

    /* Driver Code */
    public static void main(String[] args) {
        LinkedQueue queue = new LinkedQueue();
        assert queue.isEmpty();

        queue.enqueue(1); /* 1 */
        queue.enqueue(2); /* 1 2 */
        queue.enqueue(3); /* 1 2 3 */
        System.out.println(queue); /* [1, 2, 3] */

        assert queue.size() == 3;
        assert queue.dequeue() == 1;
        assert queue.peekFront() == 2;
        assert queue.peekRear() == 3;

        queue.clear();
        assert queue.isEmpty();

        System.out.println(queue); /* [] */
    }
}
