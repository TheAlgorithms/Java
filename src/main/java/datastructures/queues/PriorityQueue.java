package datastructures.queues;

/**
 * This class implements a PriorityQueue.
 * <p>
 * A priority queue adds elements into positions based on their priority.
 * So the most important elements are placed at the front/on the top.
 * In this example I give numbers that are bigger, a higher priority.
 * queues in theory have no fixed size but when using an array
 * implementation it does.
 *
 * @author Unknown
 */
public class PriorityQueue {
    /**
     * The max size of the queue
     */
    private int maxSize;
    /**
     * The array for the queue
     */
    private int[] queueArray;
    /**
     * How many items are in the queue
     */
    private int nItems;

    /**
     * Constructor
     *
     * @param size Size of the queue
     */
    public PriorityQueue(int size) {
        maxSize = size;
        queueArray = new int[size];
        nItems = 0;
    }

    /**
     * Inserts an element in it's appropriate place
     *
     * @param value Value to be inserted
     */
    public void insert(int value) {
        if (nItems == 0) {
            queueArray[0] = value;
        } else {
            int j = nItems;
            while (j > 0 && queueArray[j - 1] > value) {
                //Shifts every element up to make room for insertion
                queueArray[j] = queueArray[j - 1];
                j--;
            }
            //Once the correct position is found the value is inserted
            queueArray[j] = value;
        }
        nItems++;
    }

    /**
     * Remove the element from the front of the queue
     *
     * @return The element removed
     */
    public int remove() {
        return queueArray[--nItems];
    }

    /**
     * Checks what's at the front of the queue
     *
     * @return element at the front of the queue
     */
    public int peek() {
        return queueArray[nItems - 1];
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