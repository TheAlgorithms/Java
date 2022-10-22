package com.thealgorithms.datastructures.heaps;

/**
 * Minimum Priority Queue It is a part of heap data structure A heap is a
 * specific tree based data structure in which all the nodes of tree are in a
 * specific order. that is the children are arranged in some respect of their
 * parents, can either be greater or less than the parent. This makes it a min
 * priority queue or max priority queue.
 *
 * <p>
 *
 * <p>
 * Functions: insert, delete, peek, isEmpty, print, heapSort, sink
 */
public class MaxPriorityQueue {

    private int[] heap;
    private int capacity;
    private int size;

    // calss the constructor and initializes the capacity
    MaxPriorityQueue(int c) {
        this.capacity = c;
        this.size = 0;
        this.heap = new int[c + 1];
    }

    // inserts the key at the end and rearranges it
    // so that the binary heap is in appropriate order
    public void insert(int key) {
        if (this.isFull()) {
            return;
        }
        this.heap[this.size + 1] = key;
        int k = this.size + 1;
        while (k > 1) {
            if (this.heap[k] > this.heap[k / 2]) {
                int temp = this.heap[k];
                this.heap[k] = this.heap[k / 2];
                this.heap[k / 2] = temp;
            }
            k = k / 2;
        }
        this.size++;
    }

    // returns the highest priority value
    public int peek() {
        return this.heap[1];
    }

    // returns boolean value whether the heap is empty or not
    public boolean isEmpty() {
        if (0 == this.size) {
            return true;
        }
        return false;
    }

    // returns boolean value whether the heap is full or not
    public boolean isFull() {
        if (this.size == this.capacity) {
            return true;
        }
        return false;
    }

    // prints the heap
    public void print() {
        for (int i = 1; i <= this.capacity; i++) {
            System.out.print(this.heap[i] + " ");
        }
        System.out.println();
    }

    // heap sorting can be done by performing
    // delete function to the number of times of the size of the heap
    // it returns reverse sort because it is a max priority queue
    public void heapSort() {
        while (this.size > 0){
            this.delete();
        }
    }

    // this function reorders the heap after every delete function
    private void sink() {
        int k = 1;
        while (2 * k <= this.size || 2 * k + 1 <= this.size) {
            int maxIndex;
            if (this.heap[2 * k] <= this.heap[k]) {
                if (
                    2 * k + 1 <= this.size &&
                    this.heap[2 * k + 1] <= this.heap[k]
                ) {
                    break;
                } else if (2 * k + 1 > this.size) {
                    break;
                }
            }
            if (2 * k + 1 > this.size) {
                maxIndex = this.heap[2 * k] > this.heap[k] ? 2 * k : k;
            } else {
                if (
                    this.heap[k] < this.heap[2 * k] ||
                    this.heap[k] < this.heap[2 * k + 1]
                ) {
                    maxIndex =
                        this.heap[2 * k] > this.heap[2 * k + 1]
                            ? 2 * k
                            : 2 * k + 1;
                } else {
                    maxIndex = k;
                }
            }
            int temp = this.heap[k];
            this.heap[k] = this.heap[maxIndex];
            this.heap[maxIndex] = temp;
            k = maxIndex;
        }
    }

    // deletes the highest priority value from the heap
    public int delete() {
        int max = this.heap[1];
        this.heap[1] = this.heap[this.size];
        this.heap[this.size] = max;
        this.size--;
        this.sink();
        return max;
    }
    
}
