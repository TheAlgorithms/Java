package datastructures.queues;

/**
 * This implements queues by using the class Queue.
 * <p>
 * A queue data structure functions the same as a real world queue.
 * The elements that are added first are the first to be removed.
 * New elements are added to the back/rear of the queue.
 *
 * @author Unknown
 */
public class Queue {
    /**
     * Max size of the queue
     */
    private int maxSize;
    /**
     * The array representing the queue
     */
    private int[] queueArray;
    /**
     * Front of the queue
     */
    private int front;
    /**
     * Rear of the queue
     */
    private int rear;
    /**
     * How many items are in the queue
     */
    private int nItems;

    /**
     * Constructor
     *
     * @param size Size of the new queue
     */
    public Queue(int size) {
        maxSize = size;
        queueArray = new int[size];
        front = 0;
        rear = -1;
        nItems = 0;
    }

    /**
     * Inserts an element at the rear of the queue
     *
     * @param x element to be added
     * @return True if the element was added successfully
     */
    public boolean insert(int x) {
        if (isFull())
            return false;
        //If the back of the queue is the end of the array wrap around to the front
        if (rear == maxSize - 1)
            rear = -1;
        rear++;
        queueArray[rear] = x;
        nItems++;
        return true;
    }

    /**
     * Remove an element from the front of the queue
     *
     * @return the new front of the queue
     */
    public int remove() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return -1;
        }
        int temp = queueArray[front];
        front++;
        if (front == maxSize) //Dealing with wrap-around again
            front = 0;
        nItems--;
        return temp;
    }

    /**
     * Checks what's at the front of the queue
     *
     * @return element at the front of the queue
     */
    public int peekFront() {
        return queueArray[front];
    }

    /**
     * Checks what's at the rear of the queue
     *
     * @return element at the rear of the queue
     */
    public int peekRear() {
        return queueArray[rear];
    }

    /**
     * Returns true if the queue is empty
     *
     * @return true if the queue is empty
     */
    public boolean isEmpty() {
        return (nItems == 0);
    }

    /**
     * Returns true if the queue is full
     *
     * @return true if the queue is full
     */
    public boolean isFull() {
        return (nItems == maxSize);
    }

    /**
     * Returns the number of elements in the queue
     *
     * @return number of elements in the queue
     */
    public int getSize() {
        return nItems;
    }
}