package com.thealgorithms.datastructures.heaps;

import java.util.ArrayList;
import java.util.List;

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
public class MinPriorityQueue<T extends Comparable<T>> {

    private List<T> heap;
    private int capacity;
    private int size;

    // calss the constructor and initializes the capacity
    public MinPriorityQueue(int c) {
        this.capacity = c;
        this.size = 0;
        this.heap = new ArrayList<T>(c + 1); //new T[c + 1];
    }

    // inserts the key at the end and rearranges it
    // so that the binary heap is in appropriate order
    public void insert(T key) {
        if (this.isFull()) {
            return;
        }
        int k = this.size + 1;
        this.heap.set(k, key);
        
        while (k > 1) {
            if (this.heap.get(k).compareTo(this.heap.get(k / 2)) < 0) {
                T temp = this.heap.get(k);
                this.heap.set(k, this.heap.get(k / 2));
                this.heap.set(k / 2, temp);
            }
            k = k / 2;
        }
        this.size++;
    }

    // returns the highest priority value
    public T peek() {
        return this.heap.get(1);
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
            System.out.print(this.heap.get(i) + " ");
        }
        System.out.println();
    }

    // heap sorting can be done by performing
    // delete function to the number of times of the size of the heap
    // it returns reverse sort because it is a min priority queue
    public void heapSort() {
        while (this.size > 0){
            this.delete();
        }
    }

    // this function reorders the heap after every delete function
    private void sink() {
        int k = 1;
        while (2 * k <= this.size || 2 * k + 1 <= this.size) {
            int minIndex;
            if (this.heap.get(2 * k).compareTo(this.heap.get(k)) >= 0) {
                if (
                    2 * k + 1 <= this.size &&
                    this.heap.get(2 * k + 1).compareTo(this.heap.get(k)) >= 0
                ) {
                    break;
                } else if (2 * k + 1 > this.size) {
                    break;
                }
            }
            if (2 * k + 1 > this.size) {
                minIndex = this.heap.get(2 * k).compareTo(this.heap.get(k)) < 0 ? 2 * k : k;
            } else {
                if (
                    this.heap.get(k).compareTo(this.heap.get(2*k)) > 0 ||
                    this.heap.get(k).compareTo(this.heap.get(2*k + 1)) > 0
                ) {
                    minIndex =
                        this.heap.get(2 * k).compareTo(this.heap.get(2 * k + 1)) < 0
                            ? 2 * k
                            : 2 * k + 1;
                } else {
                    minIndex = k;
                }
            }
            T temp = this.heap.get(k);
            this.heap.set(k, this.heap.get(minIndex));
            this.heap.set(minIndex, temp);
            k = minIndex;
        }
    }

    // deletes the highest priority value from the heap
    public T delete() {
        T min = this.heap.get(1);
        this.heap.set(1, this.heap.get(this.size));
        this.heap.set(size, min);
        this.size--;
        this.sink();
        return min;
    }

    
}
