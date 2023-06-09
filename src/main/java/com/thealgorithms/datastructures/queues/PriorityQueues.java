package com.thealgorithms.datastructures.queues;

/**
 * This class implements a PriorityQueue.
 *
 * <p>
 * A priority queue adds elements into positions based on their priority. So the
 * most important elements are placed at the front/on the top. In this example I
 * give numbers that are bigger, a higher priority. Queues in theory have no
 * fixed size but when using an array implementation it does.
 * <p>
 * Additional contibutions made by: PuneetTri(https://github.com/PuneetTri)
 */
class PriorityQueue {

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
     * Default Constructor
     */

    public PriorityQueue() {
        /* If capacity is not defined, default size of 11 would be used
         *  capacity=max+1 because we cant access 0th element of PQ, and to
         *  accomodate (max)th elements we need capacity to be max+1.
         *  Parent is at position k, child at position (k*2,k*2+1), if we
         *  use position 0 in our queue, its child would be at:
         *  (0*2, 0*2+1) -> (0,0). This is why we start at position 1
         */
        int size = 11; // Default value of 11
        maxSize = size + 1;
        queueArray = new int[maxSize];
        nItems = 0;
    }

    /**
     * Parameterized Constructor
     *
     * @param size Size of the queue
     */

    public PriorityQueue(int size) {
        maxSize = size + 1;
        queueArray = new int[maxSize];
        nItems = 0;
    }

    /**
     * Helper function for the max-heap implementation of PQ
     * Function would help demote parent node to their correct
     * position
     *
     * @param pos Position of newly added element at bottom
     */
    private void swim(int pos) {
        // Check if parent is smaller than child node
        while (pos > 1 && (queueArray[pos / 2] < queueArray[pos])) {
            // In such case swap value of child with parent
            int temp = queueArray[pos];
            queueArray[pos] = queueArray[pos / 2];
            queueArray[pos / 2] = temp;
            pos = pos / 2; // Jump to position of parent node
        }
        // Promotion of child node will go on until it becomes smaller than the parent
    }

    /**
     * Helper function for the max-heap implementation of PQ
     * Function would help demote parent node to their correct
     * position
     *
     * @param pos Position of element at top
     */
    private void sink(int pos) {
        // Check if node's position is that of parent node
        while (2 * pos <= nItems) {
            int current = 2 * pos; // Jump to the positon of child node
            // Compare both the children for the greater one
            if (current < nItems && queueArray[current] < queueArray[current + 1]) current++;
            // If the parent node is greater, sink operation is complete. Break the loop
            if (queueArray[pos] >= queueArray[current]) break;

            // If not exchange the value of parent with child
            int temp = queueArray[pos];
            queueArray[pos] = queueArray[current];
            queueArray[current] = temp;
            pos = current; // Exchange parent position to child position in the array
        }
    }

    /**
     * Inserts an element in it's appropriate place
     *
     * @param value Value to be inserted
     */
    public void insert(int value) {
        // Print overflow message if the capacity is full
        if (isFull()) {
            throw new RuntimeException("Queue is full");
        } else {
            queueArray[++nItems] = value;
            swim(nItems); // Swim up the element to its correct position
        }
    }

    /**
     * Dequeue the element with the max priority from PQ
     *
     * @return The element removed
     */
    public int remove() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is Empty");
        } else {
            int max = queueArray[1]; // By defintion of our max-heap, value at queueArray[1] pos is
                                     // the greatest

            // Swap max and last element
            int temp = queueArray[1];
            queueArray[1] = queueArray[nItems];
            queueArray[nItems] = temp;
            queueArray[nItems--] = 0; // Nullify the last element from the priority queue
            sink(1); // Sink the element in order

            return max;
        }
    }

    /**
     * Checks what's at the front of the queue
     *
     * @return element at the front of the queue
     */
    public int peek() {
        return queueArray[1];
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
        return (nItems == maxSize - 1);
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
