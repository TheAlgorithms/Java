package com.thealgorithms.datastructures.heaps;

/**
 * A MinPriorityQueue is a specialized data structure that maintains the
 * min-heap property, where the smallest element has the highest priority.
 *
 * <p>In a min-priority queue, every parent node is less than or equal
 * to its child nodes, which ensures that the smallest element can
 * always be efficiently retrieved.</p>
 *
 * <p>Functions:</p>
 * <ul>
 *     <li><b>insert(int key)</b>: Inserts a new key into the queue.</li>
 *     <li><b>delete()</b>: Removes and returns the highest priority value (the minimum).</li>
 *     <li><b>peek()</b>: Returns the highest priority value without removing it.</li>
 *     <li><b>isEmpty()</b>: Checks if the queue is empty.</li>
 *     <li><b>isFull()</b>: Checks if the queue is full.</li>
 *     <li><b>heapSort()</b>: Sorts the elements in ascending order.</li>
 *     <li><b>print()</b>: Prints the current elements in the queue.</li>
 * </ul>
 */
public class MinPriorityQueue {

    private final int[] heap;
    private final int capacity;
    private int size;

    /**
     * Initializes a new MinPriorityQueue with a specified capacity.
     *
     * @param c the maximum number of elements the queue can hold
     */
    public MinPriorityQueue(int c) {
        this.capacity = c;
        this.size = 0;
        this.heap = new int[c + 1];
    }

    /**
     * Inserts a new key into the min-priority queue.
     *
     * @param key the value to be inserted
     */
    public void insert(int key) {
        if (this.isFull()) {
            throw new IllegalStateException("MinPriorityQueue is full. Cannot insert new element.");
        }
        this.heap[this.size + 1] = key;
        int k = this.size + 1;
        while (k > 1) {
            if (this.heap[k] < this.heap[k / 2]) {
                int temp = this.heap[k];
                this.heap[k] = this.heap[k / 2];
                this.heap[k / 2] = temp;
            }
            k = k / 2;
        }
        this.size++;
    }

    /**
     * Retrieves the highest priority value (the minimum) without removing it.
     *
     * @return the minimum value in the queue
     * @throws IllegalStateException if the queue is empty
     */
    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("MinPriorityQueue is empty. Cannot peek.");
        }
        return this.heap[1];
    }

    /**
     * Checks whether the queue is empty.
     *
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks whether the queue is full.
     *
     * @return true if the queue is full, false otherwise
     */
    public boolean isFull() {
        return size == capacity;
    }

    /**
     * Prints the elements of the queue.
     */
    public void print() {
        for (int i = 1; i <= this.size; i++) {
            System.out.print(this.heap[i] + " ");
        }
        System.out.println();
    }

    /**
     * Sorts the elements in the queue using heap sort.
     */
    public void heapSort() {
        for (int i = 1; i <= this.size; i++) {
            this.delete();
        }
    }

    /**
     * Reorders the heap after a deletion to maintain the heap property.
     */
    private void sink() {
        int k = 1;
        while (2 * k <= this.size) {
            int minIndex = k; // Assume current index is the minimum

            if (2 * k <= this.size && this.heap[2 * k] < this.heap[minIndex]) {
                minIndex = 2 * k; // Left child is smaller
            }
            if (2 * k + 1 <= this.size && this.heap[2 * k + 1] < this.heap[minIndex]) {
                minIndex = 2 * k + 1; // Right child is smaller
            }

            if (minIndex == k) {
                break; // No swap needed, heap property is satisfied
            }

            // Swap with the smallest child
            int temp = this.heap[k];
            this.heap[k] = this.heap[minIndex];
            this.heap[minIndex] = temp;

            k = minIndex; // Move down to the smallest child
        }
    }

    /**
     * Deletes and returns the highest priority value (the minimum) from the queue.
     *
     * @return the minimum value from the queue
     * @throws IllegalStateException if the queue is empty
     */
    public int delete() {
        if (isEmpty()) {
            throw new IllegalStateException("MinPriorityQueue is empty. Cannot delete.");
        }
        int min = this.heap[1];
        this.heap[1] = this.heap[this.size]; // Move last element to the root
        this.size--;
        this.sink();
        return min;
    }
}
